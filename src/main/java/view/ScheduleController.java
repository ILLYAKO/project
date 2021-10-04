package view;

import domain.Schedule;
import domain.User;
import exception.EntityNotFoundException;
import service.ScheduleService;
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

public class ScheduleController extends BaseController {
    private Service<Schedule> service;

    public void init() {
        service = new ScheduleService();
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
                    showScheduleForm(request, response);
                    break;
                case "/insert":
                    insertSchedule(request, response);
                    break;
                case "/delete":
                    deleteSchedule(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateSchedule(request, response);
                    break;
                case "/listSchedule":
                    listSchedule(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/pages/schedulepages/schedulelist.jsp");
        dispatcher.forward(request, response);
    }
    private void listSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Schedule> listSchedule = service.findAll();
        request.setAttribute("listSchedule", listSchedule);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/views/pages/schedulepages/scheduleList.jsp");
        dispatcher.forward(request, response);
    }

    private void showScheduleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/views/pages/schedulepages/scheduleForm.jsp");
        request.setAttribute("isNew", true);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("scheduleId");
            Schedule existingSchedule = service.findById(id);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/views/pages/schedulepages/scheduleForm.jsp");
            dispatcher.forward(request, response);
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            listSchedule(request, response);
        }
    }

    private void insertSchedule(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Schedule schedule = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //A universally unique identifier (UUID) is a 128-bit number used to identify information in computer systems
        String scheduleId = UUID.randomUUID().toString();
        String scheduleName = request.getParameter("scheduleName");
        String scheduleType = request.getParameter("scheduleType");
        String schedulePart = request.getParameter("schedulePart");
        String scheduleDescription = request.getParameter("scheduleDescription");

        if (scheduleName.isEmpty() || schedulePart.isEmpty() || scheduleDescription.isEmpty()) {
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("/views/pages/schedulepages/scheduleList.jsp");
            rd.forward(request, response);
        } else {
            Schedule newSchedule = new Schedule(
                    scheduleId,
                    scheduleName,
                    user,
                    scheduleType,
                    schedulePart,
                    scheduleDescription);
            ScheduleService scheduleService = new ScheduleService();
            scheduleService.add(newSchedule);
            listSchedule(request, response);
        }
    }

    private void updateSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Schedule schedule = null;
        try {
            String id = request.getParameter("id");
            String scheduleName = request.getParameter("scheduleName");
            String scheduleType = request.getParameter("scheduleType");
            String schedulePart = request.getParameter("schedulePart");
            schedule = new Schedule();
            service.modify(schedule);
            request.setAttribute("message", Message.buildSuccessMessage("Schedule updated successfully"));
            listSchedule(request, response);
        } catch (Exception e) {
            request.setAttribute("schedule", schedule);
            request.setAttribute("message", processException(e));
            request.setAttribute("isEdit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/pages/schedulepages/scheduleList.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("scheduleId");
            service.remove(id);
            request.setAttribute("message", Message.buildSuccessMessage("User deleted successfully"));
        } catch (Exception e) {
            request.setAttribute("message", processException(e));
        }
        listSchedule(request, response);
    }

}