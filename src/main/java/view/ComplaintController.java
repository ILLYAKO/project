package view;

import domain.Complaint;
import domain.User;
import domain.UserType;
import exception.EntityNotFoundException;
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


/**
 * Servlet implementation class ComplaintController
 */
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
                    showRegistrationForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/listUser":
                    listUser(request, response);
                    break;
                case "/login":
                    loginUser(request, response);
                    break;
                case "/logout":
                    logoutUser(request, response);
                    break;
                case "/askForLogin":
                    askForLogin(request, response);
                    break;
                default:
                    askForLogin(request, response);
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

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            List<Complaint> listUser = service.findAll();
            request.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/user/UserList.jsp");
            dispatcher.forward(request, response);
    }

    private void showRegistrationForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/views/pages/userpages/registrationForm.jsp");
            request.setAttribute("types", UserType.values());
            request.setAttribute("isNew", true);
            dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                String id = request.getParameter("id");
                User existingUser = service.findById(id);
                RequestDispatcher dispatcher = request
                        .getRequestDispatcher("/pages/user/UserForm.jsp");
                request.setAttribute("types", UserType.values());
                request.setAttribute("user", existingUser);
                request.setAttribute("isEdit", true);
                dispatcher.forward(request, response);
            } catch (EntityNotFoundException e) {
                request.setAttribute("message", e.getMessage());
                listUser(request, response);
            }

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            User user = null;


            try {
                String id = UUID.randomUUID().toString();//A universally unique identifier (UUID) is a 128-bit number used to identify information in computer systems
                String userFirstName = request.getParameter("userfirstname");
                String userSecondName= request.getParameter("usersecondname");
                String userName      = request.getParameter("username");
                //String userPassword  = request.getParameter("userpassword");
                ProtectedConfigFile protectedConfigFile =
                        new ProtectedConfigFile(request.getParameter("userpassword"));
                String userPassword = protectedConfigFile.getEncryptedPassword();
                String stringUserAge = request.getParameter("userage");
                String userGender    = request.getParameter("usergender");
                String userEmail = request.getParameter("useremail");
                String userAddress   = request.getParameter("useraddress");
                String userType = request.getParameter("usertype");
                System.out.println("UserController.insertUser userType: " + userType);


                user = new User(id,
                        userFirstName,
                        userSecondName,
                        userName,
                        userPassword,
                        Integer.parseInt(stringUserAge),
                        userGender,
                        userEmail,
                        userAddress,
                        UserType.valueOf(userType));

                service.add(user);
                request.setAttribute("message", Message.buildSuccessMessage("User added successfully"));
                listUser(request, response);
            } catch (Exception e) {
                request.setAttribute("user", user);
                request.setAttribute("message", processException(e));
                request.setAttribute("isNew", true);
                RequestDispatcher dispatcher = request
                        .getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User user = null;
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
                user = new User();


                service.modify(user);
                request.setAttribute("message", Message.buildSuccessMessage("User updated successfully"));
                listUser(request, response);
            } catch (Exception e) {
                request.setAttribute("user", user);
                request.setAttribute("message", processException(e));
                request.setAttribute("isEdit", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/UserForm.jsp");
                dispatcher.forward(request, response);
            }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                String id = request.getParameter("id");
                service.remove(id);
                request.setAttribute("message", Message.buildSuccessMessage("User deleted successfully"));
            } catch (Exception e) {
                request.setAttribute("message", processException(e));
            }
            listUser(request, response);

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
                    showRegistrationForm(request, response);
                } catch (ServletException e1) {
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

