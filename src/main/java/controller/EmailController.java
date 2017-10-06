package controller;

import service.MessageConsumerService;
import service.MessagePublisherService;
import service.impl.MessageConsumerServiceImpl;
import model.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private MessagePublisherService messagePublisherService;
    @Autowired
    private MessageConsumerService messageConsumerService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public String send(@RequestBody EmailDto emailDto) {
        Integer id = emailDto.getId();
        String host = emailDto.getHost();
        String port = emailDto.getPort();
        String sender = emailDto.getSender();
        String receiver = emailDto.getReceiver();
        String username = emailDto.getUsername();
        String password = emailDto.getPassword();
        String authorization = emailDto.getAuthorization();
        String name = emailDto.getName();
        String subject = emailDto.getSubject();
        String content = emailDto.getContent();
        try {
            messagePublisherService.publish(new EmailDto(id, host, port, sender, receiver, name,
                    username, password, authorization, subject, content));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "邮件发送成功";
    }

    @RequestMapping(value = "/sendMutiple", method = RequestMethod.POST)
    @ResponseBody
    public String sendMutiple(@RequestBody EmailDto emailDto) {
        Integer id = emailDto.getId();
        String host = emailDto.getHost();
        String port = emailDto.getPort();
        String sender = emailDto.getSender();
        String receiver = emailDto.getReceiver();
        String username = emailDto.getUsername();
        String password = emailDto.getPassword();
        String authorization = emailDto.getAuthorization();
        String name = emailDto.getName();
        String subject = emailDto.getSubject();
        String content = emailDto.getContent();
        try {
            messagePublisherService.publish(new EmailDto(id, host, port, sender, receiver, username,
                    password, authorization, name, subject, content));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "邮件发送成功";
    }

    @RequestMapping(value = "/openConsumer", method = RequestMethod.POST)
    @ResponseBody
    public String openConsumer() {
        try {
            messageConsumerService.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "邮件消息队列开启成功";
    }

    public static void main(String[] args) {
        MessageConsumerServiceImpl messageConsumer = new MessageConsumerServiceImpl();
        try {
            messageConsumer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
