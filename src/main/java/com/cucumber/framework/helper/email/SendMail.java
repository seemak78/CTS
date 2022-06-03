package com.cucumber.framework.helper.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.cucumber.framework.configreader.PropertyFileReader;

/*
 * This class has the main code for sending mail
 */
public class SendMail {
	public static void send(String emailTo, String subject, String text, String filename) throws MessagingException {
    /* Get the session object -
	** Refer for props below in /src/main/resources/configfile/confiig.properties
	*/
	PropertyFileReader reader = new PropertyFileReader();
	String from = reader.getEmail();
	Properties prop = new Properties();
    prop.put("mail.smtp.host", reader.getHost()); //"smtp.gmail.com");
    prop.put("mail.smtp.port", reader.getSmtpPort()); //"465");
    prop.put("mail.smtp.ssl.enable", reader.getSSLEnabled()); //"true");
    prop.put("mail.smtp.auth", reader.getSmtpAuth()); //"true");
    if (emailTo.equals("report"))
    	emailTo=reader.getSendEmailTo();
    else if (emailTo.equals("bug"))
       	emailTo= "support@customtravelsolutions.com";   //reader.getBugEmailAddressTo();
       
    System.out.println("email"+emailTo);
    
    Session session = Session.getInstance(prop,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(reader.getEmail(), reader.getEmailPassword());
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(emailTo)
        );


      BodyPart objMessageBodyPart = new MimeBodyPart();
      // Option 3: Send text along with attachment
      objMessageBodyPart.setContent(
          "<h1>Report!</h1></br>" + text, "text/html");
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(objMessageBodyPart);

      objMessageBodyPart = new MimeBodyPart();
      DataSource source = new FileDataSource(filename);
      objMessageBodyPart.setDataHandler(new DataHandler(source));
      objMessageBodyPart.setFileName(filename.replace("target/", ""));
      multipart.addBodyPart(objMessageBodyPart);
      message.setContent(multipart);
      message.setSubject(subject);
      // send message
      Transport.send(message);

      System.out.println("message sent successfully");

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }// End of SEND method
}