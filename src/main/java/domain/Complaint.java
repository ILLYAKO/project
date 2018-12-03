package domain;

public class Complaint {

    private User informer = new User();
    private String problemType;
    private String problemPart;
    private String problemDescription;

    public Complaint() {    }

    public Complaint(User informer) {
        this.informer = informer;
    }

    public Complaint(User informer,String problemType, String problemPart, String problemDescription) {
        this.informer = informer;
        this.problemType = problemType;
        this.problemPart = problemPart;
        this.problemDescription = problemDescription;
    }
}
