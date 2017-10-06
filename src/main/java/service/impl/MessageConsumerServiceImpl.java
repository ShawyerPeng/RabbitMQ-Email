package service.impl;

import com.rabbitmq.client.*;
import model.EmailDto;
import service.MessageConsumerService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static util.MessageUtils.fromBytes;

@Service
public class MessageConsumerServiceImpl implements MessageConsumerService {
    private final static String QUEUE_NAME = "email_queue";

    private EmailSender emailSender;
    private Connection connection;
    private Channel channel;

    public MessageConsumerServiceImpl() {
        emailSender = new EmailSender();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        System.out.println("[*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                EmailDto emailDto = (EmailDto) fromBytes(body);
                System.out.println("[x] Received '" + emailDto + "'");

                emailSender.send(emailDto);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    @Override
    public void finalize() {
        try {
            if (channel != null) {
                channel.close();
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
