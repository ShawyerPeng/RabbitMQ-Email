package service.impl;

import model.EmailDto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

import static javax.mail.Message.RecipientType.TO;

public class EmailSender {
    public void send(EmailDto emailDto) {
        System.out.println(emailDto.toString());

        final String host = emailDto.getHost();
        final String port = emailDto.getPort();
        final String sender = emailDto.getSender();                     // 发送人邮箱
        final List<String> receiver = emailDto.getReceiver();           // 收件人
        final String password = emailDto.getPassword();                 // 邮箱登录密码
        final String authorization = emailDto.getAuthorization();       // 邮箱设置中的授权码

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, authorization);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setSubject(emailDto.getSubject());
            message.setText(emailDto.getContent());

            final int num = receiver.size();
            InternetAddress[] addresses = new InternetAddress[num];
            for (int i = 0; i < num; i++) {
                addresses[i] = new InternetAddress(receiver.get(i));
            }
            message.setRecipients(TO, addresses);

            Transport.send(message);

            System.out.println("Emails are sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
