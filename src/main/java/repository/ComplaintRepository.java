package repository;

import domain.*;
import exception.InfrastructureException;
import service.ComplaintPartService;
import service.ComplaintTypeService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ComplaintRepository extends BaseRepository<Complaint> {
    private static final String LOG_ERROR_MSG = "Error during the Complaint %s";

    public void add(Complaint newComplaint) {
        Connection conn = openConnection();
        try{
            log("Creating prepared statement-1...");
            String complaintsSQL = "INSERT INTO complaints VALUES (?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(complaintsSQL); // generates sql query
            pstmt.setString(1, newComplaint.getComplaintId());
            pstmt.setString(2, newComplaint.getComplaintPart().getComplaintPart_id());
            pstmt.setString(3, newComplaint.getComplaintDescription());
            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The insert wasn't executed!");
            }else{
                log("successfully inserted");
            }

        }catch(SQLException se){
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "insert"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "insert"),se);
        }finally{
            closeConnection(conn);
        }
        add_user_complaint(newComplaint);
    }

    public void add_user_complaint(Complaint newComplaint) {
        Connection conn = openConnection();
        try{
            log("Creating prepared statement-1...");
            String complaintsSQL = "INSERT INTO user_complaint VALUES (?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(complaintsSQL); // generates sql query
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, newComplaint.getInformer().getUserID());
            pstmt.setString(3, newComplaint.getComplaintId());
            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The insert wasn't executed!");
            }else{
                log("successfully inserted");
            }
        }catch(SQLException se){
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "insert"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "insert"),se);
        }finally{
            closeConnection(conn);
        }
    }

    public void modify(Complaint newComplaint){

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "UPDATE users SET user_firstname=?, user_secondname=?, user_username=?,user_password=?,"
                    + " user_age=?,user_gender=?,user_email=?,user_address=?, user_type = ? WHERE user_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query

//            pstmt.setString(1,user.getUserFirstName());


            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The Update wasn't executed!");
            } else{
                log("successfully modified");
            }
        }
        catch(SQLException se){
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "update"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "update"),se);
        }finally{
            closeConnection(conn);
        }
    }

    public void remove(Complaint newComplaint) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "DELETE FROM user_complaint WHERE complaint_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query
            pstmt.setString(1, newComplaint.getComplaintId());

            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The delete wasn't executed!");
            } else{
                log("successfully deleted");
            }
        }
        catch(SQLException se){
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "delete"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "delete"),se);
        }finally{
            closeConnection(conn);
        }
    }

    public Optional<Complaint> findById(String id){
        Complaint wantedComplaint= null;

        Connection conn = openConnection();

        try
        {
            log("Creating prepared statement...");
            String SQL = "SELECT * FROM complaints WHERE complaint_id =?";

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
                // from DB
                wantedComplaint= new Complaint(rs.getString("complaint_id")
                );
                return Optional.of(wantedComplaint);
            }
        }catch(SQLException se){
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "findById"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"),se);
        }finally{
            closeConnection(conn);
        }
        return Optional.empty();
    }

    public Optional<Complaint> findByCriteria(String field, String criteria){
        Complaint wantedComplaint= null;

        Connection conn = openConnection();

        try
        {
            log("Creating prepared statement...");
            String SQL = "SELECT * FROM complaints WHERE "+field+"=?";

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,criteria);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
                // from DB
//                wantedUser= new User(rs.getString("user_id"),
//                        rs.getString("user_firstname")
//                );
                return Optional.of(wantedComplaint);
            }
        }catch(SQLException se){
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "findById"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"), se);
        }finally{
            closeConnection(conn);
        }
        return Optional.empty();
    }

    public List<Complaint> findAll(){
        List<Complaint> complaints = new ArrayList<>();
        Connection conn = openConnection();
        try
        {
            log("Creating statement...");
            String SQL = "SELECT * FROM complaints";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next())
            {
                 //from DB
                complaints.add(
                        new Complaint(
                                rs.getString("complaint_id"),
                                new User(),
                                new ComplaintType(),
                                new ComplaintPart(),
                        "DESCRIPTION"
                                )
                        );
            }
        }
        catch(SQLException se){
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "findAll"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findAll"),se);
        }finally
        {
            closeConnection(conn);
        }
        return complaints;
    }

    public List<Complaint> findAllComplaintOfUser(User user){
        List<Complaint> userComplaints = new ArrayList<>();
        Connection conn = openConnection();
        Complaint complaint=null;
        String complaintID;
        String problematicPartId;
        ComplaintPartService complaintPartService = new ComplaintPartService();
        ComplaintTypeService complaintTypeService = new ComplaintTypeService();
        ComplaintPart complaintPart = null;
        ComplaintType complaintType = null;
        try
        {
            log("Creating statement..7.");
            String SQL = "SELECT * FROM user_complaint WHERE user_id =?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,user.getUserID());
            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                complaintID = rs.getString("complaint_id");
                problematicPartId =problematicPartId(complaintID);
                complaintPart = complaintPartService.findById(problematicPartId);
                complaintType = complaintTypeService.findById(complaintPart.getComplaintType_id());

                complaint =  new Complaint(complaintID,user,
                        complaintType,
                        complaintPart,
                        complaintPart.getComplaintPartDescription()
                );
                userComplaints.add(complaint);
            }
        }
        catch(SQLException se){
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "findAll"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findAll"),se);
        }finally
        {
            closeConnection(conn);
        }
        return userComplaints;
    }

    public String problematicPartId(String complaintId){
        Connection conn = openConnection();
        try
        {
            log("Creating prepared statement...");
            String SQL = "SELECT * FROM complaints WHERE complaint_id =?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,complaintId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) //while(rs.next())
            {
                // from DB
                return rs.getString("problematicpart_id");
            }
        }catch(SQLException se){
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "findById"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"),se);
        }finally{
            closeConnection(conn);
        }
        return null;
    }

    public List<ComplaintType> findProblemAllTypeList(){

        List<ComplaintType> problemTypes = new ArrayList<>();
        Connection conn = openConnection();

        try
        {
            log("Creating statement...");
            String SQL = "SELECT * FROM problemtypes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next())
            {
                // from DB
                problemTypes.add(new ComplaintType( rs.getString("problemtype_id"),
                                rs.getString("problemtype_shortname"),
                                rs.getString("problemtype_fullname"),
                                rs.getString("problemtype_description")
                        )
                );
            }
        }
        catch(SQLException se){
            log(se.getMessage());
        }finally {
            closeConnection(conn);
        }
        return problemTypes;
    }

    public List<ComplaintPart> findAllComplaintPartList() {
        List <ComplaintPart> complaintParts = new ArrayList<>();
        Connection conn = openConnection();
        try
        {
            log("Creating statement...");
            String SQL = "SELECT * FROM problematicparts";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next())
            {
                // from DB
                complaintParts.add(new ComplaintPart( rs.getString("problematicpart_id"),
                                rs.getString("problemtype_id"),
                                rs.getString("problematicpart_name"),
                                rs.getString("problematicpart_description")
                        )
                );
            }
        }
        catch(SQLException se){
            log(se.getMessage());
        }finally {
            closeConnection(conn);
        }
        return complaintParts;
    }
}