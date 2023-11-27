import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class DFSPract1 {
    private int V;
    private ArrayList<ArrayList<Integer>> adjList;

    public DFSPract1(int vertices) {
        V = vertices;
        adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; ++i) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public void dfs(int startVertex) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        visited[startVertex] = true;
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            System.out.print(currentVertex + " ");

            for (int adjacentVertex : adjList.get(currentVertex)) {
                if (!visited[adjacentVertex]) {
                    visited[adjacentVertex] = true;
                    stack.push(adjacentVertex);
                }
            }
        }
    }
}

public class DFSPract {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices in the graph: ");
        int vertices = scanner.nextInt();

        DFSPract1 graph = new DFSPract1(vertices);

        System.out.println("Enter the edges (enter -1 to stop):");
        while (true) {
            int u = scanner.nextInt();
            if (u == -1) {
                break;
            }
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }

        System.out.print("Enter the starting vertex for DFS: ");
        int startVertex = scanner.nextInt();

        System.out.println("Depth-First Search starting from vertex " + startVertex + ":");
        graph.dfs(startVertex);

        scanner.close();
    }
}

