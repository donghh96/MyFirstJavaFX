/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyFirstJavaFX;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author huidong
 */
public class UserPageController implements Initializable {

    @FXML
    TextField nametextfield, passwordtextfield, nametextfield2, passwordtextfield2;
    @FXML
    AnchorPane tablepane, addpane, editpane;
    @FXML
    TableView<User> tableview = new TableView<User>();
    @FXML
    TableColumn<User, String> nameColumn = new TableColumn<User, String>("name");
    @FXML
    TableColumn<User, String> passwordColumn = new TableColumn<User, String>("password");
       
    @FXML
    private void showUsers(ActionEvent event) throws SQLException {
        System.out.println("You clicked me!");
        tableview.getItems().clear();
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User,String>("password"));       
        ObservableList<User> users  = FXCollections.observableArrayList();

        for(User user: DataBase.getUsers()) {
            users.add(user);
        }

        tableview.setItems(users);
    }
    
    @FXML
    private void addUser(ActionEvent event) {
        nametextfield.clear();
        passwordtextfield.clear();
        tableview.setVisible(false);
        addpane.setVisible(true);
    }
    
    @FXML
    private void confirmAdd(ActionEvent event) {
        String name = nametextfield.getText();
        String password = passwordtextfield.getText();
        
        DataBase.addUser(name, password);
 
        tableview.setVisible(true);
        addpane.setVisible(false);
    }
    
    @FXML
    private void cancelAdd(ActionEvent event) {
        tableview.setVisible(true);
        addpane.setVisible(false);
    }
    
    @FXML
    private void editUser(ActionEvent event) {
        if(tableview.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Please select a record first");
        }
        else {
            User user = (User)tableview.getSelectionModel().getSelectedItem();
            tableview.setVisible(false);
            editpane.setVisible(true);
            nametextfield2.setText(user.getName());
            passwordtextfield2.setText(user.getPassword());         
        }        
    }
    
    @FXML
    private void confirmEdit(ActionEvent event) {
        String name = nametextfield2.getText();
        String password = passwordtextfield2.getText();
        
        System.out.println(password);
        
        DataBase.editUser(name, password);
 
        tableview.setVisible(true);
        editpane.setVisible(false);
    }
    
    @FXML
    private void cancelEdit(ActionEvent event) {
        tableview.setVisible(true);
        editpane.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
