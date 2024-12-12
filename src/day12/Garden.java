package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Garden {

    static Map<Character, List<Pair>> regions = new HashMap<>();


    static char[][] initializeGarden() throws IOException {
        List<char[]> rows = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("resources/input-day-12.txt"))){
            reader.lines().forEach(line -> {
                rows.add(line.toCharArray());
            });
        }
        return rows.toArray(new char[0][]);

    }
    static class Pair{
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
        static void sortByRow(List<Pair> pairs){
            pairs.sort(Comparator.comparingInt((Pair p) -> p.row).thenComparing(p -> p.col));
        }
        static void sortByCol(List<Pair> pairs){
            pairs.sort(Comparator.comparingInt((Pair p) -> p.col).thenComparing(p -> p.row));
        }
    }
}
