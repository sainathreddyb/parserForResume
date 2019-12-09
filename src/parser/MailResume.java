package parser;
import java.io.IOException;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailResume {

public static void send(String[] filepath,int length) {

    final String username = "gshubasree@gmail.com";
    final String password = "rememberingpwd@gmail";
    String msg="resumes";

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("gshubasree@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("gshubasree@gmail.com"));
        message.setSubject("resume macthing with requirements");
        message.setText("PFA");

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(msg,"text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

       // messageBodyPart = new MimeBodyPart();
        
        //String file = path;
        //String fileName = "resume";
        //DataSource source = new FileDataSource(file);
        //messageBodyPart.setDataHandler(new DataHandler(source));
        //messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);
        for(int i=0;i<length;i++)
        {
        	 MimeBodyPart attachPart = new MimeBodyPart();
        	 try {
        		 System.out.println(filepath[i]);
                 attachPart.attachFile(filepath[i]);
             } 
        	 catch (IOException ex) {
                 ex.printStackTrace();
             }
        	 
             multipart.addBodyPart(attachPart);
         }

        message.setContent(multipart);

        System.out.println("Sending");

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
  }
}
