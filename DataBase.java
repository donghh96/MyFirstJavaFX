/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyFirstJavaFX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author huidong
 */
public class DataBase {
    
    static Connection conn;
    static ArrayList<User> users = new ArrayList<User>();
    
    public static ArrayList<User> getUsers() {
        conn = getConnection();
        users.clear();
        
        try{
            PreparedStatement pst = conn.prepareStatement("select * from user");
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                System.out.println(name + " " + password);

                users.add(new User(name, password));              
            } 
        }catch(Exception e) {
            System.out.println("Query DB error" + e.getMessage());
        }finally{
            try{
                conn.close();
            }catch(Exception e){
                System.out.println("Close DB connection error" + e.getMessage());
            }
        }
        return users;
    }
    
    public static void addUser(String name, String password) {
        conn = getConnection();
        
        try{
            PreparedStatement pst = conn.prepareStatement("insert into user values (?,?)");
            pst.setString(1, name);
            pst.setString(2, password);
            pst.executeUpdate();          
        }catch(Exception e) {
            System.out.println("Update DB error" + e.getMessage());
        }finally{
            try{
                conn.close();
            }catch(Exception e){
                System.out.println("Close DB connection error" + e.getMessage());
            }
        }
    }

        public static void editUser(String name, String password) {
        conn = getConnection();
        
        try{
            PreparedStatement pst = conn.prepareStatement("update user set password=? where name=?");
            pst.setString(1, password);
            pst.setString(2, name);
            pst.executeUpdate();          
        }catch(Exception e) {
            System.out.println("Update DB error" + e.getMessage());
        }finally{
            try{
                conn.close();
            }catch(Exception e){
                System.out.println("Close DB connection error" + e.getMessage());
            }
        }
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
