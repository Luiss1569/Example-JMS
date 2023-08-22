package br.edu.unifei;

import br.edu.unifei.factory.Activemq;
import br.edu.unifei.model.Arithmetic;
import br.edu.unifei.model.Consumer.Consumer;
import br.edu.unifei.model.Consumer.ConsumerArithmetic;
import br.edu.unifei.model.Consumer.ConsumerFile;
import br.edu.unifei.model.Consumer.ConsumerMessage;
import br.edu.unifei.model.Producer.Producer;
import br.edu.unifei.model.Producer.ProducerArithmetic;
import br.edu.unifei.model.Producer.ProducerFile;
import br.edu.unifei.model.Producer.ProducerMessage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var conn = Activemq.getConnection();
        var scanner = new Scanner(System.in);

        var pm = new ProducerMessage(conn, "messages");
        var pf = new ProducerFile(conn, "files");
        var pa = new ProducerArithmetic(conn, "arithmetic");
        var cm = new ConsumerMessage(conn, "messages");
        var cf = new ConsumerFile(conn, "files");
        var ca = new ConsumerArithmetic(conn, "arithmetic");

        while (true) {
            String input = scanner.nextLine();

            String operation = input.split(" ")[0];

            if (operation.equals("sum") || operation.equals("sub") || operation.equals("mul") || operation.equals("div")) {

                if (input.split(" ").length != 3) {
                    System.out.println("Input invalid");
                    continue;
                }

                var numbers = input.substring(operation.length() + 1).split(" ");
                var arithmetic = new Arithmetic(numbers, operation);
                pa.send(arithmetic);

            }else{
                pm.send(input);
                pf.send(input);
            }

            if (input.equals("exit")) {
                break;
            }


        }

    }
}