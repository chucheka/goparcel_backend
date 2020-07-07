package com.neulogics.GoParcel.services;




import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendEmail(String sendTo,String subject,String message) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(sendTo);
		msg.setSubject(subject);
		msg.setText(message);
		javaMailSender.send(msg);
		
	}
}