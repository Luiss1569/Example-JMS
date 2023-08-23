package br.edu.unifei.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateFile {
    private String path = "./";
    private final String fileName;

    public UpdateFile(String fileName) {
        this.fileName = fileName;

    }

    public UpdateFile(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public void update(String content) {
        File arq = new File(path + fileName);

        try {
            if (!arq.exists()) {
                arq.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(arq, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(content);

            printWriter.flush();

            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
