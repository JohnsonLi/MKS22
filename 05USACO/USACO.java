import java.util.*;
import java.io.*;

public class USACO{

    public static int bronze(String filename){
        int row = 0, col = 0, e = 0, n = 0;
        int[][] field = new int[row][col];
        Scanner in = null;

        try {
            File f = new File(filename);
            in = new Scanner(f);

            row = Integer.parseInt(in.next()); 
            col = Integer.parseInt(in.next());
            e = Integer.parseInt(in.next());
            n = Integer.parseInt(in.next());

            // System.out.println(row + " " + col);
            field = new int[row][col];
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    field[i][j] = Integer.parseInt(in.next());
                }
            }
        }catch (FileNotFoundException error){
            System.out.println("FILE NOT FOUND");
            System.exit(1);
        }

        for(int i = 0; i < n; i++){
            int r_s = Integer.parseInt(in.next()) - 1; 
            int c_s = Integer.parseInt(in.next()) - 1; 
            int d_s = Integer.parseInt(in.next()); 

            int tallest = 0;
            for(int j = 0; j < 3 && j + r_s < field.length; j++){
                for(int k = 0; k < 3 && k + c_s < field[0].length; k++){
                    if(field[r_s + j][c_s + k] > tallest){
                        tallest = field[r_s + j][c_s + k];
                    }
                }
            }
            int newD = tallest - d_s;
            for(int j = 0; j < 3 && j + r_s < field.length; j++){
                for(int k = 0; k < 3 && k + c_s < field[0].length; k++){
                    if(field[r_s + j][c_s + k] > newD){
                        field[r_s + j][c_s + k] = newD;
                    }
                }
            }
        }

        int totalStomped = 0;
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j  < field[0].length; j++){
                if(field[i][j] < e){
                    totalStomped += e - field[i][j];
                }
            }
        }

        //System.out.println(field[0][2]);
        in.close();
        return totalStomped * 72 * 72;
    }


    public static int silver(String filename){
        int row=0,col=0,secs=0,rStart=0,rEnd=0,cStart=0,cEnd=0;
        char[][] pasture = new char[row][col]; int[][] board1, board2; 
        Scanner in = null;

        try {
            File f = new File(filename);
            in = new Scanner(f);

            row = Integer.parseInt(in.next()); 
            col = Integer.parseInt(in.next());
            secs = Integer.parseInt(in.next());

            pasture = new char[row][col];
            for(int i = 0; i < row; i++) {
                String holder = in.next();
                for(int j = 0; j < col; j++) {
                    pasture[i][j] = holder.charAt(j);
                }
            }

            rStart = Integer.parseInt(in.next()) - 1;
		    cStart = Integer.parseInt(in.next()) - 1;
		    rEnd = Integer.parseInt(in.next()) - 1;
            cEnd = Integer.parseInt(in.next()) - 1;
        }catch (FileNotFoundException error){
            System.out.println("FILE NOT FOUND");
            System.exit(1);
        }

        board1 = new int[row][col];
        board2 = new int[row][col];
        board1[rStart][cStart] = 1;

        for(int i = 0; i < secs; i++){
            for(int j = 0; j < row; j++){
                for(int k = 0; k < col; k++){
                    if(board1[j][k] != 0){
                        board2[j][k] = 0;
                    }else if(pasture[j][k] != '*' && board2[j][k] == 0){
                        int ways = 0;
                        if(j + 1 < row){
                            ways += board1[j + 1][k];
                        }
                        if(j - 1 >= 0){
                            ways += board1[j - 1][k];
                        }
                        if(k + 1 < col){
                            ways += board1[j][k + 1];
                        }
                        if(k - 1 >= 0){
                            ways += board1[j][k - 1];
                        }
                        board2[j][k] = ways;
                    }
                }
            }

            for(int j = 0; j < row; j++){
                for(int k = 0; k < col; k++){
                    board1[j][k] = board2[j][k];
                }
            }
        }

        in.close();
        return board2[rEnd][cEnd];
    }


    // public static void main(String[] args) {
    //    System.out.println( USACO.bronze("makelake.in"));
    //    System.out.println(USACO.silver("ctravel.in"));
    // }
}