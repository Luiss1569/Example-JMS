package br.edu.unifei.model.Consumer;

import br.edu.unifei.utils.UpdateFile;

import javax.jms.*;
import java.io.IOException;

public class ConsumerFile extends Consumer {

    public ConsumerFile(Connection conn, String topic) {
        super(conn, topic, new HandleEvent(new UpdateFile("database.txt")));
    }

    private static class HandleEvent implements MessageListener {

        private UpdateFile updateFile;

        public HandleEvent(UpdateFile updateFile) {
            this.updateFile = updateFile;
        }

        @Override
        public void onMessage(Message message) {
            TextMessage textMessage = (TextMessage) message;

            try {
                this.updateFile.update(textMessage.getText());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
