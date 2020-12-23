import java.util.*;
import java.io.*;
public class Maze{


    private char[][] maze;
    private int[][] moves = {{1,0},{0,1},{-1,0},{0,-1}};
    private boolean animate;//false by default
    //private int count = -1;
    

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)

      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!


      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then: print a meaningful error and exit the program.

    */

    public Maze(String filename) throws FileNotFoundException{
        //COMPLETE CONSTRUCTOR
        animate = false;
        int row=1,col=0;
        String mazeStr = "";

        File text = new File(filename);
        Scanner inf = new Scanner(text);

        if(inf.hasNextLine()){
            String line = inf.nextLine();
            mazeStr += line;
            col = line.length();
        }

        while(inf.hasNextLine()){
            row++;
            mazeStr+=inf.nextLine();
        }  

        maze = new char[row][col];
        
        int count = 0;
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                maze[i][j] = mazeStr.charAt(count);   
                count++;         
            }
        }

        inf.close();

    }   
    

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }


    public void clearTerminal(){

        //erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");

    }


    private boolean canPlace(int row, int col, int moveNum){
        return row + moves[moveNum][0] < maze.length                            && 
               col + moves[moveNum][1] < maze[0].length                         &&
               (maze[row + moves[moveNum][0]][col + moves[moveNum][1]] == ' '   || 
               maze[row + moves[moveNum][0]][col + moves[moveNum][1]] == 'E');
    }

    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
        int startR = -1;
        int startC = -1;
            //find the location of the S. 
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                if(maze[i][j] == ('S')){
                    startR = i;
                    startC = j;
                }
            }
        }

        //erase the S
        maze[startR][startC] = ' ';

        //return solveH(???,???);
        return solveH(startR, startC, 0);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

            Note: This is not required based on the algorithm, it is just nice visually to see.
        All visited spots that are part of the solution are changed to '@'
    */
    private int solveH(int row, int col, int moveNum){ //you can add more parameters since this is private


        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(50);
        }

        //COMPLETE SOLVE
        if(maze[row][col] == 'E'){
            return moveNum;
        }
        maze[row][col] = '@';

        for(int i = 0 ; i < moves.length; i++){
            if(canPlace(row, col, i)){
                int numSteps = solveH(row + moves[i][0], col + moves[i][1], moveNum + 1);
                if (numSteps != -1){
                    return numSteps;
                }
                // idk why this doesnt work
                // if(solveH(row + moves[i][0], col + moves[i][1], moveNum + 1) != -1){
                //     return solveH(row + moves[i][0], col + moves[i][1], moveNum + 1);
                // }
            }
        }

        maze[row][col] = '.';
        //COMPLETE SOLVE
        return -1; //so it compiles    
    }

    public String toString(){
        String str = "";        
    
        for(int i = 0; i < maze.length; i++){
            for(int j = 0;j < maze[0].length; j++){
                str += maze[i][j];
            }
            str += "\n";
        }
        return str;
    }
}
