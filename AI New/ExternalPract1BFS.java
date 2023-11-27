import java.util.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
public class ExternalPract1BFS {
    static ArrayList<ArrayList<edge>> adj = new ArrayList<>();

    static class edge implements Comparable<edge> {
        int v, cost;

        edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(edge o) {
            if (o.cost < cost) {
                return 1;
            } else
                return -1;
        }
    }

    public ExternalPract1BFS(int v) {
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    static void best_first_search(int source, int target, int v) {
        PriorityQueue<edge> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[v];
        visited[source] = true;
        pq.add(new edge(source, -1));

        while (!pq.isEmpty()) {
            int x = pq.poll().v;
            System.out.print(x + " ");
            if (target == x) {
                break;
            }
            for (edge adjacentNodeEdge : adj.get(x)) {
                if (!visited[adjacentNodeEdge.v]) {
                    visited[adjacentNodeEdge.v] = true;
                    pq.add(adjacentNodeEdge);
                }
            }
        }
    }

    void addedge(int u, int v, int cost) {
        adj.get(u).add(new edge(v, cost));
        adj.get(v).add(new edge(u, cost));
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int v = scanner.nextInt();
        ExternalPract1BFS graph = new ExternalPract1BFS(v);

        System.out.println("Enter the edges (source, destination, cost), one per line. Enter -1 -1 -1 to stop.");
        while (true) {
            int u = scanner.nextInt();
            int w = scanner.nextInt();
            int cost = scanner.nextInt();
            if (u == -1 && w == -1 && cost == -1) {
                break;
            }
            graph.addedge(u, w, cost);
        }

        System.out.print("Enter the source node: ");
        int source = scanner.nextInt();

        System.out.print("Enter the target node: ");
        int target = scanner.nextInt();

        // Function call
        best_first_search(source, target, v);

        scanner.close();
    }
}
