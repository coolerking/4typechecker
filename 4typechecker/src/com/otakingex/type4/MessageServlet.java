package com.otakingex.type4;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otakingex.type4.control.Utils;

public class MessageServlet extends BaseServlet {

	private static final long serialVersionUID = -6530680988519844145L;
	private Logger log = Logger.getLogger(getClass().getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		try{
			// サブタイトルなしで
			String text = req.getParameter("text");
			
			String address = System.getProperty(ViewConstants.SYSTEM_PROP_MAILADDRESS);
			if(address!=null){
				Session session = Session.getDefaultInstance(new Properties());
				MimeMessage message = new MimeMessage(session);
				message.addRecipient(
                            RecipientType.TO,
                            new InternetAddress(
                                            address,
                                            "4TypeResultAnalyst",
                                            "ISO-2022-JP"));
				message.setSubject(
                            "[MemeMessage] " + Utils.now());
				message.setText(
                            text,
                            "ISO-2022-JP");
				message.setFrom(
                            new InternetAddress(
                                            "4typechecker.ex@gmail.com",
                                            "MemeChecker",
                                            "ISO-2022-JP"));
				Transport.send(message);
			}
			sendRedirect(JSP_THANKS, req, resp);

		}catch(Exception e){
			log.log(Level.INFO, "MessageServlet#doPost()処理中例外", e);
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
}
