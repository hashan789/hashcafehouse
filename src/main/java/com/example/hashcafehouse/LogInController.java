package com.example.hashcafehouse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import alerts.AlertMessages;
import dataAccess.UserDataAccess;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Data;
import model.User;

public class LogInController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField address;

        @FXML
        private Button createAccBtn;

        @FXML
        private TextField email;

        @FXML
        private AnchorPane home;

        @FXML
        private AnchorPane login;

        @FXML
        private Button loginBtn;

        @FXML
        private PasswordField password;

        @FXML
        private AnchorPane signup;

        @FXML
        private Button signupBtn;

        @FXML
        private TextField username;

        @FXML
        private TextField usernamelogin;

        @FXML
        private PasswordField passwordlogin;

        @FXML
        private Button haveAccBtn;

        @FXML
        private Alert alert;

        @FXML
        private AnchorPane forgetPassword;

        @FXML
        private PasswordField newPassword;

        @FXML
        private PasswordField confirmPassword;

        @FXML
        private TextField passusername;

        private Parent root;

        public void switchForm(ActionEvent event){
            TranslateTransition slider = new TranslateTransition();
            if(event.getSource() == createAccBtn){
                slider.setNode(home);
                slider.setToX(300);
                slider.setDuration(Duration.seconds(.5));

                slider.setOnFinished((ActionEvent e) -> {
                    haveAccBtn.setVisible(true);
                    createAccBtn.setVisible(false);
                    forgetPassword.setVisible(false);
                });

                slider.play();
            }
            else if(event.getSource() == haveAccBtn){
                slider.setNode(home);
                slider.setToX(0);
                slider.setDuration(Duration.seconds(.5));

                slider.setOnFinished((ActionEvent e) -> {
                    haveAccBtn.setVisible(false);
                    createAccBtn.setVisible(true);
                    forgetPassword.setVisible(false);
                });

                slider.play();
            }

        }

        public void signup(){
            if(username.getText().isEmpty() || password.getText().isEmpty() || email.getText().isEmpty()){
                AlertMessages alert = new AlertMessages();
                alert.errorMessage("error","Error Message","Please fill in the blanks");
            }
            else{
                User user = new User();
                user.setUsername(username.getText());
                user.setPassword(password.getText());
                user.setEmail(email.getText());
                user.setAddress(address.getText());
                UserDataAccess.save(user);

                AlertMessages alert = new AlertMessages();
                alert.errorMessage("information","Message","Successfully registered account");

                TranslateTransition slider = new TranslateTransition();

                slider.setNode(home);
                slider.setToX(0);
                slider.setDuration(Duration.seconds(.5));

                slider.setOnFinished((ActionEvent e) -> {
                    haveAccBtn.setVisible(false);
                    createAccBtn.setVisible(true);
                    forgetPassword.setVisible(false);
                });

                slider.play();
            }
        }

        public void login() throws IOException{

            String name = usernamelogin.getText();
            String passwrd = passwordlogin.getText();
            User user = UserDataAccess.login(name,passwrd);
            if(user == null){
                AlertMessages alert = new AlertMessages();
                alert.errorMessage("error","Error Message","Incorrect username or password");
            }
            else{
                if(!user.getUsername().equals(usernamelogin.getText())){
                    AlertMessages alert = new AlertMessages();
                    alert.errorMessage("information","Message","wait for admin approval");
                }
                if(user.getUsername().equals(usernamelogin.getText())){

                    Data.username = name;

                        Parent root = FXMLLoader.load(getClass().getResource("mainFhashanorm.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setTitle("Main Form");
                        stage.setScene(scene);
                        stage.show();

                        loginBtn.getScene().getWindow().hide();

                }
            }
        }

        public void switchForgetPassword(){
            forgetPassword.setVisible(true);
            login.setVisible(false);
        }

        public void changePassword(){
            if(newPassword.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                AlertMessages alert = new AlertMessages();
                alert.errorMessage("error","Error Message","Please Fill in the blanks");
            }
            else{
                String username = passusername.getText();
                String password = confirmPassword.getText();
                UserDataAccess.update(username,password);
                AlertMessages alert = new AlertMessages();
                alert.errorMessage("information","Information Message","Changed password successfully");
            }
        }


}
