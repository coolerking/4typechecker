package com.otakingex.type4.store;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.otakingex.type4.ViewConstants;
import com.otakingex.type4.control.WorkoutContext;
import com.otakingex.type4.master.TestMasterManager;
import com.otakingex.type4.model.User;

public class MailStoreManager {
	private static MailStoreManager manager = new MailStoreManager();
	private boolean store = false;
	private String address = null;
	private MailStoreManager(){
		String doStore = System.getProperty(ViewConstants.SYSTEM_PROP_IS_STORE);
		doStore = doStore.trim().toLowerCase();
		if("true".equals(doStore)){
			store = true;
		}else{
			store = false;
		}
		address = System.getProperty(ViewConstants.SYSTEM_PROP_MAILADDRESS);
		if(address==null) store = false;
		else store = true;
	}
	public static synchronized MailStoreManager getInstance(){
		return manager;
	}
	
	public void doStore(WorkoutContext context) throws UnsupportedEncodingException, MessagingException{
		if(!store) return;
		User user = context.getUser();
		Session session = Session.getDefaultInstance(new Properties());
		MimeMessage message = new MimeMessage(session);
		message.addRecipient(
				RecipientType.TO,
				new InternetAddress(
						address,
						"4Type履歴管理者",
						"ISO-2022-JP"));
		StringBuffer subject = new StringBuffer();
		subject.append(user.getName());
		subject.append(" /");
		subject.append(user.getAge());
		subject.append("歳");
		message.setSubject(subject.toString());
		StringBuffer text = new StringBuffer();
		text.append("\"日時\",");
		text.append(new Date().toString());
		text.append("\n");
		text.append("\"氏名\",\"");
		text.append(user.getName());
		text.append("\"\n");
		text.append("\"年齢\",");
		text.append(user.getAge());
		text.append("\n");
		text.append("\"性別\",\"");
		if(user.isMale()) text.append("男\"\n");
		else text.append("女\"\n");
		text.append("\"テストバージョン\",");
		text.append(TestMasterManager.getInstance().getTestMaster().getClass().getName());
		text.append("\n");
		text.append("\"王様・軍人向性\",");
		text.append(context.getKingOrSolderScore());
		text.append("\n");
		text.append("\"学者・職人向性\",");
		text.append(context.getScholarOrCraftsmanScore());
		text.append("\n");
		text.append("\"王様\",");
		text.append(context.getKingScore());
		text.append("\n");
		text.append("\"軍人\",");
		text.append(context.getSolderScore());
		text.append("\n");
		text.append("\"学者\",");
		text.append(context.getScholarScore());
		text.append("\n");
		text.append("\"職人\",");
		text.append(context.getCraftsmanScore());
		text.append("\n");
		
		message.setText(text.toString(), "ISO-2022-JP");
		message.setFrom(new InternetAddress("xxxx@otaking-ex.com", "4type簡易判定サイト", "ISO-2022-JP"));
		Transport.send(message);
		return;
	}

}
