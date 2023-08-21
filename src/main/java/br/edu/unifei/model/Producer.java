package br.edu.unifei.model;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Producer {
    private final Connection conn;
    private final String topic;

    public Producer(Connection conn, String topic) {
        this.conn = conn;
        this.topic = topic;
    }

    public void sendMessage(String txt) {
        try {
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic(topic);

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String text = "[" + Thread.currentThread().getName() + " : " + this.hashCode() + ": " +  " " + txt;
            TextMessage message = session.createTextMessage(text);

            producer.send(message);
            session.close();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

}
