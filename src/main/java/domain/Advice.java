package domain;

public class Advice {

    private String adviceId;
    private String adviceName;
    private User Advisor = new User();
    private String adviceType;
    private String advicePart;
    private String adviceDescription;

    public Advice() {    }

    public Advice(String adviceId, String adviceName) {
        this.adviceId = adviceId;
        this.adviceName = adviceName;
    }

    public Advice(String adviceId, String adviceName, User advisor, String adviceType, String advicePart, String adviceDescription) {
        this.adviceId = adviceId;
        this.adviceName = adviceName;
        Advisor = advisor;
        this.adviceType = adviceType;
        this.advicePart = advicePart;
        this.adviceDescription = adviceDescription;
    }

    public String getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(String adviceId) { this.adviceId = adviceId; }

    public String getAdviceName() {
        return adviceName;
    }

    public void setAdviceName(String adviceName) {
        this.adviceName = adviceName;
    }

    public User getAdvisor() {
        return Advisor;
    }

    public void setAdvisor(User advisor) {
        Advisor = advisor;
    }

    public String getAdviceType() {
        return adviceType;
    }

    public void setAdviceType(String adviceType) {
        this.adviceType = adviceType;
    }

    public String getAdvicePart() {
        return advicePart;
    }

    public void setAdvicePart(String advicePart) {
        this.advicePart = advicePart;
    }

    public String getAdviceDescription() {
        return adviceDescription;
    }

    public void setAdviceDescription(String adviceDescription) {
        this.adviceDescription = adviceDescription;
    }
}
