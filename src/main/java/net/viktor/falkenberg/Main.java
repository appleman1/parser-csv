package net.viktor.falkenberg;

import net.viktor.falkenberg.parser.CsvParser;
import net.viktor.falkenberg.writer.CsvWriter;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * Входные данные подаются через аргументы.
 * Первый аргумент путь к папке для выходных файлов.
 * Остальные аргументы пути к файлам csv.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ConcurrentHashMap<String, Set<String>> map = new ConcurrentHashMap<>();
        CompletableFuture[] futures = new CompletableFuture[args.length -1];
        for (int i = 1; i < args.length; i++) {
            CsvParser csvParser = new CsvParser(args[i], map);
            futures[i-1] = CompletableFuture.runAsync(csvParser);
        }
        CompletableFuture.allOf(futures).get();
        CsvWriter.writeFile(args[0], map);
    }
}

