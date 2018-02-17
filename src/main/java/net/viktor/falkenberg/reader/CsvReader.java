package net.viktor.falkenberg.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<String> readFile(String path) throws IOException {
        List<String> resultRead = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String lineFile;
            while ((lineFile = bufferedReader.readLine()) != null) {
                resultRead.add(lineFile);
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
        }
        return resultRead;
    }
}
