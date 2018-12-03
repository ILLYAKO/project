package domain;

public class ComplaintType {

    private String complaintTypeID;
    private String complaintTypeShortName;
    private String complaintTypeFullName;
    private String complaintTypeDescription;

    public ComplaintType() {    }

    public ComplaintType(String complaintTypeID, String complaintTypeShortName,String complaintTypeFullName, String complaintTypeDescription) {
        this.complaintTypeID = complaintTypeID;
        this.complaintTypeShortName = complaintTypeShortName;
        this.complaintTypeFullName = complaintTypeFullName;
        this.complaintTypeDescription = complaintTypeDescription;
    }

    public String getComplaintTypeID() {
        return complaintTypeID;
    }

    public void setComplaintTypeID(String complaintTypeID) {
        this.complaintTypeID = complaintTypeID;
    }

    public String getComplaintTypeShortName() {
        return complaintTypeShortName;
    }

    public void setComplaintTypeShortName(String complaintTypeShortName) {
        this.complaintTypeShortName = complaintTypeShortName;
    }

    public String getComplaintTypeFullName() {
        return complaintTypeFullName;
    }

    public void setComplaintTypeFullName(String complaintTypeFullName) {
        this.complaintTypeFullName = complaintTypeFullName;
    }

    public String getComplaintTypeDescription() {
        return complaintTypeDescription;
    }

    public void setComplaintTypeDescription(String complaintTypeDescription) {
        this.complaintTypeDescription = complaintTypeDescription;
    }
}
