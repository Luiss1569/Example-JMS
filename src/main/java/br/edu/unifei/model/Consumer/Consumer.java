package br.edu.unifei.model.Consumer;

import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.JMSException;

public abstract class Consumer {
    private final Connection conn;
    private final Session session;
    private final MessageConsumer consumer;
    private final String topic;

    public Consumer(Connection conn, String topic, MessageListener HandleEvent) {
        this.conn = conn;
        this.topic = topic;
        try {
            this.session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.consumer = session.createConsumer(session.createTopic(topic));
            consumer.setMessageListener(HandleEvent);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            this.consumer.close();
            this.session.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}