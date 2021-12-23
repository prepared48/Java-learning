package com.geniu.tool.mail;

import lombok.Data;

import javax.mail.internet.MimeMultipart;
import java.io.File;

@Data
public class EmailMessage {

    /**
     * 邮件服务器域名
     **/
    private String emailHost;

    /**
     * 发件人
     **/
    private String emailFrom;

    /**
     * 收件人
     **/
    private String emailTo;

    /**
     * 邮件主题
     **/
    private String emailSubJect;

    /**
     * 邮件内容
     **/
    private String emailContent;

    private int type;

    /**
     * 附件
     **/
    private File attachFile;

    private MimeMultipart multipart;

}