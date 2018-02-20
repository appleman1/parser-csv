package net.viktor.falkenberg.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс производит построчное чтение данных из файла.
 */
public interface CsvReader {
    /**
     * @param path путь к файлу
     * @return список прочтенных строк
     * @throws IOException возникает при ошибках чтения файла.
     */
    default List<String> readFile(String path) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
             Stream<String> stringStream = bufferedReader.lines()) {
            return stringStream.collect(Collectors.toList());

        } catch (IOException e) {
            System.out.println(e);
        }
        return Collections.emptyList();
    }
}
