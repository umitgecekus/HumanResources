package com.umit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    /**
     * ttdt sjkq xora nmhr
     */
    private final JavaMailSender javaMailSender;

    //@EventListener(ApplicationReadyEvent.class)
    public void sendMailChangePassword(String email, String password) {
        String activationLink = generateActivationLink(email,password);
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("workforce.solutions.info@gmail.com");
        mailMessage.setTo(email);
        mailMessage.setSubject("Workforce Solutions");
        mailMessage.setText("Your Account is activated. \n \n " +
                "Welcome to Workforce Solutions! \n \n " +
                "Your password is : " + password + "\n \n " +
                "Please change your password by clicking on the link below. \n \n " + "" + activationLink);

        javaMailSender.send(mailMessage);
    }

    public String generateActivationLink(String email,String password) {
        return "http://localhost:9091/swagger-ui/index.html#/auth-controller/changePassword?email=" + email + "&password=" + password;
        // todo:  "http://localhost:9091/dev/v1/auth/change-password?email=" + email + "&password=" + password -> kubernetes kısmı gelince burası güncellenecek.

    }

    public void sendMailReject(String email) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("workforce.solutions.info@gmail.com");
        mailMessage.setTo(email);
        mailMessage.setSubject("Workforce Solutions");
        mailMessage.setText("Your Account could not be activated. \n \n " +
                "Thank You For Choosing Us.!");
        javaMailSender.send(mailMessage);
    }

}
