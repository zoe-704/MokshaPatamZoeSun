import java.util.*;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Zoe Sun
 *
 */


public class MokshaPatam {
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        // Create an array such that jumps[start] = end for each ladder and snake
        int[] jumps = new int[boardsize + 1];
        for (int[] l : ladders) jumps[l[0]] = l[1];
        for (int[] s : snakes) jumps[s[0]] = s[1];

        // Edge case: board with size 1 wins with 0 moves
        if (boardsize == 1) return 0;

        // Initialize queue and visited array for BFS
        Queue<int[]> q = new LinkedList<>();
        boolean[] vis = new boolean[boardsize + 1];
        q.add(new int[]{1, 0});
        vis[1] = true;

        // Track previously traveled squares to find winning path
        int[] previous = new int[boardsize + 1];
        Arrays.fill(previous, -1);

        // BFS implementation
        while (!q.isEmpty()) {
            // Dequeue first item and store it
            int[] cur = q.poll();
            int pos = cur[0], moves = cur[1];

            if (pos == boardsize) {
                // Create winningPath list using previous[]
                List<Integer> winningPath = new ArrayList<>();
                for (int at = boardsize; at != -1; at = previous[at]) {
                    winningPath.add(at);
                }
                Collections.reverse(winningPath);

                // Print path and return min number of moves
                for (int step : winningPath) {
                    System.out.println(step);
                }
                return moves;
            }

            // Search path for rolling 1-6 on a die
            for (int dice = 1; dice <= 6; dice++) {
                int next = pos + dice;
                if (next > boardsize) continue; // Out of bounds of board
                // Automatically go up a ladder or down a snake
                if (jumps[next] != 0) next = jumps[next];
                // If next item hasn't been visited, mark as visited and enqueue
                if (!vis[next]) {
                    previous[next] = pos; // Track past moves
                    vis[next] = true;
                    q.add(new int[]{next, moves+1});
                }
            }
        }
        return -1; // Can't reach end of board
    }
}
