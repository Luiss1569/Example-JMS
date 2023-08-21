package br.edu.unifei.model;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer {
    private final Connection conn;
    private final Session session;
    private final MessageConsumer consumer;
    private final String topic;

    public Consumer(Connection conn, String topic) {
        this.conn = conn;
        this.topic = topic;
        try {
            this.session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.consumer = session.createConsumer(session.createTopic(topic));
            consumer.setMessageListener(new HelloMessageListener());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private static class HelloMessageListener implements MessageListener {

        @Override
        public void onMessage(Message message) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Consumer " + Thread.currentThread().getName() + " received message: " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}