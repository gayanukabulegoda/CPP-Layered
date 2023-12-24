package lk.grb.ceylonPottersPaletteLayered.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendEmail {

    private Session newSession = null;
    private MimeMessage mimeMessage = new MimeMessage(Session.getDefaultInstance(new Properties(), null));

    public void sendMail(String[] recipientSubjectBody) throws MessagingException {

        setUpServerProperties();
        draftEmail(recipientSubjectBody);
        sendEmail();
    }

    public void setUpServerProperties() {

        Properties properties = new Properties();
        properties.put("mail.smtp.port", "587"); // Use TLS port
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        newSession = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ceylonpotterspallet@gmail.com", "vord gsyd hfnf vwua");
            }
        });
    }

    public MimeMessage draftEmail(String[] detail) throws MessagingException {

        mimeMessage.setFrom(new InternetAddress("ceylonpotterspallet@gmail.com"));
        String recipients = detail[0];
        String subject = detail[1];
        String body = detail[2];

        mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(recipients)));
        mimeMessage.setSubject(subject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(body, "text/html");

        MimeMultipart multipart = new MimeMultipart(); //mime msg's body
        multipart.addBodyPart(bodyPart);

        mimeMessage.setContent(multipart);

        return mimeMessage;
    }

    public void sendEmail() throws MessagingException {

        String host = "smtp.gmail.com";
        String userName = "ceylonpotterspallet@gmail.com";
        String password = "vord gsyd hfnf vwua"; // Replace with your actual Gmail password or App Password

        Transport transport = newSession.getTransport("smtp");
        transport.connect(host, userName, password);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email Sent Successfully !");
    }
}
