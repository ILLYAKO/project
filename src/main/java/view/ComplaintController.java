package view;

import domain.Complaint;
import domain.ComplaintType;
import domain.User;
import domain.UserType;
import exception.EntityNotFoundException;
import service.ComplaintService;
import service.ProtectedConfigFile;
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

public class ComplaintController extends BaseController {
    private Service<Complaint> service;

    public void init() {
        service = new ComplaintService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--doGet--");
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //HttpSession session = request.getSession();
        System.out.println("--doPost--");
        try {
            String action = extractAction(request);

            switch (action) {
                case "/add":
                    showComplaintForm(request, response);
                    break;
                case "/insert":
                    //insertComplaint(request, response);
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
                case "/listComplaint":
                    listComplaint(request, response);
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

    private void listComplaint(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("-listComplaint-");
//            HttpSession session = request.getSession();
//            User user = (User)session.getAttribute("user");

            List<Complaint> listComplaint = service.findAll();
            request.setAttribute("listComplaint", listComplaint);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/views/pages/complaintpages/ComplaintList.jsp");
            dispatcher.forward(request, response);
    }

    private void showComplaintForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("complaintForm.jsp");
            request.setAttribute("types", ComplaintType.getComplaintTypeFullName());
            request.setAttribute("isNew", true);
            dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                String id = request.getParameter("id");
                Complaint existingComplaint = service.findById(id);
                RequestDispatcher dispatcher = request
                        .getRequestDispatcher("/pages/complaintpages/complaintForm.jsp");
                request.setAttribute("types", UserType.values());
                request.setAttribute("user", existingComplaint);
                request.setAttribute("isEdit", true);
                dispatcher.forward(request, response);
            } catch (EntityNotFoundException e) {
                request.setAttribute("message", e.getMessage());
                listComplaint(request, response);
            }

    }

//    private void insertComplaint(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Complaint complaint = null;
//
//
//            try {
//                String complaintId = UUID.randomUUID().toString();//A universally unique identifier (UUID) is a 128-bit number used to identify information in computer systems
//                User informer = request.getParameter("userfirstname");
//                String complaintType= request.getParameter("usersecondname");
//                String complaintPart      = request.getParameter("username");
//                //String userPassword  = request.getParameter("userpassword");
//                ProtectedConfigFile protectedConfigFile =
//                        new ProtectedConfigFile(request.getParameter("userpassword"));
//                String userPassword = protectedConfigFile.getEncryptedPassword();
//                String complaintDescription = request.getParameter("userage");
//                String userGender    = request.getParameter("usergender");
//                String userEmail = request.getParameter("useremail");
//                String userAddress   = request.getParameter("useraddress");
//                String userType = request.getParameter("usertype");
//                System.out.println("UserController.insertUser userType: " + userType);
//
//
//                complaint = new Complaint(complaintId,
//                        informer,
//                        complaintType,
//                        complaintPart,
//                        complaintDescription);
//
//                service.add(complaint);
//                request.setAttribute("message", Message.buildSuccessMessage("User added successfully"));
//                listComplaint(request, response);
//            } catch (Exception e) {
//                request.setAttribute("complaint", complaint);
//                request.setAttribute("message", processException(e));
//                request.setAttribute("isNew", true);
//                RequestDispatcher dispatcher = request
//                        .getRequestDispatcher("/index.jsp");
//                dispatcher.forward(request, response);
//            }
//    }

    private void updateComplaint(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Complaint complaint = null;
            try {
                String id = request.getParameter("id");
                String email = request.getParameter("email");
                //
//            ProtectedConfigFile protectedConfigFile = null;
//            protectedConfigFile = new ProtectedConfigFile(request.getParameter("password"));
//            String password = protectedConfigFile.getEncryptedPassword();
                String password = request.getParameter("password");

                //



                String userType = request.getParameter("type");

//                user = new User(id,
//                        email, password, UserType.valueOf(userType));
                complaint = new Complaint();


                service.modify(complaint);
                request.setAttribute("message", Message.buildSuccessMessage("User updated successfully"));
                listComplaint(request, response);
            } catch (Exception e) {
                request.setAttribute("user", complaint);
                request.setAttribute("message", processException(e));
                request.setAttribute("isEdit", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/UserForm.jsp");
                dispatcher.forward(request, response);
            }
    }

    private void deleteComplaint(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                String id = request.getParameter("id");
                service.remove(id);
                request.setAttribute("message", Message.buildSuccessMessage("User deleted successfully"));
            } catch (Exception e) {
                request.setAttribute("message", processException(e));
            }
        listComplaint(request, response);

    }

    private void askForLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/pages/userpages/login.jsp");
            dispatcher.forward(request, response);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        System.out.println("-loginUser-");

       // HttpSession session = request.getSession();

        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");

        System.out.println("userEmail: " + userEmail);
        System.out.println("password: " + password);

        User requiredUser = new User(userEmail,password);
        System.out.println("requiredUser.getUserEmail(): " + requiredUser.getUserEmail());
        System.out.println("requiredUser.getUserPassword(): " + requiredUser.getUserPassword());

        UserService userService =new UserService();
        User existingUser = null;
        RequestDispatcher dispatcher;
        try {
            existingUser = userService.login(requiredUser);

            if (existingUser != null){
                System.out.println("-existingUser-");
                request.setAttribute("user", existingUser);
                dispatcher = request.getRequestDispatcher("/views/pages/userpages/userRegistered.jsp");
                dispatcher.forward(request, response);
            }else {
                //request.setAttribute("message", "CREATE USER");
                System.out.println("Something wrong -01 in loginUser");
                request.setAttribute("isWrong", true);

                try {
                    System.out.println("Something wrong -02 in loginUser");
                    //showRegistrationForm(request, response);
                }
                //catch (ServletException e1)
                 catch (Exception e1){
                    e1.printStackTrace();
                }

                System.out.println("-NULL-");
            }
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            try {
                System.out.println("Something wrong -03 in loginUser");
                request.setAttribute("isWrong", true);
                askForLogin(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("-logoutUser-");
        RequestDispatcher dispatcher;
        User user = null;
        HttpSession session = request.getSession();

        if(session != null){
            session.invalidate();}
        request.setAttribute("user", user);
        dispatcher = request.getRequestDispatcher("/index.jsp");//.forward(request,response);
        dispatcher.forward(request, response);
    }





    }

