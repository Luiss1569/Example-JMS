package br.edu.unifei.model;

import javax.jms.*;
import java.util.logging.Logger;

public class Producer {
    private final Connection conn;
    private final String topic;
    private final String name;
    private final Logger logger = Logger.getLogger(Producer.class.getName());

    public Producer(Connection conn, String topic, String name) {
        this.conn = conn;
        this.topic = topic;
        this.name = name;
    }

    public void send(String command, String data) {
        try {
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic(topic);

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.setTimeToLive(300000);

            Message message = session.createObjectMessage(new TransferMessage(this.name,command, data));

            producer.send(message);
            session.close();
        }
        catch (Exception e) {
            logger.severe("Caught: " + e);
        }
    }
}