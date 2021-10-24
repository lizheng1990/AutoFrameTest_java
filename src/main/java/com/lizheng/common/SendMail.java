package com.lizheng.common;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

public class SendMail {
    // 成员变量中设置发件默认设置
    // 发件人的 邮箱 和 密码
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（比如QQ邮箱称为“授权码”）,
    // 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    public static String postMailAccount = "453600806@qq.com";
    public static String postMailPassword = "bpkceozhoipgcaad";
    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com QQ邮箱为smtp.qq.com 在各大邮箱的设置中能够找到。
    //sina.com:
    //POP3服务器地址:pop3.sina.com.cn（端口：110）
    //SMTP服务器地址:smtp.sina.com.cn（端口：25）
    //sinaVIP：
    //POP3服务器:pop3.vip.sina.com （端口：110）
    //SMTP服务器:smtp.vip.sina.com （端口：25）
    //
    //sohu.com:
    //POP3服务器地址:pop3.sohu.com（端口：110）
    //SMTP服务器地址:smtp.sohu.com（端口：25）
    //
    //126邮箱：
    //POP3服务器地址:pop.126.com（端口：110）
    //SMTP服务器地址:smtp.126.com（端口：25）
    //
    //139邮箱：
    //POP3服务器地址：POP.139.com（端口：110）
    //SMTP服务器地址：SMTP.139.com(端口：25)
    //
    //163.com:
    //POP3服务器地址:pop.163.com（端口：110）
    //SMTP服务器地址:smtp.163.com（端口：25）
    //
    //QQ邮箱
    //POP3服务器地址：pop.qq.com（端口：110）
    //SMTP服务器地址：smtp.qq.com （端口：25）
    //
    //QQ企业邮箱
    //POP3服务器地址：pop.exmail.qq.com （SSL启用 端口：995）
    //SMTP服务器地址：smtp.exmail.qq.com（SSL启用 端口：587/465）
    //
    //yahoo.com:
    //POP3服务器地址:pop.mail.yahoo.com
    //SMTP服务器地址:smtp.mail.yahoo.com
    //
    //yahoo.com.cn:
    //POP3服务器地址:pop.mail.yahoo.com.cn（端口：995）
    //SMTP服务器地址:smtp.mail.yahoo.com.cn（端口：587
    //
    //HotMail
    //POP3服务器地址：pop3.live.com （端口：995）
    //SMTP服务器地址：smtp.live.com （端口：587）
    //
    //gmail(google.com)
    //POP3服务器地址:pop.gmail.com（SSL启用 端口：995）
    //SMTP服务器地址:smtp.gmail.com（SSL启用 端口：587）
    //
    //263.net:
    //POP3服务器地址:pop3.263.net（端口：110）
    //SMTP服务器地址:smtp.263.net（端口：25）
    //
    //263.net.cn:
    //POP3服务器地址:pop.263.net.cn（端口：110）
    //SMTP服务器地址:smtp.263.net.cn（端口：25）
    //
    //x263.net:
    //POP3服务器地址:pop.x263.net（端口：110）
    //SMTP服务器地址:smtp.x263.net（端口：25）
    //
    //21cn.com:
    //POP3服务器地址:pop.21cn.com（端口：110）
    //SMTP服务器地址:smtp.21cn.com（端口：25）
    //
    //Foxmail：
    //POP3服务器地址:POP.foxmail.com（端口：110）
    //SMTP服务器地址:SMTP.foxmail.com（端口：25）
    //
    //china.com:
    //POP3服务器地址:pop.china.com（端口：110）
    //SMTP服务器地址:smtp.china.com（端口：25）
    //
    //tom.com:
    //POP3服务器地址:pop.tom.com（端口：110）
    //SMTP服务器地址:smtp.tom.com（端口：25）
    //
    //etang.com:
    //POP3服务器地址:pop.etang.com
    //SMTP服务器地址:smtp.etang.com
    public static String postEmailSMTPHost = "smtp.qq.com";
    // 收件人邮箱(多个以逗号分割)
    public static String receiveMailAccount = "304594568@qq.com,leez6590@sina.com";
    // 昵称,尽量设定，避免被识别为垃圾邮件
    public static String nickname="leez";
    // 抄送名单(多个以逗号分割)
    public static String copyMailAccount = "304594568@qq.com,453600806@qq.com";;
    // 邮箱标题
    public static String title = "测试邮件-来自特斯汀学院自动化测试框架！";
    // 属性
    public static Properties props;
    // 发送时间，默认立即
    public static Date date = new Date();

    /**
     * 基于成员变量,完成属性设置，从而完成邮件初始化设置
     */
    public void initMail() {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        props = new Properties(); // 参数配置
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", postEmailSMTPHost); // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        // 需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        // QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
    }

    /**
     * 发送文本和附件的邮件
     * @param content
     */
    public static void send(String content,String... attachments){
        try {
            //创建session
            Session session = Session.getInstance(props);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //通过message对象创建邮件内容
            MimeMessage message = createSimpleMail(session,content,attachments);
            //通过session得到transport对象
            Transport ts = session.getTransport();
            //使用邮箱的smtp,用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            ts.connect(postEmailSMTPHost, postMailAccount, postMailPassword);
            //发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }catch (Exception e){
            AutoLogger.log.error("邮件配置有问题，请检查！！！");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     * @param session
     * @param content
     * @return
     * @throws Exception
     */
    public static MimeMessage createSimpleMail(Session session,String content,String... attachments)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(postMailAccount, nickname, "UTF-8"));
        //设置多个发送地址
        try {
            if (null != receiveMailAccount && !receiveMailAccount.isEmpty()) {
                new InternetAddress();
                InternetAddress[] internetAddressTo = InternetAddress.parse(receiveMailAccount);
                message.setRecipients(MimeMessage.RecipientType.TO, internetAddressTo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置多个抄送地址
        try {
            if (null != copyMailAccount && !copyMailAccount.isEmpty()) {
                new InternetAddress();
                InternetAddress[] internetAddressCC = InternetAddress.parse(copyMailAccount);
                message.setRecipients(MimeMessage.RecipientType.CC, internetAddressCC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //邮件的标题
        message.setSubject("标题-只包含文本的简单邮件");
        //Mulitpart设置多种格式节点的邮件内容，形成一个混合节点
        MimeMultipart allmm=new MimeMultipart();
        //创建正文节点
        MimeBodyPart text=new MimeBodyPart();
        //设置节点的内容为文本
        text.setContent(content, "text/html;charset=UTF-8");
        //将正文节点添加到multipart中
        allmm.addBodyPart(text);
        //attachments：附件列表发送
        for(String attachment:attachments) {
            //创建一个附件节点
            MimeBodyPart attach=new MimeBodyPart();
            //读取本地文件
            DataHandler attachContent=new DataHandler(new FileDataSource(attachment));
            //将文件添加到附件节点
            attach.setDataHandler(attachContent);
            //设置附件的文件名
            attach.setFileName(MimeUtility.encodeText(attachContent.getName()));
            //将附件节点添加到混合节点multipart中
            allmm.addBodyPart(attach);
        }
        //设置邮件内容为编好的混合节点
        message.setContent(allmm);
        // 设置发件时间
        message.setSentDate(date);
        // 保存设置
        message.saveChanges();
        //返回创建好的邮件对象
        return message;
    }
}