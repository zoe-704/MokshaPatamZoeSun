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
    // public static final int dice = 6;

    public static int BFS(int boardsize, int[][] ladders, int[][] snakes) {
        // Board with size 1 wins with 0 moves
        if (boardsize == 1) return 0;

        // Create an array such that jumps[start] = end for each ladder and snake
        int[] jumps = new int[boardsize + 1];
        for (int[] l : ladders) jumps[l[0]] = l[1];
        for (int[] s : snakes) jumps[s[0]] = s[1];

        // Prepare BFS
        Queue<int[]> q = new LinkedList<>();
        boolean[] vis = new boolean[boardsize + 1];

        q.add(new int[]{1, 0});
        vis[1] = true;

        // BFS implementation
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int pos = cur[0], moves = cur[1];
            if (pos == boardsize) return moves;

            for (int dice = 1; dice <= 6; dice++) {
                int next = pos + dice;
                if (next > boardsize) continue;
                if (jumps[next] != 0) next = jumps[next];
                if (!vis[next]) {
                    vis[next] = true;
                    q.add(new int[]{next, moves+1});
                }
            }
        }
        return -1;
    }
    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {

        /* // Sort in ascending order for starting point of ladder or snake
        Arrays.sort(ladders, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(snakes, (a, b) -> Integer.compare(a[0], b[0]));*/

        return BFS(boardsize, ladders, snakes);
    }
}
