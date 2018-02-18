package net.viktor.falkenberg.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ����� ���������� ���������� ������ ������ �� �����.
 */
public class CsvReader {
    /**
     * @param path ���� � �����
     * @return ������ ���������� �����
     * @throws IOException ��������� ��� ������� ������ �����.
     */
    public static List<String> readFile(String path) {
        List<String> resultRead = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String lineFile;
            while ((lineFile = bufferedReader.readLine()) != null) {
                resultRead.add(lineFile);
            }

        } catch (IOException e) {
            System.out.println("������ ��� ������ �����");
        }
        return resultRead;
    }
}
