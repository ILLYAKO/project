package repository;

import domain.Progress;
import domain.ComplaintPart;
import domain.ComplaintType;
import domain.User;
import exception.InfrastructureException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgressRepository extends BaseRepository<Progress> {
    private static final String LOG_ERROR_MSG = "Error during the Progress %s";

    public void add(Progress newProgress) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "INSERT INTO Progresses VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query
            pstmt.setString(1, newProgress.getProgressId());
            pstmt.setString(2,newProgress.getProgressName());
            pstmt.setString(3,newProgress.getProgressType());
            pstmt.setString(4,newProgress.getProgressPart());
            pstmt.setString(5,newProgress.getProgressDescription());

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

    public void modify(Progress newProgress){

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "UPDATE users SET user_firstname=?, user_secondname=?, user_username=?,user_password=?,"
                    + " user_age=?,user_gender=?,user_email=?,user_address=?, user_type = ? WHERE user_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query

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

    public void remove(Progress newProgress) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "DELETE FROM users WHERE user_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query

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

    public Optional<Progress> findById(String id){

        Progress wantedProgress= null;

        Connection conn = openConnection();

        try
        {
            log("Creating prepared statement...");
            String SQL = "SELECT * FROM users WHERE user_id =?";

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) //while(rs.next())
            {

                return Optional.of(wantedProgress);
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

    public Optional<Progress> findByCriteria(String field, String criteria){

        Progress wantedProgress= null;

        Connection conn = openConnection();

        try
        {
            log("Creating prepared statement...");
            String SQL = "SELECT * FROM users WHERE "+field+"=?";

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,criteria);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) //while(rs.next())
            {
                return Optional.of(wantedProgress);
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

    public List<Progress> findAll(){
        List<Progress> Progresses = new ArrayList<>();

        Connection conn = openConnection();

        try
        {
            log("Creating statement...");
            String SQL = "SELECT * FROM Progresses";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next())
            {
                 //from DB
                Progresses.add(
                        new Progress(
                                rs.getString("Progress_id"),
                                rs.getString("Progress_name"),
                                new User(),
                                rs.getString("Progress_type"),
                                rs.getString("Progress_part"),
                                rs.getString("Progress_description")
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
        return Progresses;
    }

    @Override
    public List<Progress> findAllComplaintOfUser(User user) {
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


    public List<ComplaintPart> findAllProgressPartList() {
        List <ComplaintPart> ProgressParts = new ArrayList<>();
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
                ProgressParts.add(new ComplaintPart( rs.getString("problematicpart_id"),
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
        return ProgressParts;
    }
}