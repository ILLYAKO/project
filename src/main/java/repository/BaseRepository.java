package repository;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseRepository<T> implements Repository<T> {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/healthyhabits";
    private static final String DEFAULT_USER = "root";
    private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : DEFAULT_USER;
    private static final String DEFAULT_PASS = "admin";
    private static final String PASS = System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : DEFAULT_USER;


    final static Logger logger = Logger.getLogger(BaseRepository.class);

    protected void closeConnection(Connection conn){
        try {
            //log("Goodbye!");
            conn.close();
        }catch (SQLException se){
            log("Problems during close connection");
            logger.error("Problems during closeConnection connection", se);
        }
    }

    protected Connection openConnection() {
        //log("Connecting to database...");
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER , PASS);

            if (connection == null) {
                log("Failed to make connection!");
                logger.error("Failed to make connection!");
            }else {
                //log("Connection Successful! Enjoy. Now it's time to use data");
            }

        } catch (SQLException se) {
            log("MySQL Connection Failed!");
            se.printStackTrace(); //It prints the standard error stream.
            logger.error("MySQL Connection Failed!", se);
            return null;
        }
        return connection;
    }

    // Simple log utility
    protected  void log(String string){System.out.println(string);}
}
