package Project.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class LostPasswordController {

    private final String databaseMail = "HKR.Health.App@gmail.com";
    private final String databaseMailPassword = "Projecttwoapp";
    private String receiverMail = "anton.sjostrand10@gmail.com";
    private String subject = "test", body = "Testing application mail function";

    @FXML
    private TextField emailTF;

    @FXML
    private Button passwordButton;

    @FXML
    private Button cancelButton;

    @FXML
    void cancelButtonPressed(ActionEvent event) {

    }

    @FXML
    void passwordButtonPressed(ActionEvent event) {
        sendEmail(databaseMail, databaseMailPassword, receiverMail, subject, body);

    }

    //Subject och body behöver innehålla lösenordet!

    public void sendEmail(String from, String password, String to, String subject, String body){
        try {
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
            InternetAddress toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();


        //Catcha AddressException och MessagingException
        }catch (Exception e){

        }
    }

}




