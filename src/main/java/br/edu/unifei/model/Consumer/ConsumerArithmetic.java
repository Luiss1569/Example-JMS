package br.edu.unifei.model.Consumer;

import br.edu.unifei.model.Arithmetic;

import javax.jms.*;

public class ConsumerArithmetic extends Consumer {

    public ConsumerArithmetic(Connection conn, String topic) {
        super(conn, topic, new HandleEvent());
    }

    private static class HandleEvent implements MessageListener {

        @Override
        public void onMessage(Message message) {
            ObjectMessage objMessage = (ObjectMessage) message;

            Arithmetic obj = null;
            try {
                obj = (Arithmetic) objMessage.getObject();
            } catch (JMSException e) {
                e.printStackTrace();
                return;
            }

            Integer result = calculate(obj);

            System.out.println("Consumer " + Thread.currentThread().getName() + " received operation: " + obj.getOperation() + " with numbers: " + obj.getNumbers() + " and result: " + result);
        }

        private Integer calculate(Arithmetic obj) {
            try {
                switch (obj.getOperation()) {
                    case "sum":
                        return obj.getNumbers().stream().reduce(0, (a, b) -> a + b);
                    case "sub":
                        return obj.getNumbers().stream().reduce(0, (a, b) -> a - b);
                    case "mul":
                        return obj.getNumbers().stream().reduce(0, (a, b) -> a * b);
                    case "div":
                        return obj.getNumbers().stream().reduce(0, (a, b) -> a / b);
                    default:
                        return 0;
                }
            } catch (Exception e) {
                return 0;
            }
        }

    }
}
