package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException {
        int[][] trails = initializeTrails();
        int sum = 0;
        for(int i = 0; i < trails.length; i++){
            for(int j = 0; j < trails[0].length; j++){
                if(trails[i][j] == 0){
                    sum += DFS.part1(trails, new boolean[trails.length][trails[0].length], i, j, 0);
                }
            }
        }
        System.out.println(sum);
    }

    static int[][] initializeTrails() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader("resources/input-day-10.txt"))){
            List<int[]> lines = new ArrayList<>();
            reader.lines().forEach(line -> {
                char[] heightsChar = line.toCharArray();
                int[] heights = new int[heightsChar.length];
                for(int i = 0; i < heightsChar.length; i++){
                    if(Character.isDigit(heightsChar[i])){
                        heights[i] = Integer.parseInt(String.valueOf(heightsChar[i]));
                        continue;
                    }
                    throw new RuntimeException("Invalid input");
                }
                lines.add(heights);
            });
            return lines.toArray(new int[0][]);
        }

    }

}
