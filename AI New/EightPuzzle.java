import java.util.*;

class Node implements Comparable<Node> {
    int[][] state;
    Node parent;
    String move;
    int depth;
    int heuristic;

    public Node(int[][] state, Node parent, String move, int depth, int heuristic) {
        this.state = state;
        this.parent = parent;
        this.move = move;
        this.depth = depth;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.heuristic, other.heuristic);
    }
}

public class EightPuzzle {

    // Helper function to calculate the Misplaced Tiles heuristic
    private static int calculateHeuristic(int[][] state, int[][] goalState) {
        int misplacedTiles = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != goalState[i][j]) {
                    misplacedTiles++;
                }
            }
        }
        return misplacedTiles;
    }

    // Best First Search algorithm
    private static List<String> bestFirstSearch(int[][] initialState, int[][] goalState) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        int initialHeuristic = calculateHeuristic(initialState, goalState);
        Node initialNode = new Node(initialState, null, null, 0, initialHeuristic);
        openList.add(initialNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();

            if (Arrays.deepEquals(currentNode.state, goalState)) {
                List<String> path = new ArrayList<>();
                while (currentNode.move != null) {
                    path.add(currentNode.move);
                    currentNode = currentNode.parent;
                }
                Collections.reverse(path);
                return path;
            }

            // Generate possible moves and add to the open list
            int row = findPosition(currentNode.state, 0)[0];
            int col = findPosition(currentNode.state, 0)[1];
            int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            String[] moveNames = {"UP", "DOWN", "LEFT", "RIGHT"};

            for (int i = 0; i < 4; i++) {
                int newRow = row + moves[i][0];
                int newCol = col + moves[i][1];
                if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                    int[][] newState = Arrays.stream(currentNode.state).map(int[]::clone).toArray(int[][]::new);
                    newState[row][col] = newState[newRow][newCol];
                    newState[newRow][newCol] = 0;
                    int newHeuristic = calculateHeuristic(newState, goalState);
                    Node newNode = new Node(newState, currentNode, moveNames[i], currentNode.depth + 1, newHeuristic);
                    openList.add(newNode);
                }
            }
        }
        return null;
    }

    // Helper function to find the position of a value in the puzzle
    private static int[] findPosition(int[][] state, int value) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == value) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // Example usage
    public static void main(String[] args) {
        int[][] initialState = {{1, 2, 3}, {5, 6, 0}, {7, 8, 4}};
        int[][] goalState = {{1, 2, 3}, {5, 8, 6}, {0, 7, 4}};

        List<String> solution = bestFirstSearch(initialState, goalState);
        if (solution != null) {
            System.out.println("Solution Path:");
            System.out.println(solution);
        } else {
            System.out.println("No solution exists.");
        }
    }
}