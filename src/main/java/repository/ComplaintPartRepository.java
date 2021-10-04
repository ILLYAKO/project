package repository;

import domain.ComplaintPart;
import domain.User;
import exception.InfrastructureException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComplaintPartRepository extends BaseRepository<ComplaintPart> {

    private static final String LOG_ERROR_MSG = "Error during the complaintPart %s";

    public void add(ComplaintPart complaintPart) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "INSERT INTO problematicparts VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query
            pstmt.setString(1, complaintPart.getComplaintPart_id());
            pstmt.setString(2,complaintPart.getComplaintType_id());
            pstmt.setString(3,complaintPart.getComplaintPartName());
            pstmt.setString(4,complaintPart.getComplaintPartDescription());

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

    public void modify(ComplaintPart complaintPart){

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "UPDATE problematicparts SET problemtype_id=?, problematicpart_name=?," +
                    " problematicpart_description=?, WHERE problematicpart_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query

            pstmt.setString(1,complaintPart.getComplaintType_id());
            pstmt.setString(2,complaintPart.getComplaintPartName());
            pstmt.setString(3,complaintPart.getComplaintPartDescription());
            pstmt.setString(4, complaintPart.getComplaintPart_id());

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

    public void remove(ComplaintPart complaintPart) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "DELETE FROM problematicparts WHERE problematicpart_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query
            pstmt.setString(1, complaintPart.getComplaintPart_id());

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

    public Optional<ComplaintPart> findById(String id){

        ComplaintPart wantedComplaintPart = null;

        Connection conn = openConnection();

        try
        {
            log("Creating prepared statement...");
            String SQL = "SELECT * FROM problematicparts WHERE problematicpart_id =?";

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) //while(rs.next())
            {
                // from DB
                wantedComplaintPart= new ComplaintPart(
                        rs.getString("problematicpart_id"),
                        rs.getString("problemtype_id"),
                        rs.getString("problematicpart_name"),
                        rs.getString("problematicpart_description")
                );
            return Optional.of(wantedComplaintPart);
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

    public Optional<ComplaintPart> findByCriteria(String field, String criteria){

        ComplaintPart wantedComplaintPart = null;

        Connection conn = openConnection();

        try
        {
            log("Creating prepared statement...");
            String SQL = "SELECT * FROM problematicparts WHERE " + field + "=?";

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,criteria);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) //while(rs.next())
            {
                // from DB
                wantedComplaintPart= new ComplaintPart(
                        rs.getString("problematicpart_id"),
                        rs.getString("problemtype_id"),
                        rs.getString("problematicpart_name"),
                        rs.getString("problematicpart_description")
                );

                return Optional.of(wantedComplaintPart);
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

    public List<ComplaintPart> findAll(){
        List<ComplaintPart> complaintParts = new ArrayList<>();

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
                complaintParts.add(
                        new ComplaintPart(
                            rs.getString("problematicpart_id"),
                            rs.getString("problemtype_id"),
                            rs.getString("problematicpart_name"),
                            rs.getString("problematicpart_description")
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
        return complaintParts;
    }

    @Override
    public List<ComplaintPart> findAllComplaintOfUser(User user) {
        return null;
    }
}