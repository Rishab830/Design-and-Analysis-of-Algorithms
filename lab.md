title: "DAA Lab"
author: "Rishab Ramesh Nair"

---

# 1) Pascal Triangle

    import java.util.*;
    class PascalTriangle {
        static int[] generateRow(int n){
            int[] row = new int[n];
            row[0] = 1;
            for(int i=1;i<n;i++){
                row[i] = row[i-1]*(n-i)/i;
            }
            return row;
        }
        
        static List<int[]> generateTriangle(int n){
            n++;
            List<int[]> triangle = new ArrayList<int[]>();
            for(int i=1;i<n;i++){
                triangle.add(generateRow(i));
            }
            return triangle;
        }
        
        public static void main(String[] args) {
            List<int[]> triangle = generateTriangle(5);
            for(int[] row: triangle){
                for(int e: row){
                    System.out.print(e+" ");
                }
                System.out.println();
            }
        }
    }

---

# 2) Longest Increasing Subsequence

    class LongestIncreasingSubsequenceDP{

        static Object[] lisDP(int[] arr){
            int[] traceback = new int[arr.length];
            int[] dp = new int[arr.length];
            dp[0] = 1;
            traceback[0] = -1;
            dp[arr.length - 1] = lisRecDP(arr, dp, traceback, arr.length - 1);
            return new Object[] {dp, traceback};
        }

        static int lisRecDP(int[] arr, int[] dp, int[] traceback, int i){
            if(dp[i] == 0){
                for(int j=0;j<i;j++){
                    if(dp[j] == 0){
                        dp[j] = lisRecDP(arr, dp, traceback, j);
                    }
                    if(arr[j]<arr[i]){
                        if(dp[j]+1>dp[i]){
                            dp[i] = dp[j] + 1;
                            traceback[i] = j;
                        }
                    }
                    else{
                        dp[i] = Math.max(1, dp[i]);
                    }
                }
            }
            return dp[i];
        }

        public static void main(String[] args) {
            int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
            Object[] output = lisDP(arr);
            int[] dp = (int[])output[0];
            int[] traceback = (int[])output[1];
            for(int i: dp){
                System.out.print(i+" ");
            }
            System.out.println();
            for(int i: traceback){
                System.out.print(i+" ");
            }
            System.out.println();
            int max = 0;
            int maxi = 0;
            for(int i=0;i<arr.length;i++){
                if(arr[i]>max){
                    max = arr[i];
                    maxi = i;
                }
            }
            int i = maxi;
            while(i!=-1){
                System.out.print(arr[i] + " ");
                i = traceback[i];
            }
        }
    }

---

# 3) Perfect Squares

    public class SumOfSquares {

        static int coinChange(int amt){
            int[] coins = new int[(int)Math.sqrt(amt)];
            for(int i=1;i<coins.length+1;i++){
                coins[i-1] = (int)Math.pow(i, 2);
            }
            int[] dp = new int[amt+1];
            for(int i=0;i<coins.length;i++){
                for(int j=1;j<=amt;j++){
                    if(coins[i]<=j){
                        dp[j] = Math.min(dp[j-1]+1,dp[j-coins[i]]+1);
                    }
                    else{
                        dp[j] = dp[j] == 0? dp[j-1]+1: Math.min(dp[j-1]+1,dp[j]);
                    }
                }
                for(int k: dp){
                    System.out.print(k+" ");
                }
                System.out.println();
            }
            return dp[amt];
        }

        public static void main(String[] args) {
            coinChange(13);
        }
    }

---

