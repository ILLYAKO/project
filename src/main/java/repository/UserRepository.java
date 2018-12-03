package repository;

import domain.User;
import domain.UserType;
import exception.InfrastructureException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends BaseRepository<User> {

    private static final String LOG_ERROR_MSG = "Error during the user %s";

    /**
     * Add User
     * @param user
     */
    public void add(User user) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query
            pstmt.setString(1, user.getUserID());
            pstmt.setString(2,user.getUserFirstName());
            pstmt.setString(3,user.getUserSecondName());
            pstmt.setString(4,user.getUserName());
            pstmt.setString(5,user.getUserPassword());
            pstmt.setString(6,Integer.toString(user.getUserAge()));
            pstmt.setString(7,user.getUserGender());
            pstmt.setString(8,user.getUserEmail());
            pstmt.setString(9,user.getUserAddress());
            pstmt.setString(10, user.getUserType().name());

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
     * Modify User
     * @param user
     * @throws Exception
     */
    public void modify(User user){

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "UPDATE users SET user_firstname=?, user_secondname=?, user_username=?,user_password=?,"
                    + " user_age=?,user_gender=?,user_email=?,user_address=?, user_type = ? WHERE user_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query

            pstmt.setString(1,user.getUserFirstName());
            pstmt.setString(2,user.getUserSecondName());
            pstmt.setString(3,user.getUserName());
            pstmt.setString(4,user.getUserPassword());
            pstmt.setString(5,Integer.toString(user.getUserAge()));
            pstmt.setString(6,user.getUserGender());
            pstmt.setString(7,user.getUserEmail());
            pstmt.setString(8,user.getUserAddress());
            pstmt.setString(9,user.getUserType().name());
            pstmt.setString(10, user.getUserID());

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

    /**
     * Remove User
     * @param user
     * @throws Exception
     */
    public void remove(User user) {

        Connection conn = openConnection();

        try{
            log("Creating prepared statement...");
            String SQL = "DELETE FROM users WHERE user_id= ?";

            PreparedStatement pstmt = conn.prepareStatement(SQL); // generates sql query
            pstmt.setString(1, user.getUserID());

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

    /**
     * Find User By ID
     * @param id
     * @return
     */
    public Optional<User> findById(String id){

        User wantedUser = null;

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
                wantedUser= new User(rs.getString("user_id"),
                        rs.getString("user_firstname"),
                        rs.getString("user_secondname"),
                        rs.getString("user_username"),
                        rs.getString("user_password"),
                        Integer.parseInt(rs.getString("user_age")),
                        rs.getString("user_gender"),
                        rs.getString("user_email"),
                        rs.getString("user_address"),
                        UserType.valueOf(rs.getString("user_type"))
                );
            return Optional.of(wantedUser);
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

    /**
     * Find User By Criteria
     * @param criteria
     * @return
     */
    public Optional<User> findByCriteria(String field, String criteria){

        User wantedUser = null;

        Connection conn = openConnection();

        try
        {
            log("Creating prepared statement...");
            //String SQL = "SELECT * FROM users WHERE user_username =?";
            String SQL = "SELECT * FROM users WHERE "+field+"=?";

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,criteria);

            ResultSet rs = pstmt.executeQuery();
            System.out.println("UserRepository.findByCriteria -01");

            if(rs.next()) //while(rs.next())
            {
                // from DB
                System.out.println("UserRepository.findByCriteria -02");
                wantedUser= new User(rs.getString("user_id"),
                                    rs.getString("user_firstname"),
                                    rs.getString("user_secondname"),
                                    rs.getString("user_username"),
                                    rs.getString("user_password"),
                                    Integer.parseInt(rs.getString("user_age")),
                                    rs.getString("user_gender"),
                                    rs.getString("user_email"),
                                    rs.getString("user_address"),
                                    UserType.valueOf(rs.getString("user_type"))
                );
                System.out.println("UserRepository.findByCriteria -03");
                System.out.println("UserRepository.findByCriteria wantedUser.getUserEmail: " + wantedUser.getUserEmail());

                return Optional.of(wantedUser);
            }
        }catch(SQLException se){
            System.out.println("UserRepository.findByCriteria -04");
            log(se.getMessage());
            logger.error(String.format(LOG_ERROR_MSG, "findById"), se);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"), se);
        }finally{
            System.out.println("UserRepository.findByCriteria -05");
            closeConnection(conn);
        }
        System.out.println("UserRepository.findByCriteria -06");
        return Optional.empty();
    }



    /**
     * Find All Users
     * @return
     */
    public List<User> findAll(){
        List<User> users = new ArrayList<>();

        Connection conn = openConnection();

        try
        {
            log("Creating statement...");
            String SQL = "SELECT * FROM users";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next())
            {
                // from DB
                users.add(new User( rs.getString("user_id"),
                                    rs.getString("user_firstname"),
                                    rs.getString("user_secondname"),
                                    rs.getString("user_username"),
                                    rs.getString("user_password"),
                                    Integer.parseInt(rs.getString("user_age")),
                                    rs.getString("user_gender"),
                                    rs.getString("user_email"),
                                    rs.getString("user_address"),
                                    UserType.valueOf(rs.getString("user_type"))
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
        return users;
    }
}