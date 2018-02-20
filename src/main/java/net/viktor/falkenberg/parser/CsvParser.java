package net.viktor.falkenberg.parser;


import net.viktor.falkenberg.reader.CsvReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Реализация многопоточной обработки.
 * Один поток - один файл.
 */
public class CsvParser implements Runnable, CsvReader {
    /**
     * путь к файлу для чтения
     */
    private String pathReadFile;

    /**
     * Коллекция результатов работы
     * ключ- заголовок
     * значение - набор параметров соответствующий данному заголовку
     */
    private ConcurrentHashMap<String, Set<String>> mapForWrite;

    /**
     * конструктор
     *
     * @param pathReadFile путь к файлу для чтения
     * @param mapForWrite  Коллекция результатов работы
     */
    public CsvParser(String pathReadFile, ConcurrentHashMap<String, Set<String>> mapForWrite) {
        this.pathReadFile = pathReadFile;
        this.mapForWrite = mapForWrite;
    }

    /**
     * единичный поток в котором производится чтение файла и обработка результата,
     * который записывается в {@link ConcurrentHashMap<String,Set<String>>}
     */
    @Override
    public void run() {
        List<String> file = readFile(pathReadFile);
        List<String> headers = Arrays.stream(file.get(0).split(";"))
                .peek(x -> mapForWrite.putIfAbsent(x, new HashSet<>()))
                .collect(Collectors.toList());
        for (int i = 1; i < file.size(); i++) {
            for (int j = 0; j < headers.size(); j++) {
                String[] arrayValue = file.get(i).split(";");
                Set<String> setThisKey = mapForWrite.get(headers.get(j));
                setThisKey.add(arrayValue[j]);
            }
        }
    }
}
