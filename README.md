# 简介
本 Demo 采用 RabbitMQ + javax.email 实现邮件队列发送功能。

# 使用
按照如下JSON格式发送 POST 请求到 `http://localhost:8080/email/send`
```json
{
	"id":1,
	"host":"smtp.163.com",
	"port":"25",
	"sender":"xxx@163.com",
	"receiver":[
        "yyy@gmail.com",
        "zzz@gmail.com"
    ],
	"username":"xxx@163.com",
	"password":"password",
	"authorization":"email_authorization_code",
	"name":"Your Name",
	"subject":"我是主题",
	"content":"我是内容"
}
```