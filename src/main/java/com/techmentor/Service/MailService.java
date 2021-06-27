package com.techmentor.Service;

import com.techmentor.Model.Mail;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class MailService {
	String from = "7loveindia@gmail.com";
//	String password = "Techmentor10";// change according
	private final Properties props;
	private final Session session;

	public MailService() {
		String from = "7loveindia@gmail.com";
		final String password = "Techmentor10";// change according
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
//	     System.out.println(props);
		// Get the Session object.
		session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password); // To change body of generated methods, choose Tools
																	// | Templates.
			}
		});
		// session created
	}

	public String sendMail(Mail mail) {

		try {
//              Message message = new SMTPMessage(session);
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
			message.setSubject(mail.getSubject());

			message.setContent(mail.getBody(), "text/html");
			Transport.send(message);
			System.out.println("Sent message successfully....");
			return "Message Successfully sent";

		} catch (AddressException ex) {
			System.out.print("Addresss exception");
			return "Address exception";
		} catch (MessagingException ex) {
			System.out.print("Messaging exception");
			return "Messaging exception";
		}

	}

	public String sendMailWithImage(Mail mail, String filename) {
		try {
//          Message message = new SMTPMessage(session);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
			message.setSubject(mail.getSubject());
//			message.setContent(mail.getBody(), "text/html");
			
		      // creating first MimeBodyPart object
            BodyPart messageBodyPart1 = new MimeBodyPart(); 
            messageBodyPart1.setText(mail.getBody());
			
            // creating second MimeBodyPart object
            BodyPart messageBodyPart2 = new MimeBodyPart(); 
            DataSource source = new FileDataSource(filename);  
            messageBodyPart2.setDataHandler(new DataHandler(source));  
            messageBodyPart2.setFileName(filename);
			
            // creating MultiPart object
            Multipart multipartObject = new MimeMultipart();  
            multipartObject.addBodyPart(messageBodyPart1);  
            multipartObject.addBodyPart(messageBodyPart2);
            
            // set body of the email.
            message.setContent(multipartObject);
            //send
			Transport.send(message);
			System.out.println("Sent message successfully....");
			return "Message Successfully sent";

		} catch (AddressException ex) {
			System.out.print("Addresss exception");
			return "Address exception";
		} catch (MessagingException ex) {
			System.out.print("Messaging exception");
			return "Messaging exception";
		}

	}


}
