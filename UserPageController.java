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

/**
 *
 * @author huidong
 */
public class UserPageController implements Initializable {

//    static ObservableList<User> users  = FXCollections.observableArrayList();

    @FXML
    TextField textfield;
    @FXML
    TableView<User> tableview = new TableView<User>();
    @FXML
    TableColumn<User, String> nameColumn = new TableColumn<User, String>("name");
    @FXML
    TableColumn<User, String> passwordColumn = new TableColumn<User, String>("password");
       
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        System.out.println("You clicked me!");
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User,String>("password"));       
        ObservableList<User> users  = FXCollections.observableArrayList();
       
        for(User user: DataBase.getUsers()) {
            users.add(user);
        }

        tableview.setItems(users);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
