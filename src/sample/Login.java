package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
    public Login() {

    }

    @FXML
    private Button button1;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }
    private void checkLogin() throws IOException {
        Main m = new Main();
        if(username.getText().toString().equals("javacoding") && password.getText().toString().equals("123")){
            wrongLogin.setText("Success!");

            m.changeScene("afterLogin.fxml");
        }
        else if (username.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogin.setText("Please enter your data.");
        }
        else {
            wrongLogin.setText("Wrong username or password!");
        }
    }
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM Username = '"+ username.getText() + "'And password'"+password.getText()+"'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()){
                if (queryResult.getInt(1) == 1) {
                    // loginMessageLabel.setText("Congratulations!");
                    createAccountForm();
                } else {
                    wrongLogin.setText("Invalid login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createAccountForm(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.setTitle("login");
            registerStage.setScene(new Scene(root, 520, 539));
            registerStage.show();
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
}



