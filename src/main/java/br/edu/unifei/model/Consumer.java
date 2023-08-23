package br.edu.unifei.model;

import javax.jms.*;
import java.util.logging.Logger;

public class Consumer {
    private final Session session;
    private final MessageConsumer messageConsumer;
    private final String name;

    public Consumer(Connection conn, String topic, String name) {
        this.name = name;
        try {
            this.session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(topic);
            this.messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new HandleMessage(name));
        } catch (JMSException e) {
            Logger logger = Logger.getLogger(Consumer.class.getName());
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return this.name;
    }

    public void close() throws JMSException {
        this.messageConsumer.close();
        this.session.close();
    }
}