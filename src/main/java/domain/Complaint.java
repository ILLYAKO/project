package domain;

public class Complaint {

    private String complaintId;
    private User informer = new User();
    private ComplaintType complaintType;
    private String complaintTypeShortName;
    private ComplaintPart complaintPart;
    private String complaintPartName;
    private String complaintDescription;

    public Complaint() {    }

    public Complaint(User informer) {
        this.informer = informer;
    }

    public Complaint(User informer,String complaintTypeShortName, String complaintPartName,
                     String complaintDescription) {
        this.informer = informer;
        this.complaintTypeShortName = complaintTypeShortName;
        this.complaintPartName = complaintPartName;
        this.complaintDescription = complaintDescription;
    }

    public Complaint(String complaintId, User informer,String complaintTypeShortName, String complaintPartName,
                     String complaintDescription) {
        this.complaintId = complaintId;
        this.informer = informer;
        this.complaintTypeShortName = complaintTypeShortName;
        this.complaintPartName = complaintPartName;
        this.complaintDescription = complaintDescription;
    }

    public Complaint(String complaintId, User informer, ComplaintType complaintType,
                     ComplaintPart complaintPart, String complaintDescription) {
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

    public ComplaintType getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(ComplaintType complaintType) {
        this.complaintType = complaintType;
    }

    public String getComplaintTypeShortName() {
        return complaintTypeShortName;
    }

    public void setComplaintTypeShortName(String complaintTypeShortName) {
        this.complaintTypeShortName = complaintTypeShortName;
    }

    public ComplaintPart getComplaintPart() {
        return complaintPart;
    }

    public void setComplaintPart(ComplaintPart complaintPart) {
        this.complaintPart = complaintPart;
    }

    public String getComplaintPartName() {
        return complaintPartName;
    }

    public void setComplaintPartName(String complaintPartName) {
        this.complaintPartName = complaintPartName;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }
}
