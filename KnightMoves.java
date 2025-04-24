import java.util.*;

public class KnightMoves {

    static int[][] recKnightMoves(int[][] board, int i, int j, int depth, int[][] path){
        if(depth == (int)Math.pow(board.length,2) - 1){
            path[i][j] = depth;
            return path;
        }
        path[i][j] = depth;
        board[i][j] = 1;
        int[][] temp = new int[board.length][board.length];
        if(i+2<board.length&&j+1<board.length&&board[i+2][j+1]==0){
            temp = recKnightMoves(board, i+2, j+1, depth + 1, path);
            if(temp!=null){
                return temp;
            }
            board[i+2][j+1] = 0;
        }
        if(i+2<board.length&&j-1>-1&&board[i+2][j-1]==0){
            temp = recKnightMoves(board, i+2, j-1, depth + 1, path);
            if(temp!=null){
                return temp;
            }
            board[i+2][j-1] = 0;
        }
        if(i-2>-1&&j+1<board.length&&board[i-2][j+1]==0){
            temp = recKnightMoves(board, i-2, j+1, depth + 1, path);
            if(temp!=null){
                return temp;
            }
            board[i-2][j+1] = 0;
        }
        if(i-2>-1&&j-1>-1&&board[i-2][j-1]==0){
            temp = recKnightMoves(board, i-2, j-1, depth + 1, path);
            if(temp!=null){
                return temp;
            }
            board[i-2][j-1] = 0;
        }
        if(i+1<board.length&&j+2<board.length&&board[i+1][j+2]==0){
            temp = recKnightMoves(board, i+1, j+2, depth + 1, path);
            if(temp!=null){
                return temp;
            }
            board[i+1][j+2] = 0;
        }
        if(i-1>-1&&j+2<board.length&&board[i-1][j+2]==0){
            temp = recKnightMoves(board, i-1, j+2, depth + 1, path);
            if(temp!=null){
                return temp;
            }
            board[i-1][j+2] = 0;
        }
        if(i+1<board.length&&j-2>-1&&board[i+1][j-2]==0){
            temp = recKnightMoves(board, i+1, j-2, depth + 1, path);
            if(temp!=null){
                return temp;
            }
            board[i+1][j-2] = 0;
        }
        if(i-1>-1&&j-2>-1&&board[i-1][j-2]==0){
            temp = recKnightMoves(board, i-1, j-2, depth + 1, path);
            if(temp!=null){
                return temp;
            }
            board[i-1][j-2] = 0;
        }
        return null;
    }

    static int[][] knightMoves(int n){
        int[][] board = new int[n][n];
        int[][] path = new int[n][n];
        int[][] temp = new int [n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                temp = recKnightMoves(board, i, j, 0, path);
                if (temp != null){
                    return temp;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int n = 10;
        int[][] path = knightMoves(n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(path[i][j]+" ");
            }
            System.out.println();
        }
    }
}
