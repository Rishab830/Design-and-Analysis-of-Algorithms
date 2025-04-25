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
