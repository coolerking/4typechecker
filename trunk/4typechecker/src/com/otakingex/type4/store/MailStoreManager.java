package com.otakingex.type4.store;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.otakingex.type4.ViewConstants;
import com.otakingex.type4.control.Utils;
import com.otakingex.type4.control.WorkoutContext;

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
               Session session = Session.getDefaultInstance(new Properties());
               MimeMessage message = new MimeMessage(session);
               message.addRecipient(
                               RecipientType.TO,
                               new InternetAddress(
                                               address,
                                               "4TypeResultAnalyst",
                                               "ISO-2022-JP"));
               message.setSubject(
                               "[MemeResult] " + Utils.now());
               message.setText(
                               context.getMessageBody(),
                               "ISO-2022-JP");
               message.setFrom(
                               new InternetAddress(
                                               "4typechecker.ex@gmail.com",
                                               "MemeChecker",
                                               "ISO-2022-JP"));
               Transport.send(message);
               return;
       }
}