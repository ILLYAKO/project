package view;

import domain.Advice;
import domain.Complaint;
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
        System.out.println("-AdviceController.listAdvice-");
//            HttpSession session = request.getSession();
//            User user = (User)session.getAttribute("user");

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
           // request.setAttribute("types", AdviceType.getAdviceTypeFullName());
            request.setAttribute("isNew", true);
            dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                String id = request.getParameter("adviceId");
                Advice existingAdvice = service.findById(id);
                RequestDispatcher dispatcher = request
                        .getRequestDispatcher("/pages/advicepages/adviceForm.jsp");
                //request.setAttribute("types", UserType.values());
                //request.setAttribute("user", existingAdvice);
                //request.setAttribute("isEdit", true);
                dispatcher.forward(request, response);
            } catch (EntityNotFoundException e) {
                request.setAttribute("message", e.getMessage());
                listAdvice(request, response);
            }

    }

    private void insertAdvice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Advice advice = null;
        System.out.println("--insertAdvice--");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println("--AdviceController.insertAdvice user: " + user.getUserFirstName());
        //A universally unique identifier (UUID) is a 128-bit number used to identify information in computer systems
        String adviceId = UUID.randomUUID().toString();
        String adviceName =   request.getParameter("adviceName");
        String adviceType = request.getParameter("adviceType");
        String advicePart =        request.getParameter("advicePart");
        String adviceDescription = request.getParameter("adviceDescription");

        if(adviceName.isEmpty() || advicePart.isEmpty() || adviceDescription.isEmpty()){
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("/views/pages/advicepages/adviceList.jsp");//adviceForm.jsp

            //out.println("<font color=red>Please fill all the fields</font>");
            rd.forward(request, response);
        }else{
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

    private void updateAdvice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Advice advice = null;
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

    private void deleteAdvice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

