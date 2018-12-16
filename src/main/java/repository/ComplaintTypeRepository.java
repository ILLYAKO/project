package repository;

import domain.ComplaintType;
import domain.User;
import exception.InfrastructureException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComplaintTypeRepository extends BaseRepository<ComplaintType> {

    private static final String LOG_ERROR_MSG = "Error during the complaintType %s";

    public void add(ComplaintType complaintType) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "INSERT INTO problemtypes VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query
            pstmt.setString(1, complaintType.getComplaintTypeID());
            pstmt.setString(2,complaintType.getComplaintTypeShortName());
            pstmt.setString(3,complaintType.getComplaintTypeFullName());
            pstmt.setString(4,complaintType.getComplaintTypeDescription());

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

    public void modify(ComplaintType complaintType){

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "UPDATE problemtypes SET problemtype_shortname=?, problemtype_fullname=?, " +
                                                "problemtype_description=? WHERE problemtype_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query

            pstmt.setString(1,complaintType.getComplaintTypeShortName());
            pstmt.setString(2,complaintType.getComplaintTypeFullName());

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

    public void remove(ComplaintType complaintType) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "DELETE FROM problemtypes WHERE problemtype_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query
            pstmt.setString(1, complaintType.getComplaintTypeID());

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

    public Optional<ComplaintType> findById(String id){

        ComplaintType wantedComplaintType = null;

        Connection conn = openConnection();

        try
        {
            log("Creating prepared statement...");
            String SQL = "SELECT * FROM problemtypes WHERE problemtype_id =?";

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) //while(rs.next())
            {
                // from DB
                wantedComplaintType= new ComplaintType(rs.getString("problemtype_id"),
                        rs.getString("problemtype_shortname"),
                        rs.getString("problemtype_fullname"),
                        rs.getString("problemtype_description")
                );
            return Optional.of(wantedComplaintType);
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

    public Optional<ComplaintType> findByCriteria(String field, String criteria){

        ComplaintType wantedComplaintType = null;

        Connection conn = openConnection();

        try
        {
            log("Creating prepared statement...");
            String SQL = "SELECT * FROM problemtypes WHERE "+field+"=?";

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,criteria);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
                // from DB
                wantedComplaintType= new ComplaintType(rs.getString("problemtype_id"),
                                    rs.getString("problemtype_shortname"),
                                    rs.getString("problemtype_fullname"),
                                    rs.getString("problemtype_description")
                );
                return Optional.of(wantedComplaintType);
            }
        }catch(SQLException se){
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "findByCriteria"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findByCriteria"), se);
        }finally{
            closeConnection(conn);
        }
        return Optional.empty();
    }

    public List<ComplaintType> findAll(){
        List<ComplaintType> complaintTypes = new ArrayList<>();

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
                complaintTypes.add(new ComplaintType(
                        rs.getString("problemtype_id"),
                        rs.getString("problemtype_shortname"),
                        rs.getString("problemtype_fullname"),
                        rs.getString("problemtype_description")
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
        return complaintTypes;
    }

    @Override
    public List<ComplaintType> findAllComplaintOfUser(User user) {
        return null;
    }
}