package view;

import domain.Progress;
import domain.Progress;
import domain.Progress;
import domain.User;
import exception.EntityNotFoundException;
import service.ProgressService;
import service.ProgressService;
import service.ProtectedConfigFile;
import service.Service;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class ProgressController extends BaseController {
    private Service<Progress> service;

    public void init() {
        service = new ProgressService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = extractAction(request);
            switch (action) {
                case "/add":
                    showRegistrationForm(request, response);
                    break;
                case "/insert":
                    insertProgress(request, response);
                    break;
                case "/delete":
                    deleteProgress(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateProgress(request, response);
                    break;
                case "/listProgress":
                    listProgress(request, response);
                    break;
                default:
                    ask(request, response);
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

    private void ask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/pages/progresspages/progressbar.jsp");
        dispatcher.forward(request, response);
    }

    private void listProgress(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Progress> listProgress = service.findAll();
        request.setAttribute("listProgress", listProgress);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/pages/progress/ProgressList.jsp");
        dispatcher.forward(request, response);
    }

    private void showRegistrationForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Progress progress = new Progress();
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/views/pages/progresspages/registrationForm.jsp");
        request.setAttribute("types", progress.getProgressType());
        request.setAttribute("isNew", true);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Progress existingProgress = service.findById(id);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/pages/progress/ProgressForm.jsp");
            request.setAttribute("types", existingProgress.getProgressType());
            request.setAttribute("progress", existingProgress);
            request.setAttribute("isEdit", true);
            dispatcher.forward(request, response);
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            listProgress(request, response);
        }
    }

    private void insertProgress(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Progress progress = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //A universally unique identifier (UUID) is a 128-bit number used to identify information in computer systems
        String progressId = UUID.randomUUID().toString();
        String progressName = request.getParameter("progressName");
        String progressType = request.getParameter("progressType");
        String progressPart = request.getParameter("progressPart");
        String progressDescription = request.getParameter("progressDescription");

        if (progressName.isEmpty() || progressPart.isEmpty() || progressDescription.isEmpty()) {
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("/views/pages/progresspages/progressList.jsp");
            rd.forward(request, response);
        } else {
            Progress newProgress = new Progress(
                    progressId,
                    progressName,
                    user,
                    progressType,
                    progressPart,
                    progressDescription);
            ProgressService progressService = new ProgressService();
            progressService.add(newProgress);
            listProgress(request, response);
        }
    }

    private void updateProgress(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Progress progress = null;
        try {
            String id = request.getParameter("id");
            String progressName = request.getParameter("progressName");
            String progressType = request.getParameter("progressType");
            String progressPart = request.getParameter("progressPart");
            progress = new Progress();
            service.modify(progress);
            request.setAttribute("message", Message.buildSuccessMessage("Progress updated successfully"));
            listProgress(request, response);
        } catch (Exception e) {
            request.setAttribute("progress", progress);
            request.setAttribute("message", processException(e));
            request.setAttribute("isEdit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/pages/progresspages/progressList.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteProgress(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.remove(id);
            request.setAttribute("message", Message.buildSuccessMessage("Progress deleted successfully"));
        } catch (Exception e) {
            request.setAttribute("message", processException(e));
        }
        listProgress(request, response);
    }
}