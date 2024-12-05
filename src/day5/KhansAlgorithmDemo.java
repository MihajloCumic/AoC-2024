package day5;

import java.io.FileNotFoundException;
import java.util.*;

public class KhansAlgorithmDemo {
    
    public static void main(String[] args) throws FileNotFoundException {
       Map<Integer, List<Integer>> pagesGraph = new HashMap<>();

       pagesGraph.put(97, List.of(13, 47, 29));
       pagesGraph.put(75, List.of(13, 47));
       pagesGraph.put(47, List.of(13, 29));
       pagesGraph.put(29, List.of(13));
       pagesGraph.put(13, List.of());

       Map<Integer, Integer> inDegree = new HashMap<>();
       for(Integer page: pagesGraph.keySet()){
           inDegree.put(page, 0);
       }

       for(Map.Entry<Integer, List<Integer>> entry: pagesGraph.entrySet()){
           for(Integer page: entry.getValue()){
               inDegree.put(page, inDegree.get(page) + 1);
           }
       }
       //Khans algorithm
        Queue<Integer> queue = new LinkedList<>();
       for(Integer key: inDegree.keySet()){
           if(inDegree.get(key) == 0){
               queue.add(key);
           }
       }
       List<Integer> result = new ArrayList<>();
       while (!queue.isEmpty()){
           Integer page = queue.poll();
           result.add(page);
           List<Integer> dependentPages = pagesGraph.get(page);
           for(Integer dependentPage: dependentPages){
               inDegree.put(dependentPage, inDegree.get(dependentPage) - 1);
               if(inDegree.get(dependentPage) == 0){
                   queue.offer(dependentPage);
               }
           }
       }

       if(result.size() != inDegree.size()){
           throw new RuntimeException("Cyclic graph.");
       }

       for(Integer page: result){
           System.out.println(page);
       }
    }
}
