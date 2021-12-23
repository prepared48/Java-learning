package com.geniu.tool.mail;

import com.geniu.tool.FileUtil;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class JavaMailWithAttachment {
    private MimeMessage message;
    private Session session;
    private Transport transport;

    private String mailHost = "";
    private String sender_username = "";
    private String sender_password = "";

    private Properties properties = new Properties();

    /*
     * 初始化方法
     */
    public JavaMailWithAttachment(boolean debug) {
        ClassPathResource imgResource = new ClassPathResource("MailServer.properties");

        try {
            InputStream in = imgResource.getInputStream();
            properties.load(in);
            this.mailHost = properties.getProperty("mail.smtp.host");
            this.sender_username = properties.getProperty("mail.sender.username");
            this.sender_password = properties.getProperty("mail.sender.password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        session = Session.getInstance(properties);
        session.setDebug(debug);// 开启后有调试信息
        message = new MimeMessage(session);
    }


    public void doSendMulitiPartEmail(String subject, String sendHtml, String receiveUser, File attachment) {
        MultiPartEmail multiPartEmail = new MultiPartEmail();
        try {
            multiPartEmail.setHostName(mailHost);
            multiPartEmail.setAuthentication(sender_username, sender_password);
            // 发件人
            multiPartEmail.setFrom(sender_username, "prepared.com");
            // 收件人
            multiPartEmail.addTo(receiveUser);

            // 邮件主题
            multiPartEmail.setSubject(subject);
            multiPartEmail.setMsg(sendHtml);
//            multiPartEmail.setContent(sendHtml, "text/html; charset=utf-8");
            EmailAttachment attachmentLogs = new EmailAttachment();
            attachmentLogs.setPath(attachment.getPath());
            attachmentLogs.setDisposition(EmailAttachment.ATTACHMENT);
            attachmentLogs.setDescription("Logs");
            attachmentLogs.setName(attachment.getName());

            // 将multipart对象放到message中
            multiPartEmail.attach(attachmentLogs);

            multiPartEmail.send();
            System.out.println("send success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 发送邮件
     * 
     * @param subject 邮件主题
     * @param sendHtml 邮件内容
     * @param receiveUser 收件人地址
     * @param attachment 附件
     */
    public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser, File attachment) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sender_username);
            message.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);

            // 邮件主题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            
            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            
            // 添加附件的内容
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                
                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
                
                //MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }
            
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("send success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送html格式文件正文+附件（解决中文错误码）
     *
     * @param message
     */
    public void sendHTMLEmail (EmailMessage message) {
        try {
            HtmlEmail htmlEmail = new HtmlEmail();
            htmlEmail.setAuthentication(sender_username, sender_password);
            htmlEmail.setHostName(mailHost);
            htmlEmail.addTo(message.getEmailTo());
            htmlEmail.setFrom(sender_username, "prepared.com");
            htmlEmail.setSubject(message.getEmailSubJect());
            htmlEmail.setMsg(message.getEmailContent());
            htmlEmail.setCharset("utf-8");
            // 乱码解决
            String name = new String(message.getAttachFile().getName().toString().getBytes(),"utf-8");
            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//            String name1="=?UTF-8?B?"+enc.encode("测试.txt".getBytes("utf-8"))+"?=";
            String fileName = "=?UTF-8?B?"+enc.encode(name.getBytes("utf-8"))+"?=";
            htmlEmail.embed(new FileDataSource(message.getAttachFile()), fileName);
            htmlEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        JavaMailWithAttachment se = new JavaMailWithAttachment(true);
//        EmailMessage emailMessage = new EmailMessage();
//        Map<String, Object> fileContent = new HashMap<>();
//        fileContent.put("key", "vales");
//        fileContent.put("test1", "value2");
//        String msgHtml = "<html>\n" +
//                "\t<head><meta http-equiv=Content-Type content=text/html; charset=utf-8></head>\n" +
//                "\t<body bgcolor=\"#ffffff\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\"> \n" +
//                "\t\t\t<table width=\"580\" style=\"font-size:12px;\">\t\n" +
//                "\t\t\t\t<tr>\t\n" +
//                "\t\t\t\t\t<td align=\"left\">尊敬的用户，您好：\t<br><br>&nbsp;&nbsp;&nbsp;&nbsp;请下载附件。            \n" +
//                "\t\t\t\t\t</td>\t\n" +
//                "\t\t\t\t</tr> \n" +
//                "\t\t\t</table> \n" +
//                "\t</body> \n" +
//                "\n" +
//                "</html>";
//        emailMessage.setAttachFile(FileUtil.contentToFile(fileContent, "测试prepared.txt"));
//        emailMessage.setEmailTo("438208265@qq.com");
//        emailMessage.setEmailSubJect("test");
//        emailMessage.setEmailContent(msgHtml);
////        emailMessage.setType(19);
//        se.sendEmail(emailMessage);
//    }

    public static void main(String[] args) {
        String sendContent = "<html>\n" +
                "\t<head><meta http-equiv=Content-Type content=text/html; charset=utf-8></head>\n" +
                "\t<body bgcolor=\"#ffffff\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\"> \n" +
                "\t\t<center> \n" +
                "\t\t\t<table width=\"580\" style=\"font-size:12px;\">\t\n" +
                "\t\t\t\t<tr>\t\n" +
                "\t\t\t\t\t<td align=\"left\">\t<br>\t尊敬的用户，您好：\t<br>\t<br>\t&nbsp;&nbsp;&nbsp;&nbsp;您的个人信息已准备完成，请下载附件。            \n" +
                "\t\t\t\t\t</td>\t\n" +
                "\t\t\t\t</tr> \n" +
                "\t\t\t</table> \n" +
                "\t\t</center> \n" +
                "\t</body> \n" +
                "\n" +
                "</html>";
        JavaMailWithAttachment se = new JavaMailWithAttachment(true);
        File affix = new File("d:\\tmp\\text.txt");
//        se.doSendHtmlEmail("test", sendContent, "shibo.zhong@qunar.com", affix);
        se.doSendMulitiPartEmail("test", sendContent, "shibo.zhong@qunar.com", affix);
    }
}

