package repository;

import domain.Advice;
// import domain.AdvicePart;
// import domain.AdviceType;
import domain.ComplaintPart;
import domain.ComplaintType;
import domain.User;
import exception.InfrastructureException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdviceRepository extends BaseRepository<Advice> {
    private static final String LOG_ERROR_MSG = "Error during the Advice %s";

    /**
     * add
     * @param newAdvice
     */
    public void add(Advice newAdvice) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "INSERT INTO advices VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query
            pstmt.setString(1, newAdvice.getAdviceId());
            pstmt.setString(2,newAdvice.getAdviceName());
            pstmt.setString(3,newAdvice.getAdviceType());
            pstmt.setString(4,newAdvice.getAdvicePart());
            pstmt.setString(5,newAdvice.getAdviceDescription());
//            pstmt.setString(6,Integer.toString(user.getUserAge()));
//            pstmt.setString(7,newAdvice.getUserGender());
//            pstmt.setString(8,newAdvice.getUserEmail());
//            pstmt.setString(9,newAdvice.getUserAddress());

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

    /**
     * modify
     * @param newAdvice
     */
    public void modify(Advice newAdvice){

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "UPDATE users SET user_firstname=?, user_secondname=?, user_username=?,user_password=?,"
                    + " user_age=?,user_gender=?,user_email=?,user_address=?, user_type = ? WHERE user_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query

//            pstmt.setString(1,user.getUserFirstName());
//            pstmt.setString(2,user.getUserSecondName());
//            pstmt.setString(3,user.getUserName());
//            pstmt.setString(4,user.getUserPassword());
//            pstmt.setString(5,Integer.toString(user.getUserAge()));
//            pstmt.setString(6,user.getUserGender());
//            pstmt.setString(7,user.getUserEmail());
//            pstmt.setString(8,user.getUserAddress());
//            pstmt.setString(9,user.getUserType().name());
//            pstmt.setString(10, user.getUserID());

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

    public void remove(Advice newAdvice) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "DELETE FROM users WHERE user_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query
            //pstmt.setString(1, user.getUserID());

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

    public Optional<Advice> findById(String id){

        //User wantedUser = null;
        Advice wantedAdvice= null;

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
                // from DB
//                wantedUser= new User(rs.getString("user_id"),
//                        rs.getString("user_firstname"),
//                        rs.getString("user_secondname"),
//                        rs.getString("user_username"),
//                        rs.getString("user_password"),
//                        Integer.parseInt(rs.getString("user_age")),
//                        rs.getString("user_gender"),
//                        rs.getString("user_email"),
//                        rs.getString("user_address"),
//                        UserType.valueOf(rs.getString("user_type"))
//                );
                return Optional.of(wantedAdvice);
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

    public Optional<Advice> findByCriteria(String field, String criteria){

        //User wantedUser = null;
        Advice wantedAdvice= null;

        Connection conn = openConnection();

        try
        {
            log("Creating prepared statement...");
            //String SQL = "SELECT * FROM users WHERE user_username =?";
            String SQL = "SELECT * FROM users WHERE "+field+"=?";

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,criteria);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) //while(rs.next())
            {
                // from DB
//                wantedUser= new User(rs.getString("user_id"),
//                        rs.getString("user_firstname"),
//                        rs.getString("user_secondname"),
//                        rs.getString("user_username"),
//                        rs.getString("user_password"),
//                        Integer.parseInt(rs.getString("user_age")),
//                        rs.getString("user_gender"),
//                        rs.getString("user_email"),
//                        rs.getString("user_address"),
//                        UserType.valueOf(rs.getString("user_type"))
//                );
                return Optional.of(wantedAdvice);
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

    public List<Advice> findAll(){
        List<Advice> advices = new ArrayList<>();

        Connection conn = openConnection();

        try
        {
            log("Creating statement...");
            String SQL = "SELECT * FROM advices";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next())
            {
                 //from DB
                advices.add(
                        new Advice(
                                rs.getString("advice_id"),
                                rs.getString("advice_name"),
                                new User(),//----------------wrong------------------
                                rs.getString("advice_type"),
                                rs.getString("advice_part"),
                                rs.getString("advice_description")
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
        return advices;
    }








    /**
     * Find All Problem Types
     * @return problemTypes
     */
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


    public List<ComplaintPart> findAllAdvicePartList() {
        List <ComplaintPart> adviceParts = new ArrayList<>();
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
                adviceParts.add(new ComplaintPart( rs.getString("problematicpart_id"),
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
        return adviceParts;
    }




}