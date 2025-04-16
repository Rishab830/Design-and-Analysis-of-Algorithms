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
