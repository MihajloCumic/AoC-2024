package day8;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import static day8.Part1.*;

public class Part2 {
    public static void main(String[] args) throws IOException {
        City city  = initializeAntennas();
        Map<Character, List<Antenna>> antennasMap = city.antennas;
        int[][] antinodes = new int[city.rowSize][city.colSize];
        for(Map.Entry<Character, List<Antenna>> entry: antennasMap.entrySet()){
            List<Antenna> antennas = entry.getValue();
            for(int i = 0; i < antennas.size(); i++){
                Antenna currAntenna = antennas.get(i);
                for(int j = i + 1; j < antennas.size(); j++){
                    Antenna nextAntenna = antennas.get(j);
                    int rowDiff = Math.abs(currAntenna.row - nextAntenna.row);
                    int colDiff = Math.abs(currAntenna.col - nextAntenna.col);

                    int upperRow = Math.min(currAntenna.row, nextAntenna.row);
                    int downRow = Math.max(currAntenna.row, nextAntenna.row);
                    int leftCol = Math.min(currAntenna.col, nextAntenna.col);
                    int rightCol = Math.max(currAntenna.col, nextAntenna.col);

                    int newRow1 = -1;
                    int newRow2 = -1;
                    int newCol1 = -1;
                    int newCol2 = -1;

                    if(rowDiff == 0){
                        newRow1 = currAntenna.row;
                        newCol1 = leftCol - colDiff;
                        newCol2 = rightCol + colDiff;
                        if(isInBoundsRow(city, newRow1)) {
                            if(isInBoundsCol(city, newCol1 + colDiff)){
                                antinodes[newRow1][newCol1 + colDiff] = 1;
                            }
                            while(isInBoundsCol(city, newCol1)){
                                antinodes[newRow1][newCol1] = 1;
                                newCol1 -= colDiff;
                            }
                            if(isInBoundsCol(city, newCol2 - colDiff)){
                                antinodes[newRow1][newCol2 - colDiff] = 1;
                            }
                            while(isInBoundsCol(city, newCol2)){
                                antinodes[newRow1][newCol2] = 1;
                                newCol2 += colDiff;
                            }

                        }
                        continue;
                    }


                    if(colDiff == 0){
                        newCol1 = currAntenna.col;
                        newRow1 = upperRow - rowDiff;
                        newRow2 = downRow + rowDiff;
                        if(isInBoundsCol(city, newCol1)){
                            if(isInBoundsRow(city, newRow1 + rowDiff)){
                                antinodes[newRow1 + rowDiff][newCol1] = 1;
                            }
                            while(isInBoundsRow(city, newRow1)){
                                antinodes[newRow1][newCol1] = 1;
                                newRow1 -= rowDiff;
                            }
                            if(isInBoundsRow(city, newRow2 - rowDiff)){
                                antinodes[newRow2 - rowDiff][newCol1] = 1;
                            }
                            while(isInBoundsRow(city, newRow2)){
                                antinodes[newRow2][newCol1] = 1;
                                newRow2 += rowDiff;
                            }
                        }
                        continue;
                    }
                    //right diagonal
                    if((currAntenna.row < nextAntenna.row && currAntenna.col < nextAntenna.col)
                            || (currAntenna.row > nextAntenna.row && currAntenna.col > nextAntenna.col)){
                        //down
                        antinodes[downRow][rightCol] = 1;
                        newRow1 = downRow + rowDiff;
                        newCol1 = rightCol + colDiff;
                        while(isInBounds(city, newRow1, newCol1)){
                            antinodes[newRow1][newCol1] = 1;
                            newRow1 += rowDiff;
                            newCol1 += colDiff;
                        }
                        //up
                        antinodes[upperRow][leftCol] = 1;
                        newRow2 = upperRow - rowDiff;
                        newCol2 = leftCol - colDiff;
                        while(isInBounds(city, newRow2, newCol2)){
                            antinodes[newRow2][newCol2] = 1;
                            newRow2 -= rowDiff;
                            newCol2 -= colDiff;
                        }
                        continue;
                    }
                    //left diagonal
                    if((currAntenna.row < nextAntenna.row && currAntenna.col > nextAntenna.col)
                            || currAntenna.row > nextAntenna.row && currAntenna.col < nextAntenna.col){
                        //down
                        antinodes[downRow][leftCol] = 1;
                        newRow1 = downRow + rowDiff;
                        newCol1 = leftCol - colDiff;
                        while(isInBounds(city, newRow1, newCol1)){
                            antinodes[newRow1][newCol1] = 1;
                            newRow1 += rowDiff;
                            newCol1 -= colDiff;
                        }
                        //up
                        antinodes[upperRow][rightCol] = 1;
                        newRow2 = upperRow - rowDiff;
                        newCol2 = rightCol + colDiff;
                        while(isInBounds(city, newRow2, newCol2)){
                            antinodes[newRow2][newCol2] = 1;
                            newRow2 -= rowDiff;
                            newCol2 += colDiff;
                        }
                    }
                }
            }

        }
        int cnt = 0;
        for(int[] row: antinodes){
            for(int col: row){
                if(col == 1) cnt++;
            }
        }
        System.out.println(cnt);
    }
}
