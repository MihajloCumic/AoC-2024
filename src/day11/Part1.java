package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    private static String input = "6 11 33023 4134 564 0 8922422 688775";

    public static void main(String[] args) {
        List<String> stones = Arrays.stream(input.split(" ")).toList();
        for(int i = 0; i < 25; i++){
            List<String> tmpStones = new ArrayList<>();
            for(String stone: stones){
                tmpStones.addAll(BlinkUtil.blink(stone));
            }
            stones = tmpStones;
        }
        System.out.println(stones.size());
    }


}
