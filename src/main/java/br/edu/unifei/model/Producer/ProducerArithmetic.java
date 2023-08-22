package br.edu.unifei.model.Producer;

import br.edu.unifei.model.Arithmetic;

import javax.jms.*;
import java.io.Serializable;

public class ProducerArithmetic extends Producer<Arithmetic> {
    public ProducerArithmetic(Connection conn, String topic) {
        super(conn, topic);
    }

    @Override
    public Message createMessage(Session session, Arithmetic arithmetic) throws JMSException {

        ObjectMessage message = session.createObjectMessage(arithmetic);

        return message;
    }
}
