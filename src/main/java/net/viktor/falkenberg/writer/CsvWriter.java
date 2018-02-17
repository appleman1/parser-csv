package net.viktor.falkenberg.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CsvWriter {
    public static void writeFile(String folderPathForWriting, ConcurrentHashMap<String, Set<String>> map) {
        for (Map.Entry<String, Set<String>> pair : map.entrySet()) {
            String fileName = folderPathForWriting + "\\" + pair.getKey() +".csv";
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
                for (String str : pair.getValue()) {
                    bufferedWriter.write(str + ";");
                }
                bufferedWriter.write(System.lineSeparator());
            } catch (IOException e) {
                System.out.println("ошибка записи в файл");
            }

        }


    }
}
