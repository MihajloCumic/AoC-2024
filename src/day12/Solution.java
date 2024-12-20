package day12;

import java.io.IOException;
import java.util.*;
import static day12.Garden.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        char[][] garden = initializeGarden();
        boolean[][] visited = new boolean[garden.length][garden[0].length];
        long result = 0;
        for(int i = 0; i < garden.length; i++){
            for(int j = 0; j < garden[0].length; j++){
                if(visited[i][j]) continue;
                List<Pair> region = bfsRegion(new Pair(i, j), garden, visited);
                result += (long) region.size() * numbOfFencesPart2(region, garden);
            }
        }
        System.out.println(result);
        
    }

    private static int numOfFencesPart1(List<Pair> region, char[][] garden){
        int fencesCnt = 0;
        for(Pair plot: region){
            int row = plot.row;
            int col = plot.col;
            char regionChar = garden[row][col];

            int rowUp = row - 1;
            if(rowUp < 0
                    || garden[rowUp][col] != regionChar) fencesCnt++;

            int rowDown = row + 1;
            if(rowDown >= garden.length
                    || garden[rowDown][col] != regionChar) fencesCnt++;

            int colLeft = col - 1;
            if(colLeft < 0
                    || garden[row][colLeft] != regionChar) fencesCnt++;

            int colRight = col + 1;
            if(colRight >= garden[0].length
                    || garden[row][colRight] != regionChar) fencesCnt++;
        }
        return fencesCnt;
    }
    private static int numbOfFencesPart2(List<Pair> region, char[][] garden){
        List<Pair> verLeft = new ArrayList<>();
        List<Pair> verRight = new ArrayList<>();
        List<Pair> horAbove = new ArrayList<>();
        List<Pair> horBelow = new ArrayList<>();
        for(Pair plot: region){
            int row = plot.row;
            int col = plot.col;
            char regionChar = garden[row][col];

            int rowUp = row - 1;
            if(rowUp < 0
                    || garden[rowUp][col] != regionChar) {
                horAbove.add(plot);
            }

            int rowDown = row + 1;
            if(rowDown >= garden.length
                    || garden[rowDown][col] != regionChar) {
                horBelow.add(plot);
            }

            int colLeft = col - 1;
            if(colLeft < 0
                    || garden[row][colLeft] != regionChar) {
                verLeft.add(plot);
            }

            int colRight = col + 1;
            if(colRight >= garden[0].length
                    || garden[row][colRight] != regionChar) {
                verRight.add(plot);
            }
        }

        int section1 = numOfSectionsVertical(verLeft);
        int section2 = numOfSectionsVertical(verRight);
        int section3 =  numOfSectionsHorizontal(horAbove);
        int section4 = numOfSectionsHorizontal(horBelow);
        return section1 + section2 + section3 + section4;


    }

    private static int numOfSectionsVertical(List<Pair> fencesVertical){
        if(fencesVertical.size() == 1) return 1;
        Pair.sortByCol(fencesVertical);
        Pair prevFence = fencesVertical.get(0);
        int sectionNum = 1;
        for(int i = 1; i < fencesVertical.size(); i++){
            Pair currFence = fencesVertical.get(i);
            if(currFence.row - 1 == prevFence.row && currFence.col == prevFence.col){
                prevFence = currFence;
                continue;
            }
            sectionNum++;
            prevFence = currFence;
        }

        return sectionNum;
    }

    private static int numOfSectionsHorizontal(List<Pair> fencesHorizontal){
        if(fencesHorizontal.size() == 1) return 1;
        Pair.sortByRow(fencesHorizontal);
        Pair prevFence = fencesHorizontal.get(0);
        int sectionNum = 1;
        for(int i = 1; i < fencesHorizontal.size(); i++){
            Pair currFence = fencesHorizontal.get(i);
            if(currFence.col - 1 == prevFence.col && currFence.row == prevFence.row){
                prevFence = currFence;
                continue;
            }
            sectionNum++;
            prevFence = currFence;
        }

        return sectionNum;
    }
    private static List<Pair> bfsRegion(Pair pair, char[][] garden, boolean[][] visited){
        Queue<Pair> queue = new LinkedList<>();
        queue.add(pair);
        visited[pair.row][pair.col] = true;
        List<Pair> region = new ArrayList<>();
        while(!queue.isEmpty()){
            Pair currPair = queue.poll();
            region.add(currPair);
            List<Pair> neighbours = getNeighbours(currPair, garden);
            for (Pair neighbour: neighbours){
                if(visited[neighbour.row][neighbour.col]) continue;
                queue.add(neighbour);
                visited[neighbour.row][neighbour.col] = true;
            }
        }
        return region;

    }

    private static List<Pair> getNeighbours(Pair pair, char[][] garden){
        int newRowUp = pair.row - 1;
        int newRowDown = pair.row + 1;
        int newColLeft = pair.col - 1;
        int newColRight = pair.col + 1;
        List<Pair> neighbours = new ArrayList<>();
        char currChar = garden[pair.row][pair.col];
        if(newRowUp >= 0 && garden[newRowUp][pair.col] == currChar){
            neighbours.add(new Pair(newRowUp, pair.col));
        }
        if(newRowDown < garden.length && garden[newRowDown][pair.col] == currChar){
            neighbours.add(new Pair(newRowDown, pair.col));
        }
        if(newColLeft >= 0 && garden[pair.row][newColLeft] == currChar){
            neighbours.add(new Pair(pair.row, newColLeft));
        }
        if(newColRight < garden[0].length && garden[pair.row][newColRight] == currChar){
            neighbours.add(new Pair(pair.row, newColRight));
        }
        return neighbours;
    }
}
