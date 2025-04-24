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
                if(depth == graph.size()-1){
                    return output;
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
