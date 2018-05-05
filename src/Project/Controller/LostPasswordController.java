package Project.Controller;

import Project.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Properties;

public class LostPasswordController {

    private final String databaseMail = "HKR.Health.App@gmail.com";
    private final String databaseMailPassword = "Projecttwoapp";
    private final String subject = "Your new password.";
    private String body, newPassword;
    boolean checkStatus;

    @FXML
    private TextField emailTF;

    @FXML private TextField ssnTF;

    @FXML
    private Button passwordButton;

    @FXML
    private Button cancelButton;

    @FXML
    void cancelButtonPressed(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project/View/loginScene.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void passwordButtonPressed(ActionEvent event) {
        try {
            checkStatus = DatabaseConnection.getInstance().checkEmailDB(ssnTF.getText(), emailTF.getText());

            if (checkStatus == true) {
                newPassword = createRandomPassword();
                DatabaseConnection.getInstance().updatePassword(newPassword, ssnTF.getText());
                body = createLostPasswordMessage(newPassword);

                sendEmail(databaseMail, databaseMailPassword, emailTF.getText(), subject, body);

            } else {
                ssnTF.clear();
                ssnTF.setText("Enter a vaild SSN.");
                emailTF.clear();
                emailTF.setText("Enter a vailed email.");
                ssnTF.requestFocus();
                throw new InputMismatchException();
            }

        //Catcha AddressException och MessagingException
        }catch (InputMismatchException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Input is not correct.");
            alert.setContentText("The information you have entered doesn't exist or it's misspelled.");
            alert.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Metod som skickar mailet till den email man skriver in.
    public void sendEmail(String from, String password, String to, String subject, String body) throws Exception{
            Properties properties = System.getProperties();
            String host = "smtp.gmail.com";
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.user", from);
            properties.put("mail.smtp.password", password);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            InternetAddress reciever = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, reciever);

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password change");
            alert.setHeaderText("Password change.");
            alert.setContentText("Your password has been changed. Check your email.");
            alert.showAndWait();

            ssnTF.clear();
            emailTF.clear();
    }

    //Metod som skapar ett meddelande innehållande det nya lösenordet.
    public String createLostPasswordMessage(String password){
        String messageBody = "Your new password is " + password + ".";
        return messageBody;
    }

    //Metod som skapar ett nytt lösenord.
    public String createRandomPassword(){
        String randomPassword = "";
        SecureRandom rand = new SecureRandom();

            for (int i = 0; i < 4; i++){
                randomPassword = randomPassword + String.valueOf(rand.nextInt(10));
            }

        return randomPassword;
    }
}




