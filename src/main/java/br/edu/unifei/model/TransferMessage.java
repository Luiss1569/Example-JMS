package br.edu.unifei.model;

import java.io.Serializable;

public record TransferMessage(String sender, String command, String content) implements Serializable {

}
