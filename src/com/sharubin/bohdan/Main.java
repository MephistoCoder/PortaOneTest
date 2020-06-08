package com.sharubin.bohdan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
/*
1. Считать данные
1.1 Во время считывания можно вычислить среднее
1.2 мин/макс
1.3 последовательность ++
1.4 последовательность --
2. Найти медиану
3. <90 sec
 */

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        ArrayList<Long> data = new ArrayList<>();
        File file = new File("./res/10m.txt");
        long min = -1, max = -1, sequence_increase = 1, sequence_decrease = 1, median = 0;
        double average = 0.0;
        long max_increase = 1, max_decrease = 1;
        long current;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String dataRead;
            dataRead = bufferedReader.readLine();
            min = max = Long.parseLong(dataRead);
            for ( int i = 0; dataRead != null; i++, dataRead = bufferedReader.readLine()) {

                data.add(Long.parseLong(dataRead));
                current = data.get(i);
                min = Math.min(min, current);
                max = Math.max(max, current);
                average += current;
                if (i != 0) {
                    if (data.get(i - 1) > current ) {
                        max_increase = Math.max(max_increase, sequence_increase);
                        sequence_increase = 1;
                        sequence_decrease++;
                    } else {
                        max_decrease = Math.max(max_decrease, sequence_decrease);
                        sequence_decrease = 1;
                        sequence_increase++;
                    }
                }
            }
            average /= data.size();
            Collections.sort(data);
            if (data.size() % 2 == 0)
                median = (data.get((data.size() - 1) / 2 - 1) + data.get(data.size()) / 2);
            else
                median = data.get(data.size() / 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;
        System.out.println("Time of execution: " + time / 1000.0 + " sec");
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Count of increasing sequence: " + max_increase );
        System.out.println("Count of decreasing sequence: " + max_decrease );
        System.out.println("Average: " + average);
        System.out.println("Median: " + median );
    }
}
