package domain;

public class Schedule {

    private String progressId;
    private String progressName;
    private User user = new User();
    private String progressType;
    private String progressPart;
    private String progressDescription;

    public Schedule() {
    }

    public Schedule(String progressId, String progressName) {
        this.progressId = progressId;
        this.progressName = progressName;
    }

    public Schedule(String progressId,
                    String progressName,
                    User user,
                    String progressType,
                    String progressPart,
                    String progressDescription) {
        this.progressId = progressId;
        this.progressName = progressName;
        user = user;
        this.progressType = progressType;
        this.progressPart = progressPart;
        this.progressDescription = progressDescription;
    }

    public String getScheduleId() {
        return progressId;
    }

    public void setScheduleId(String progressId) {
        this.progressId = progressId;
    }

    public String getScheduleName() {
        return progressName;
    }

    public void setScheduleName(String progressName) {
        this.progressName = progressName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getScheduleType() {
        return progressType;
    }

    public void setScheduleType(String progressType) {
        this.progressType = progressType;
    }

    public String getSchedulePart() {
        return progressPart;
    }

    public void setSchedulePart(String progressPart) {
        this.progressPart = progressPart;
    }

    public String getScheduleDescription() {
        return progressDescription;
    }

    public void setScheduleDescription(String progressDescription) {
        this.progressDescription = progressDescription;
    }
}
