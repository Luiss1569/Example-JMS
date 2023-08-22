package br.edu.unifei.model.Consumer;

import javax.jms.*;

public class ConsumerMessage extends Consumer {

    public ConsumerMessage(Connection conn, String topic) {
        super(conn, topic, new HandleEvent());
    }

    private static class HandleEvent implements MessageListener {

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
