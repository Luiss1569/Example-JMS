package br.edu.unifei.factory;

import javax.jms.Connection;
import org.apache.activemq.spring.ActiveMQConnectionFactory;

public class Activemq {

    static Connection conn = null;

    public static Connection getConnection() {

        if (conn == null) {
            conn = createConnection();
        }

        return conn;
    }

    static private Connection createConnection() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(System.getenv("BROKER"));
        try {
            Connection con = connectionFactory.createConnection();
            con.start();

            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
