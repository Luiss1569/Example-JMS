package br.edu.unifei.model.Producer;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Message;

public abstract class Producer<T> {
    private final Connection conn;
    private final String topic;

    public Producer(Connection conn, String topic) {
        this.conn = conn;
        this.topic = topic;

    }

    public void send(T obj) {
        try {
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic(topic);

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            Message message = createMessage(session, obj);

            producer.send(message);
            session.close();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    public abstract Message createMessage(Session session, T obj) throws JMSException;

    public void close() {
        try {
            conn.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
