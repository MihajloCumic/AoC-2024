package day11;

import java.util.*;

public class Part2 {
    private static String input = "6 11 33023 4134 564 0 8922422 688775";
    public static void main(String[] args) {
        List<String> stones = Arrays.stream(input.split(" "))
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .toList();
        Map<String, Long> count = new HashMap<>();
        for(String stone: stones){
            count.put(stone, 1L);
        }
        for(int i = 0; i < 75; i++){
            Map<String, Long> nextCount = new HashMap<>();
            for(Map.Entry<String, Long> entry: count.entrySet()){
                List<String> nextStones = BlinkUtil.blink(entry.getKey());
                for(String stone: nextStones){
                    nextCount.put(stone, nextCount.getOrDefault(stone, 0L) + entry.getValue());
                }
            }
            count = nextCount;
        }
        long sum = 0;
        for(Long cnt: count.values()){
            sum += cnt;
        }
        System.out.println(sum);

    }





}
