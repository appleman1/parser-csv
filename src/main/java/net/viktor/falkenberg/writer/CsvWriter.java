package net.viktor.falkenberg.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс для записи результата обработки.
 */
public class CsvWriter {
    /**
     * Метод записывает результат в файлы формата .csv, имя файла это ключ map а значение
     * map это параметры записываемые в файл.
     *
     * @param folderPathForWriting путь к папке в которую записываются файлы.
     * @param map                  результат для записи.
     */
    public static void writeFile(String folderPathForWriting, ConcurrentHashMap<String, Set<String>> map) {
        for (Map.Entry<String, Set<String>> pair : map.entrySet()) {
            String fileName = folderPathForWriting + File.separator + pair.getKey() + ".csv";
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
                for (String str : pair.getValue()) {
                    bufferedWriter.write(str + ";");
                }
                bufferedWriter.newLine();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
