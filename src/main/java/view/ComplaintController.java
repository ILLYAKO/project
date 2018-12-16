package view;

import domain.*;
import exception.EntityNotFoundException;
import service.*;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ComplaintController extends BaseController {
    private Service<Complaint> service;
    private Service<Advice> adviceService;

    public void init() {
        service = new ComplaintService();
        adviceService = new AdviceService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = extractAction(request);
            switch (action) {
                case "/add":
                    showComplaintForm(request, response);
                    break;
                case "/insert":
                    insertComplaint(request, response);
                    break;
                case "/delete":
                    deleteComplaint(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateComplaint(request, response);
                    break;
                case "/listComplaintOfUser":
                    listComplaintOfUser(request, response);
                    break;
                default:
                    // showComplaintForm(request, response);
                    break;
            }
        } catch (ServletException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private String extractAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "/askForLogin";
        } else {
            return pathInfo;
        }
    }

    private void listComplaintOfUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Complaint> listComplaint = service.findAllComplaintOfUser(user);
        List<Advice> listAdvice = adviceService.findAll();
        request.setAttribute("listComplaint", listComplaint);
        request.setAttribute("listAdvice", listAdvice);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/views/pages/complaintpages/complaintList.jsp");
        dispatcher.forward(request, response);
    }

    private void listComplaint(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Complaint> listComplaint = service.findAll();
        request.setAttribute("listComplaint", listComplaint);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/views/pages/complaintpages/complaintList.jsp");
        dispatcher.forward(request, response);
    }

    private void showComplaintForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/views/pages/complaintpages/complaintForm.jsp");
        request.setAttribute("types", ComplaintType.getComplaintTypeFullName());
        request.setAttribute("isNew", true);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Complaint existingComplaint = service.findById(id);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/views/pages/complaintpages/complaintForm.jsp");
            dispatcher.forward(request, response);
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            listComplaintOfUser(request, response);
        }
    }

    private void insertComplaint(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Complaint complaint = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String complaintTypeShortName = request.getParameter("complaintTypeShortName");
        String complaintPartName = request.getParameter("complaintPartName");
        String complaintPartDescription = request.getParameter("complaintPartDescription");

        if (complaintTypeShortName.isEmpty() || complaintPartName.isEmpty() || complaintPartDescription.isEmpty()) {
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("/views/pages/complaintpages/complaintList.jsp");//complaintForm.jsp
            rd.forward(request, response);
        } else {
            Complaint newComplaint = new Complaint(
                    user,
                    complaintTypeShortName,
                    complaintPartName,
                    complaintPartDescription);
            ComplaintService complaintService = new ComplaintService();
            complaintService.add(newComplaint);
            listComplaintOfUser(request, response);
        }
    }

    private void updateComplaint(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Complaint complaint = null;
        try {
            String id = request.getParameter("id");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String userType = request.getParameter("type");
            complaint = new Complaint();

            service.modify(complaint);
            request.setAttribute("message", Message.buildSuccessMessage("User updated successfully"));
            listComplaintOfUser(request, response);
        } catch (Exception e) {
            request.setAttribute("user", complaint);
            request.setAttribute("message", processException(e));
            request.setAttribute("isEdit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/UserForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteComplaint(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.remove(id);
            request.setAttribute("message", Message.buildSuccessMessage("User deleted successfully"));
        } catch (Exception e) {
            request.setAttribute("message", processException(e));
        }
        listComplaintOfUser(request, response);

    }

    private void askForLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/pages/userpages/login.jsp");
        dispatcher.forward(request, response);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");

        User requiredUser = new User(userEmail, password);

        UserService userService = new UserService();
        User existingUser = null;
        RequestDispatcher dispatcher;
        try {
            existingUser = userService.login(requiredUser);

            if (existingUser != null) {
                request.setAttribute("user", existingUser);
                dispatcher = request.getRequestDispatcher("/views/pages/userpages/userRegistered.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("isWrong", true);
                try {
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            try {
                request.setAttribute("isWrong", true);
                askForLogin(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher;
        User user = null;
        HttpSession session = request.getSession();

        if (session != null) {
            session.invalidate();
        }
        request.setAttribute("user", user);
        dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}