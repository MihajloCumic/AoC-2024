package day10;

import java.io.IOException;
import static day10.Part1.*;
public class Part2 {

    public static void main(String[] args) throws IOException {
        int[][] trails = initializeTrails();
        int sum = 0;
        for(int i = 0; i < trails.length; i++){
            for(int j = 0; j < trails[0].length; j++){
                if(trails[i][j] == 0){
                    sum += DFS.part2(trails, i, j, 0);
                }
            }
        }
        System.out.println(sum);
    }
}
