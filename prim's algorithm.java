import java.util.*;

public class PrimsAlgorithm {

    static final int V = 5; // Number of vertices

    // Find the vertex with minimum key value
    static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Prim's Algorithm
    static void primMST(int[][] graph) {

        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        // Initialize keys as infinity
        // and MST set as false
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Start from vertex 0
        key[0] = 0;
        parent[0] = -1;

        // Construct MST
        for (int count = 0; count < V - 1; count++) {

            // Pick minimum key vertex
            int u = minKey(key, mstSet);

            // Add vertex to MST
            mstSet[u] = true;

            // Update key and parent
            for (int v = 0; v < V; v++) {

                if (graph[u][v] != 0 &&
                    !mstSet[v] &&
                    graph[u][v] < key[v]) {

                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print MST
        System.out.println("Edge \tWeight");

        for (int i = 1; i < V; i++) {
            System.out.println(
                parent[i] + " - " + i + "\t" + graph[i][parent[i]]
            );
        }
    }

    public static void main(String[] args) {

        // Graph represented using adjacency matrix
        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        primMST(graph);
    }
}