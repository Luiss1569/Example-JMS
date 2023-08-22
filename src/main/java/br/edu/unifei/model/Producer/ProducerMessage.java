package br.edu.unifei.model.Producer;

import javax.jms.*;

public class ProducerMessage extends Producer<String> {
    public ProducerMessage(Connection conn, String topic) {
        super(conn, topic);
    }

    @Override
    public Message createMessage(Session session, String txt) throws JMSException {
        String text = Thread.currentThread().getName() + " : " + this.hashCode() + ": " +  " " + txt;
        TextMessage message = session.createTextMessage(text);

        return message;
    }
}
