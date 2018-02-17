package net.viktor.falkenberg;

import net.viktor.falkenberg.parser.CsvParser;
import net.viktor.falkenberg.reader.CsvReader;
import net.viktor.falkenberg.writer.CsvWriter;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConcurrentHashMap<String,Set<String>> map = new ConcurrentHashMap<>();
        for (String arg:args) {
            new CsvParser(arg,new CsvReader(),map).run();
        }
        CsvWriter.writeFile("C:\\1",map);
    }
}
