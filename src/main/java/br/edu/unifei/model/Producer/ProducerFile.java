package br.edu.unifei.model.Producer;

import javax.jms.*;

public class ProducerFile extends Producer<String> {
    public ProducerFile(Connection conn, String topic) {
        super(conn, topic);
    }

    @Override
    public Message createMessage(Session session, String txt) throws JMSException {
        String text = this.hashCode() + ": " + " " + txt;
        TextMessage message = session.createTextMessage(text);

        return message;
    }
}
