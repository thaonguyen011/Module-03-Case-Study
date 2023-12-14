package com.example.case_study.model.utils.login;


import com.example.case_study.model.entity.User;
import com.example.case_study.model.service.IUserService;
import com.example.case_study.model.service.impl.UserService;
import com.example.case_study.model.utils.generator.IGenerator;
import com.example.case_study.model.utils.generator.RandomCode;
import com.mysql.cj.protocol.Message;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class LoginManager {
    private static LoginManager instance;
    private final int MAX_LOGIN_ATTEMPTS = 5;
    private final IUserService userService;
    private final Map<String, Integer> loginAttemptsManagement;
    private final Map<String, Long> lockedUserManagement;
    private final Map<String, Integer> codeValidateManagement;
    private final long BLOCK_DURATION =  5 * 10 * 1000;
//    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    private LoginManager() {
        userService = new UserService();
        loginAttemptsManagement = new HashMap<>();
        for (User user : userService.selectAll()) {
            loginAttemptsManagement.put(user.getUsername(), 0);
        }
        lockedUserManagement = new HashMap<>();
        codeValidateManagement = new HashMap<>();
//        scheduler.scheduleAtFixedRate(this::clearBlock, 0, 1, TimeUnit.MINUTES);
    }


    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;

    }


    public int authentic(ILoginRequest loginRequest) {
        Validator loginValidator = new LoginValidator(loginRequest.getUsername(), loginRequest.getPassword());
        User user = userService.getUserByUsername(loginRequest.getUsername());
        if (user == null) {
            return 0;
        } else if (isBlockedUser(user.getUsername())) {
            return 6;
        } else if (loginValidator.isCheck() && loginAttemptsManagement.get(user.getUsername())<= MAX_LOGIN_ATTEMPTS) {
            return -1;
        } else {
            if (loginAttemptsManagement.get(user.getUsername())<= MAX_LOGIN_ATTEMPTS) {
                int count1= loginAttemptsManagement.get(user.getUsername()) + 1;
                if (count1 == 3) {
                    sendAlertEmail(user.getEmail(), user.getUsername());
                }
                loginAttemptsManagement.replace(user.getUsername(), count1);
                return count1;
            } else {
                blockUser(user.getUsername());
                return 6;
            }
        }
    }

    public void blockUser(String username) {
        lockedUserManagement.put(username, System.currentTimeMillis() + BLOCK_DURATION);
    }

    public void clearBlock() {
        long currentTime = System.currentTimeMillis();
        for (Map.Entry<String, Long> entry : lockedUserManagement.entrySet()) {
            if (currentTime > entry.getValue()) {
                lockedUserManagement.remove(entry.getKey());
                loginAttemptsManagement.replace(entry.getKey(), 0);
            }
        }
    }

    public void sendAlertEmail(String email, String account) {
        String username = "tn0785593@gmail.com";
        String password = "ecki jasb oraz uatw";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tn0785593@gmail.com"));
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Alert email");

            String htmlContent = "<html><body><p>This is a alert email sent from netflix. There is a user trying to login your account. Is it you?. Do you want to block account?</p> " +
                    "<a href=\"http://localhost:8080/block-user?username=" + account + "\">Block account</a></body></html>";

            Multipart multipart = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html");
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendCodeEmail(String email) {
        String username = "tn0785593@gmail.com";
        String password = "ecki jasb oraz uatw";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        IGenerator randomCode = new RandomCode();
        int code = (int) randomCode.generate();
        codeValidateManagement.put(email, code);

        try {
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Code validation email");
            message.setText("This is a code validation email sent from netflix. Code is " + code);
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isBlockedUser(String username) {
        return lockedUserManagement.containsKey(username);
    }

    public boolean validateCode(String email, int code) {
        return codeValidateManagement.get(email).equals(code);
    }

}
