package service;

import model.EmailDto;

import java.io.IOException;

public interface MessagePublisherService {
    void publish(EmailDto emailDto) throws IOException;
}
