package domain;

public class ComplaintPart {

    private String complaintPart_id;
    private String complaintType_id;
    private String complaintPartName;
    private String complaintPartDescription;

    public ComplaintPart(){}

    public ComplaintPart(String complaintPart_id,
                         String complaintType_id,
                         String complaintPartName,
                         String complaintPartDescription) {
        this.complaintPart_id = complaintPart_id;
        this.complaintType_id = complaintType_id;
        this.complaintPartName = complaintPartName;
        this.complaintPartDescription = complaintPartDescription;
    }

    public String getComplaintPart_id() {
        return complaintPart_id;
    }

    public void setComplaintPart_id(String complaintPart_id) {
        this.complaintPart_id = complaintPart_id;
    }

    public String getComplaintType_id() {
        return complaintType_id;
    }

    public void setComplaintType_id(String complaintType_id) {
        this.complaintType_id = complaintType_id;
    }

    public String getComplaintPartName() {
        return complaintPartName;
    }

    public void setComplaintPartName(String complaintPartName) {
        this.complaintPartName = complaintPartName;
    }

    public String getComplaintPartDescription() {
        return complaintPartDescription;
    }

    public void setComplaintPartDescription(String complaintPartDescription) {
        this.complaintPartDescription = complaintPartDescription;
    }
}
