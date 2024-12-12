package day12;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import static day12.Garden.*;

public class Part1 {

    public static void main(String[] args) throws IOException {
        char[][] garden = initializeGarden();
        boolean[][] visited = new boolean[garden.length][garden[0].length];
        long result = 0;
        for(int i = 0; i < garden.length; i++){
            for(int j = 0; j < garden[0].length; j++){
                if(visited[i][j]) continue;
                List<Pair> region = bfsRegion(new Pair(i, j), garden, visited);
                result += (long) region.size() * numOfFences(region, garden);
            }
        }
        System.out.println(result);
        
    }

    private static int numOfFences(List<Pair> region, char[][] garden){
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
