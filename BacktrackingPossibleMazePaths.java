public class BacktrackingPossibleMazePaths {

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
