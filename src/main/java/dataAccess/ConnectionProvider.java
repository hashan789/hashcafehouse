package dataAccess;
import java.sql.*;

import static java.lang.Class.forName;

public class ConnectionProvider {

    public static Connection getCon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafe?useSSL=false","root","hrytE#78N");
            return con;

        }
        catch(Exception e){
            return null;
        }
    }
}
