package bbw.project;

import java.util.*;
import java.io.*;
//it will be board
public class Simulation {

    int width;
    int height;
    int [][] board ;


    //generate  constructor
    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.board= new int [width][height];  // initialize the board where to display cells as 2 dimensional array
    }

    public Simulation(){}

    public ArrayList <String> getCordinates(){

        ArrayList<String> coordinate= new ArrayList<>();

            File file = new File("/Users/lobsang/documents/coordinate.txt");

            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String s;
                s = in.readLine();
                while (s != null) {
                    coordinate.add(s);
                    s = in.readLine();
                }
                in.close();

            } catch (FileNotFoundException e1) {
                System.err.println("File not found: " + file);

            } catch (IOException e2) {
                e2.printStackTrace();
            }

        return (coordinate);
    }



    public void printBoard(){           // printing 2 dimensional array with defined length and width
     System.out.println("____");
        for(int y=0;y<height;y++) {
            String line = "|";                 // create a boader with this pipe symbol to both start and end of line
            for (int x = 0; x < width; x++) {
                if (this.board[x][y] == 0) {

                    line += ".";               // if the value is zero we put dot symbol as dead cell
                } else {
                    line += "*";               // otherwise  star as a alive symbol for a alive cell
                }
            }
            line += "|";                    // // create a boader with this pipe symbol to both start and end of line
            System.out.println(line);
        }

        System.out.println("----\n");

    }



    public void setAlive(int x, int y){
      this.board[x][y] = 1;   // alive state cell are given 1 as value

    }

    public void setDead(int x, int y){
     this.board[x][y] = 0;   // dead state are given 0 as value

    }



// count alive neighbours
    public int countAliveNeighbours(int x, int y){

        int count=0;
        count +=getstate(x-1,y-1);
        count +=getstate(x,y-1);
        count +=getstate(x+1,y-1);

        count +=getstate(x-1,y);
        count +=getstate(x+1,y);

        count +=getstate(x-1,y+1);
        count +=getstate(x,y+1);
        count +=getstate(x+1,y+1);

        return count;

    }

    //isAlive() function: checks x and y value for index out of bound error ie return coordinate value only for valid x and y value

    public int getstate(int x,int y){
        if(x<0 || x>=width){
            return 0;
        }
        if(y<0 || y>= height){
            return 0;
        }
        return this.board[x][y];
    }

    // generating new state Board using 4 golden rules
    public void step(){

        int [][] newBoard= new int [width][height];    // new state board

        for(int y=0;y<height;y++) {
            for (int x = 0; x < width; x++) {
                int aliveNeighbours=countAliveNeighbours(x,y);

                if(getstate(x,y)==1){
                     if(aliveNeighbours<2){
                         newBoard[x][y]=0 ;  // rule 1
                     }
                     else if (aliveNeighbours == 2 || aliveNeighbours == 3 ){
                        newBoard[x][y]= 1;  // rule 2
                    }
                     else if (aliveNeighbours >3 ){
                         newBoard[x][y]= 0;  // rule 3
                     }

               }
                else {
                    if(aliveNeighbours == 3){
                        newBoard[x][y]=1;   // rule 4
                    }
                }
            }

        }
          this.board=newBoard;
    }

  public static void main(String [] args){

        Simulation sim1=new Simulation();
        Simulation simulation= new Simulation(8,5);
        simulation.setAlive(Integer.parseInt(sim1.getCordinates().get(0)), Integer.parseInt(sim1.getCordinates().get(1)));
        simulation.setAlive(Integer.parseInt(sim1.getCordinates().get(2)), Integer.parseInt(sim1.getCordinates().get(3)));
        simulation.setAlive(Integer.parseInt(sim1.getCordinates().get(4)), Integer.parseInt(sim1.getCordinates().get(5)));
        simulation.printBoard();
        simulation.step();
        simulation.printBoard();
        simulation.step();
        simulation.printBoard();
        simulation.step();
        simulation.printBoard();
        simulation.step();
  }
}
