package net.viktor.falkenberg;

import net.viktor.falkenberg.parser.CsvParser;
import net.viktor.falkenberg.reader.CsvReader;
import net.viktor.falkenberg.writer.CsvWriter;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Входные данные подаются через аргументы.
 * Первый аргумент путь к папке для выходных файлов.
 * Остальные аргументы пути к файлам csv.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConcurrentHashMap<String, Set<String>> map = new ConcurrentHashMap<>();
        for (int i = 1; i < args.length; i++) {
            new CsvParser(args[i], new CsvReader(), map).run();
        }
        CsvWriter.writeFile(args[0], map);
    }
}
