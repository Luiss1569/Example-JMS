package br.edu.unifei.model;

import br.edu.unifei.utils.UpdateFile;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.ArrayList;
import java.util.List;

public class HandleMessage implements MessageListener {
    private final UpdateFile updateFile;
    private final String name;

    public HandleMessage(String name) {
        this.name = name;
        this.updateFile = new UpdateFile("./", name + ".txt");
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            System.out.print(objectMessage.getJMSDestination() + " | ");
            TransferMessage transferMessage = (TransferMessage) objectMessage.getObject();

            switch (transferMessage.command()) {
                case "message" -> handleTextMessage(transferMessage);
                case "file" -> handleFileMessage(transferMessage);
                case "sum" -> handleSumMessage(transferMessage);
                default -> System.out.println("Command not found");
            }

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleTextMessage(TransferMessage message) {
        System.out.println("[msg:" + this.name + "] - " + message.sender() + ": " + message.content());
    }

    private void handleFileMessage(TransferMessage message) {
        try {
            System.out.println("[file:" + this.name + "] - " + message.sender() + ": " + message.content());
            this.updateFile.update(message.content());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Integer> handleListMessage(TransferMessage message) {
        var numbers = message.content().substring(1 ).split(" ");
        var list = new ArrayList<Integer>();

        for (var number : numbers) {
            list.add(Integer.parseInt(number));
        }

        return list;
    }

    private void handleSumMessage(TransferMessage message) {
        var numbers = handleListMessage(message);
        var sum = numbers.stream().reduce(0, Integer::sum);

        System.out.println("[sum:" + this.name + "] - " + message.sender() + ": " + message.content() + " = " + sum);
    }

}