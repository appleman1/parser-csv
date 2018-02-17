package net.viktor.falkenberg.parser;

import net.viktor.falkenberg.reader.CsvReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvParser extends Thread {

    private String pathReadFile;
    private CsvReader csvReader;
    private ConcurrentHashMap<String, Set<String>> mapForWrite;

    public CsvParser(String pathReadFile, CsvReader csvReader, ConcurrentHashMap<String, Set<String>> mapForWrite) {
        this.pathReadFile = pathReadFile;
        this.csvReader = csvReader;
        this.mapForWrite = mapForWrite;
    }

    @Override
    public void run() {
        try {
            List<String> file = csvReader.readFile(pathReadFile);
            Stream<String> streamHeaders = Arrays.stream(file.get(0).split(";"));
            List<String> headers = streamHeaders.collect(Collectors.toList());
            streamHeaders.forEach(x->mapForWrite.putIfAbsent(x,new HashSet<>()));

            for (int i = 1; i < file.size(); i++) {
                for (int j = 0; j < headers.size(); j++) {
                    String[] arrayValue = file.get(i).split(";");
                    Set<String> setThisKey = mapForWrite.get(headers.get(j));
                    setThisKey.add(arrayValue[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
