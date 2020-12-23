public class KnightBoard{
    private int[][] board;
    private int rows;
    private int cols;
    private int[][] moves = { 
                              {1, 2}, {-1, 2}, {1, -2}, {-1, -2},
                              {2, 1}, {2, -1}, {-2, 1}, {-2, -1}                                  
                            };

    public KnightBoard(int startingRows, int startingCols){ 
        board = new int[startingRows][startingCols];
        rows = startingRows;
        cols = startingCols;
    }

    public boolean canPlace(int moveNum, int currentRow, int currentCol){
        return currentRow + moves[moveNum][0] > -1 && 
               currentCol + moves[moveNum][1] > -1 && 
               currentRow + moves[moveNum][0] < rows && 
               currentCol + moves[moveNum][1] < cols && 
               (board[currentRow + moves[moveNum][0]][currentCol + moves[moveNum][1]] == 0);
    }

    public String toString(){
        String strBoard = "";
        for (int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if (board[i][j] == 0){
                    strBoard += "_ ";                
                }                
                else if (board[i][j] < 10){
                    strBoard += " " + board[i][j] + " ";
                } else {
                    strBoard += board[i][j] + " ";
                } 
            }
            strBoard += "\n";
        }
        return strBoard;
    }

    public void checkException(){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] != 0){
                    throw new IllegalStateException();
                }
            }
        }
    }

    public boolean solve(int row, int col){
        checkException();
        return solveH(row, col, 1);
    }

    public boolean solveH(int row, int col, int level){
        board[row][col] = level;
        if (level == (rows * cols)){
            return true;
        }
        for (int i = 0; i < 8; i++){
            if (canPlace(i, row, col)){
                if(solveH(row + moves[i][0], col + moves[i][1], level + 1)){
                    return true;
                } 
                board[row + moves[i][0]][col + moves[i][1]] = 0;
            }
        }
        return false;
    }

    public int countSolutions(int row, int col){
        checkException();
        return countH(row, col, 1);
    }

    public int countH(int row, int col, int level){
        int numSolutions = 0;
        board[row][col] = level;
        if(level == (rows * cols)){
            board[row][col] = 0;
            return 1;
        }
        for (int i = 0; i < 8; i++){
            if (canPlace(i, row, col)){
                numSolutions += countH(row + moves[i][0], col + moves[i][1], level + 1);
            }
        }
        board[row][col] = 0;
        return numSolutions;
    }

    // public static void main(String[] args){
    //     KnightBoard board = new KnightBoard(5, 5);
    //     //board.solve(0,0);
    //     System.out.println(board.countSolutions(0, 0));
    //     //System.out.println(board);    
    // }    

}






/*
,d88b.d88b,
88888888888
`Y8888888Y'
  `Y888Y'
    `Y'
*/





