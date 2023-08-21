package br.edu.unifei;

import br.edu.unifei.factory.Activemq;
import br.edu.unifei.model.Consumer;
import br.edu.unifei.model.Producer;
import org.apache.activemq.broker.BrokerService;

public class Main {
    public static void main(String[] args) {

        var conn = Activemq.getConnection();
        var p = new Producer(conn, "messages");
        var c = new Consumer(conn, "messages");

        p.sendMessage("Hello World!");

    }
}