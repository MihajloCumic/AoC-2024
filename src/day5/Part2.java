package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Map<Integer, List<Integer>> rulesMap = loadRules();
        List<List<Integer>> pagesList = loadPages();

        int sumCorrect = 0;
        int sumIncorrect = 0;
        for(List<Integer> pages: pagesList){
            List<Integer> prevPageNums = new ArrayList<>();
            boolean isCorrect = true;
            for(int i = 0; i < pages.size(); i++){
                prevPageNums.add(pages.get(i));
                if(hasCommonElements(prevPageNums, rulesMap.get(pages.get(i)))){
                    isCorrect = false;
                    break;
                }
            }
            if(isCorrect) {
                sumCorrect += pages.get((pages.size() / 2));
                continue;
            }
            List<Integer> correctlyOrderedPages = khansAlgorithm(pages, rulesMap);
            sumIncorrect += correctlyOrderedPages.get(correctlyOrderedPages.size() / 2);
        }
        System.out.println("Correct sum: " + sumCorrect);
        System.out.println("Incorrect sum: " + sumIncorrect);
    }

    private static List<Integer> khansAlgorithm(List<Integer> pages, Map<Integer, List<Integer>> pagesGraph){
        Map<Integer, Integer> inDegrees = new HashMap<>();
        for(Integer page: pages){
            inDegrees.put(page, 0);
        }
        for(Integer page: pages){
            List<Integer> dependentPages = pagesGraph.get(page);
            for(Integer dependentPage: dependentPages){
                if(inDegrees.containsKey(dependentPage)){
                    inDegrees.put(dependentPage, inDegrees.get(dependentPage) + 1);
                }
            }
        }
        //KHANS ALGORITHM
        Queue<Integer> queue = new LinkedList<>();
        for (Integer page: inDegrees.keySet()){
            if(inDegrees.get(page) == 0){
                queue.offer(page);
            }
        }
        List<Integer> correctlyOrderedPages = new ArrayList<>();
        while (!queue.isEmpty()){
            int page = queue.poll();
            correctlyOrderedPages.add(page);

            List<Integer> dependentPages = pagesGraph.get(page);
            for(Integer dependentPage: dependentPages){
                if(inDegrees.containsKey(dependentPage)){
                    inDegrees.put(dependentPage, inDegrees.get(dependentPage) - 1);
                    if(inDegrees.get(dependentPage) == 0){
                        queue.offer(dependentPage);
                    }

                }
            }
        }
        if(correctlyOrderedPages.size() != inDegrees.size()) throw new RuntimeException("Cyclic graph.");
        return correctlyOrderedPages;
    }

    private static boolean hasCommonElements(List<Integer> list1, List<Integer> list2){
        List<Integer> smaller = list1.size() > list2.size() ? list2 : list1;
        List<Integer> larger = list1.size() > list2.size() ? list1 : list2;

        Set<Integer> set = new HashSet<>(smaller);
        for(Integer num: larger){
            if(set.contains(num)) return true;
        }
        return false;
    }

    private static List<List<Integer>> loadPages() throws FileNotFoundException {
        String pagesPath = "resources/input2-day-5.txt";
        File pagesFile = new File(pagesPath);

        Scanner scanner = new Scanner(pagesFile);
        List<List<Integer>> pagesList = new ArrayList<>();
        while (scanner.hasNextLine()){
            String[] pagesStr = scanner.nextLine().split(",");
            List<Integer> linePages = new ArrayList<>();
            for(String str: pagesStr){
                linePages.add(Integer.valueOf(str));
            }
            pagesList.add(linePages);
        }
        return pagesList;
    }

    private static Map<Integer, List<Integer>> loadRules() throws FileNotFoundException {
        String rulesPath = "resources/input1-day-5.txt";
        File ruleFile = new File(rulesPath);

        Map<Integer, List<Integer>> ruleMap = new HashMap<>();
        Scanner scanner = new Scanner(ruleFile);
        while(scanner.hasNextLine()){
            String[] numStr = scanner.nextLine().split("\\|");
            if(numStr.length != 2) throw new RuntimeException("Wrong text format for rules.");

            Integer firstNum = Integer.valueOf(numStr[0]);
            Integer secondNum = Integer.valueOf(numStr[1]);
            if(!ruleMap.containsKey(firstNum)){
                ruleMap.put(firstNum, new ArrayList<>());
            }
            ruleMap.get(firstNum).add(secondNum);
        }
        return ruleMap;
    }
}
