package domain;

public class Complaint {

    private String complaintId;
    private User informer = new User();
    private String complaintType;
    private String complaintPart;
    private String complaintDescription;

    public Complaint() {    }

    public Complaint(User informer) {
        this.informer = informer;
    }

    public Complaint(String complaintId, User informer,String complaintType, String complaintPart, String complaintDescription) {
        this.complaintId = complaintId;
        this.informer = informer;
        this.complaintType = complaintType;
        this.complaintPart = complaintPart;
        this.complaintDescription = complaintDescription;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public User getInformer() {
        return informer;
    }

    public void setInformer(User informer) {
        this.informer = informer;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getComplaintPart() {
        return complaintPart;
    }

    public void setComplaintPart(String complaintPart) {
        this.complaintPart = complaintPart;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }
}
