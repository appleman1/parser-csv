package net.viktor.falkenberg.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс производит построчное чтение данных из файла.
 */
public class CsvReader {
    /**
     * @param path путь к файлу
     * @return список прочтенных строк
     * @throws IOException возникает при ошибках чтения файла.
     */
    public static List<String> readFile(String path) {
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
