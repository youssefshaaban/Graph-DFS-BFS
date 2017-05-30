/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author joe
 */
public class DepthFirstSearch {

    static ArrayList<Node> nodes = new ArrayList<>();

    public static void main(String[] args) {
        Stack<Node>ns=new Stack<>();
        int numOfNodes;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter numof Nodes");
        numOfNodes = s.nextInt();
        for (int i = 0; i < numOfNodes; i++) {
            int value = s.nextInt();
            ns.add(new Node(value));
        }
        boolean[][] graph = new boolean[numOfNodes][numOfNodes];
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = 0; j < numOfNodes; j++) {
                boolean connect = s.nextBoolean();
                graph[i][j] = connect;
            }
        }
        System.out.print("\n Enter Start Node     :");
        int startNode = s.nextInt();
        new DepthFirstSearch().dfs(graph, new Node(startNode));
    }

    static class Node {

        int value;
        boolean visited;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!this.getClass().equals(obj.getClass())) return false;
            Node n=(Node)obj;
            if(this.value==n.value)return true;
            return false; //To change body of generated methods, choose Tools | Templates.
        }

    }

    // Iterative DFS using stack
    public void dfsWithStack(boolean addj[][], Node node) {
        Stack<Node> stack = new Stack<Node>();
        stack.add(node);
        node.visited = true;
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.print(n.value + "\t");
            ArrayList<Node> neighborsNodes = neighbors(addj, n);
            for (int i = 0; i < neighborsNodes.size(); i++) {
                Node n1 = neighborsNodes.get(i);
                System.out.println(""+stack.search(n1));
                if (n1 != null && !n1.visited &&stack.search(n1)==-1) {
                    n1.visited = true;
                    stack.add(n1);
                    
                }
            }
        }
    }

    // recursive

    public void dfs(boolean addj[][], Node node) {
        System.out.print(node.value + "\t");
        ArrayList<Node> neighborsNodes = neighbors(addj, node);
        int size = neighborsNodes.size();
        for (int i = 0; i < size; i++) {
            Node node1 = neighborsNodes.get(i);
            if (node1 != null && !node1.visited) {
                node1.visited = true;
                dfs(addj, node1);
            }
        }
    }

    public ArrayList<Node> neighbors(boolean adj[][], Node node) {
        ArrayList<Node> neighborsNodes = new ArrayList<>();
        int nodeIndex = -1;
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            if (nodes.get(i).value == node.value) {
                nodeIndex = i;
                break;
            }
        }
        if (nodeIndex != -1) {
            for (int i = 0; i < adj[nodeIndex].length; i++) {
                if (adj[nodeIndex][i] == true) {
                    neighborsNodes.add(nodes.get(i));
                }
            }
        }
        return neighborsNodes;
    }
}
