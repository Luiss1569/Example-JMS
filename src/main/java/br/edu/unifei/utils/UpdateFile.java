package br.edu.unifei.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UpdateFile {
    private String path = "./";
    private String fileName;

    public UpdateFile(String fileName) {
        this.fileName = fileName;

    }

    public UpdateFile(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public void update(String content) throws IOException {
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