# 4) All Possible Paths for Robot

    public class PossibleMazePaths {

        static void printMaze(int[][] maze){
            for(int i=0;i<maze.length;i++){
                for(int j=0;j<maze[i].length;j++){
                    System.out.print(maze[i][j]+" ");
                }
                System.out.println();
            }
        }

        static int noOfPossiblePaths(int[][] maze){
            int[][] visited = new int[maze.length][maze[0].length];
            return recBackTracking(maze, visited, 0, 0, 0);
        }

        static int recBackTracking(int[][] maze, int[][] visited, int paths, int i, int j){
            if(i==maze.length-1&&j==maze[0].length-1){
                paths++;
                visited[i][j] = 0;
                // System.out.println("Maze: ");
                // printMaze(maze);
                // System.out.println("Visited: ");
                // printMaze(visited);
                return paths;
            }
            if(i+1<maze.length&&maze[i+1][j]==0&&visited[i+1][j]==0){
                visited[i][j] = 1;
                paths = recBackTracking(maze, visited, paths, i+1, j);
            }
            if(j+1<maze[0].length&&maze[i][j+1]==0&&visited[i][j+1]==0){
                visited[i][j] = 1;
                paths = recBackTracking(maze, visited, paths, i, j+1);
            }
            visited[i][j] = 0;
            // System.out.println("Maze: ");
            // printMaze(maze);
            // System.out.println("Visited: ");
            // printMaze(visited);
            // System.out.println();
            return paths;
            
        }

        public static void main(String[] args) {
            int n = noOfPossiblePaths(new int[][] {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
            System.out.println(n);
        }
    }

---

# 5) Knight

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

---

# 6) Hamiltonian Path
    import java.util.*;

    class Node{
        String name;
        List<Node> next;

        Node(String name){
            this.name = name;
            next = new ArrayList<Node>();
        }
    }

    public class HamiltonianPath {

        static String recFindPath(List<Node> graph, Node node, int depth, String output){
            String temp;
            for(Node child: node.next){
                if(output.contains(child.name)){
                    if(depth == graph.size()-1&&output.startsWith(child.name)){
                        return output+child.name;
                    }
                }
                else{
                    temp = recFindPath(graph, child, depth+1, output+child.name);
                    if(temp!=null){
                        return temp;
                    }
                }
            }
            return null;
        }

        static String findHamiltonianPath(List<Node> graph){
            String output = "";
            String temp;
            int depth = 0;
            for(Node root: graph){
                temp = recFindPath(graph, root, depth, output+root.name);
                if(temp!=null){
                    return temp;
                }
            }
            return null;
        }

        public static void main(String[] args) {
            List<Node> graph = new ArrayList<Node>();
            graph.add(new Node("A"));
            graph.add(new Node("B"));
            graph.add(new Node("C"));
            graph.add(new Node("D"));
            graph.add(new Node("E"));
            graph.get(0).next.add(graph.get(1));
            graph.get(0).next.add(graph.get(2));
            graph.get(1).next.add(graph.get(0));
            graph.get(1).next.add(graph.get(3));
            graph.get(1).next.add(graph.get(4));
            graph.get(2).next.add(graph.get(0));
            graph.get(2).next.add(graph.get(3));
            graph.get(3).next.add(graph.get(1));
            graph.get(3).next.add(graph.get(2));
            graph.get(3).next.add(graph.get(4));
            graph.get(4).next.add(graph.get(1));
            graph.get(4).next.add(graph.get(3));
            System.out.println(findHamiltonianPath(graph));

        }
}

---

# 7) Graph Color Theory

    public class ColorGraphTheory {
        
        static int[] recSetColor(int[][] graph, int m, int node, int depth, int[] colors){
            boolean flag;
            int[] temp;
            if(depth==graph.length){
                return colors;
            }
            for(int i=0;i<m;i++){
                flag = true;
                for(int j=0;j<graph.length;j++){
                    if(graph[node][j]==1&&i==colors[j]){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    colors[node] = i;
                    temp = recSetColor(graph, m, node+1, depth+1, colors);
                    if(temp!=null){
                        return temp;
                    }
                }
            }
            return null;
        }
        
        static int[] setColor(int[][] graph, int m){
            int[] colors = new int[graph.length];
            for(int i=0;i<graph.length;i++){
                colors[i] = -1;
            }
            return recSetColor(graph, m, 0, 0, colors);
        }
        
        public static void main(String[] args) {
            int n = 4;
            int m = 3;
            String[] colorDict = {"C1","C2","C3"};
            int[][] graph = new int[n][n];
            graph[0][1] = 1;
            graph[0][2] = 1;
            graph[0][3] = 1;
            graph[1][0] = 1;
            graph[1][2] = 1;
            graph[2][1] = 1;
            graph[2][0] = 1;
            graph[2][3] = 1;
            graph[3][0] = 1;
            graph[3][2] = 1;
            
            int[] colors = setColor(graph, m);
            
            for(int[] row: graph){
                for(int col: row){
                    System.out.print(col+" ");
                }
                System.out.println();
            }
            if(colors!=null){
                for(int color: colors){
                    System.out.print(colorDict[color]+" ");
                }
            }
            else{
                System.out.println(colors);
            }
        }
    }

# 9) Why is Hamiltonian path NP-complete

Hamiltonian cannot be solved in polynomial time for any general graph, hence it is NP \
Other NP problems such as Travelling Salesman Problem can be reduced down to Hamiltonian path problem, hence it is also NP-hard \
Because it is NP and NP-hard, hence it is NP-complete.
