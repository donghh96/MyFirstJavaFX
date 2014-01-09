/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyFirstJavaFX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author huidong
 */
public class DataBase {
    
    static Connection conn;
    static Statement st;
    static ArrayList<User> users = new ArrayList<User>();
    
    public static ArrayList<User> getUsers() {
        conn = getConnection();
        String sql = "select * from user";
        
        try{
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                System.out.println(name + " " + password);

                users.add(new User(name, password));              
            } 
            conn.close();
        }catch(Exception e) {
            System.out.println("Query DB error" + e.getMessage());
        }
        return users;
    }
    
    public static Connection getConnection() {
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/study", "root", "iforgot");
        }catch(Exception e){
            System.out.println("DB connection error" + e.getMessage());
        }        
        return con;
    }
}
