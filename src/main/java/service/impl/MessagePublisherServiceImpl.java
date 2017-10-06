package service.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import model.EmailDto;
import service.MessagePublisherService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static util.MessageUtils.toBytes;

@Service
public class MessagePublisherServiceImpl implements MessagePublisherService {
    private final static String QUEUE_NAME = "email_queue";
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public MessagePublisherServiceImpl() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void publish(EmailDto emailDto) throws IOException {
        channel.basicPublish("", QUEUE_NAME, null, toBytes(emailDto));
        System.out.println("[x] Sent '" + emailDto + "'");
    }

    @Override
    public void finalize() {
        System.out.println("Destroy!!!");
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
