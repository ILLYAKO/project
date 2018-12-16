package view;

import domain.Advice;
import domain.User;
import exception.EntityNotFoundException;
import service.AdviceService;
import service.Service;
import service.UserService;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class AdviceController extends BaseController {
    private Service<Advice> service;

    public void init() {
        service = new AdviceService();
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
                    showAdviceForm(request, response);
                    break;
                case "/insert":
                    insertAdvice(request, response);
                    break;
                case "/delete":
                    deleteAdvice(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateAdvice(request, response);
                    break;
                case "/listAdvice":
                    listAdvice(request, response);
                    break;
                default:
                    // showAdviceForm(request, response);
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

    private void listAdvice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Advice> listAdvice = service.findAll();
        request.setAttribute("listAdvice", listAdvice);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/views/pages/advicepages/adviceList.jsp");
        dispatcher.forward(request, response);
    }

    private void showAdviceForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/views/pages/advicepages/adviceForm.jsp");
        request.setAttribute("isNew", true);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("adviceId");
            Advice existingAdvice = service.findById(id);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/advicepages/adviceForm.jsp");
            dispatcher.forward(request, response);
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            listAdvice(request, response);
        }
    }

    private void insertAdvice(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Advice advice = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //A universally unique identifier (UUID) is a 128-bit number used to identify information in computer systems
        String adviceId = UUID.randomUUID().toString();
        String adviceName = request.getParameter("adviceName");
        String adviceType = request.getParameter("adviceType");
        String advicePart = request.getParameter("advicePart");
        String adviceDescription = request.getParameter("adviceDescription");

        if (adviceName.isEmpty() || advicePart.isEmpty() || adviceDescription.isEmpty()) {
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("/views/pages/advicepages/adviceList.jsp");
            rd.forward(request, response);
        } else {
            Advice newAdvice = new Advice(
                    adviceId,
                    adviceName,
                    user,
                    adviceType,
                    advicePart,
                    adviceDescription);
            AdviceService adviceService = new AdviceService();
            adviceService.add(newAdvice);
            listAdvice(request, response);
        }
    }

    private void updateAdvice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Advice advice = null;
        try {
            String id = request.getParameter("id");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String userType = request.getParameter("type");
            advice = new Advice();
            service.modify(advice);
            request.setAttribute("message", Message.buildSuccessMessage("User updated successfully"));
            listAdvice(request, response);
        } catch (Exception e) {
            request.setAttribute("user", advice);
            request.setAttribute("message", processException(e));
            request.setAttribute("isEdit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/UserForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteAdvice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("adviceId");
            service.remove(id);
            request.setAttribute("message", Message.buildSuccessMessage("User deleted successfully"));
        } catch (Exception e) {
            request.setAttribute("message", processException(e));
        }
        listAdvice(request, response);
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

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher;
        User user = null;
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        request.setAttribute("user", user);
        dispatcher = request.getRequestDispatcher("/index.jsp");//.forward(request,response);
        dispatcher.forward(request, response);
    }
}