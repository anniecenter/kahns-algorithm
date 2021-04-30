/**
 * Title: hw4_3.java
 * Abstract: This program implements Kahn's algorithm to sort vertices on a graph.
 * Author: Annie Center
 * ID: 8392
 * Date: 04/06/2021
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;

class Graph {
    int vertices;
    List<Integer> adjList[];

    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++)
            adjList[i] = new ArrayList<Integer>();
    }

    public void addEdge(int v1, int v2) {
        adjList[v1].add(v2);
    }

    public void topoSort() {
        int inDegree[] = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            ArrayList<Integer> list = (ArrayList<Integer>) adjList[i];
            for (int node : list) {
                inDegree[node]++;
            }
        }

        for(int i = 0; i < vertices; i++) {
            System.out.println("In-degree[" + i + "]:" + inDegree[i]);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
        }

        int count = 0;
        ArrayList<Integer> order = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            order.add(vertex);

            for (int deg : adjList[vertex]) {
                if (--inDegree[deg] == 0)
                    queue.add(deg);
            }
            count++;
        }

        if (count != vertices) {
            System.out.println("No Order:");
            return;
        }
        else {
            System.out.print("Order:");
            for (int i = 0; i < order.size(); i++) {
                System.out.print(order.get(i));
                if (i != order.size() - 1)
                    System.out.print("->");
            }
        }
    }
}

class hw4_3 {
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        int v = input.nextInt();
        int e = input.nextInt();

        Graph graph = new Graph(v);

        for(int i = 0; i < e; i++)
            graph.addEdge(input.nextInt(), input.nextInt());

        graph.topoSort();
    }
}