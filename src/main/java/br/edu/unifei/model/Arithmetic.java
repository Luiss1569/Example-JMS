package br.edu.unifei.model;

import java.io.Serializable;
import java.util.List;

public class Arithmetic implements Serializable {
    private List<Integer> numbers;
    private String operation;

    public Arithmetic(String[] numbers, String operation) {
        this.numbers = List.of(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
        this.operation = operation;
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
