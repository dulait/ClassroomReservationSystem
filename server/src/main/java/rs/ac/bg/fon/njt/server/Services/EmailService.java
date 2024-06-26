package rs.ac.bg.fon.njt.server.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendTempPassword(String email, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setFrom("fonreservations@gmail.com");
        message.setSubject("Temporary Password");
        message.setText("Your temporary password is: " + tempPassword);

        mailSender.send(message);
    }

    public void sendPasswordChangeLink(String email, String token){
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(email);
        message.setFrom("fonreservations@gmail.com");
        message.setSubject("Password change");
        message.setText("Click on this link to change your password: " + "http://localhost:8080/api/change-password?token=" + token);

        mailSender.send(message);
    }
}
