package tw.idv.Seeker_Pool_Merge.common.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * 寄 文字上帶有連結的HTML格式信件
 * @author fong
 *
 */
public class SendEmailLinkTextUtil {
    public static boolean sendMail(String to, String subject, String messageText) {
        // SMTP設定
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "SeekerPool@gmail.com";
        String password = "ucppycuvblwaeeqi";

        // 郵件內容
        String toAddress = to;
        String mail_subject = subject;
        String body = messageText;

        // 設定屬性
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // 建立Session物件
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 建立郵件訊息
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            message.setSubject(mail_subject);
            message.setContent(body, "text/html;charset=utf-8");

            // 寄出郵件
            Transport.send(message);
            System.out.println("信件傳送成功!");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("信件傳送失敗!");
            return false;
        }
    }
}