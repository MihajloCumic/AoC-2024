package day11;

import java.util.ArrayList;
import java.util.List;

public class BlinkUtil {

    static List<String> blink(String stone){
        List<String> newStones = new ArrayList<>();
        if(stone.equals("0")){
            action1(newStones);
            return newStones;
        }
        if(stone.length() % 2 == 0){
            action2(stone, newStones);
            return newStones;
        }
        action3(stone, newStones);
        return newStones;

    }


    private static void action1(List<String> newStones){
        newStones.add("1");
    }
    private static void action2(String stone, List<String> newStones){
        int mid = stone.length() / 2;
        String firstHalf = stone.substring(0, mid);
        String secondHalf = removeTrailingZeroes(stone.substring(mid));
        newStones.add(firstHalf);
        newStones.add(secondHalf);
    }
    private static void action3(String stone, List<String> newStones){
        long newStoneValue = Long.parseLong(stone) * 2024;
        newStones.add(String.valueOf(newStoneValue));
    }

    private static String removeTrailingZeroes(String str){
        int firstNotZeroNumIndex = -1;
        char[] strArr = str.toCharArray();
        for(int i = 0; i < strArr.length; i++){
            if(strArr[i] == '0'){
                continue;
            }
            firstNotZeroNumIndex = i;
            break;
        }
        if(firstNotZeroNumIndex == -1){
            firstNotZeroNumIndex = strArr.length - 1;
        }

        return str.substring(firstNotZeroNumIndex);

    }
}
