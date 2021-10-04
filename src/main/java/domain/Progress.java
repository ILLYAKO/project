package domain;

public class Progress {

    private String progressId;
    private String progressName;
    private User user = new User();
    private String progressType;
    private String progressPart;
    private String progressDescription;

    public Progress() {
    }

    public Progress(String progressId, String progressName) {
        this.progressId = progressId;
        this.progressName = progressName;
    }

    public Progress(String progressId,
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

    public String getProgressId() {
        return progressId;
    }

    public void setProgressId(String progressId) {
        this.progressId = progressId;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProgressType() {
        return progressType;
    }

    public void setProgressType(String progressType) {
        this.progressType = progressType;
    }

    public String getProgressPart() {
        return progressPart;
    }

    public void setProgressPart(String progressPart) {
        this.progressPart = progressPart;
    }

    public String getProgressDescription() {
        return progressDescription;
    }

    public void setProgressDescription(String progressDescription) {
        this.progressDescription = progressDescription;
    }
}
