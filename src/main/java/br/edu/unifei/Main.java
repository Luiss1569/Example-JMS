package br.edu.unifei;

import br.edu.unifei.factory.Activemq;
import br.edu.unifei.model.Consumer;
import br.edu.unifei.model.Producer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var conn = Activemq.getConnection();
        var scanner = new Scanner(System.in);

        var p = new Producer(conn, "messages", "producer");
        var c = new Consumer(conn, "messages", "consumer");

        while (true) {
            var command = scanner.next();
            var message = scanner.nextLine();

            p.send(command, message);
        }

    }
}