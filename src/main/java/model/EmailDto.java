package model;

import java.io.Serializable;

public class EmailDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;             // 邮件ID
    private String host;            // 服务器地址
    private String port;            // 服务器端口号
    private String sender;          // 发件人邮箱
    private String receiver;        // 收件人邮箱
    private String name;            // 发件人昵称
    private String username;        // 发件人账号
    private String password;        // 发件人密码
    private String authorization;   // 邮箱SMTP授权码
    private String subject;         // 邮件主题
    private String content;         // 邮件内容(支持 HTML)
    private EmailSettings emailSettings;    // 发件人邮件设置

    public EmailDto() {
    }

    public EmailDto(Integer id, String host, String port, String sender, String receiver, String name,
                    String username, String password, String authorization, String subject, String content) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.sender = sender;
        this.receiver = receiver;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorization = authorization;
        this.subject = subject;
        this.content = content;
    }

    public EmailDto(Integer id, String host, String port, String sender, String receiver, String name, String username,
                    String password, String authorization, String subject, String content, EmailSettings emailSettings) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.sender = sender;
        this.receiver = receiver;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorization = authorization;
        this.subject = subject;
        this.content = content;
        this.emailSettings = emailSettings;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EmailSettings getEmailSettings() {
        return emailSettings;
    }

    public void setEmailSettings(EmailSettings emailSettings) {
        this.emailSettings = emailSettings;
    }

    @Override
    public String toString() {
        return "EmailDto{" +
                "id=" + id +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorization='" + authorization + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", emailSettings=" + emailSettings +
                '}';
    }
}
