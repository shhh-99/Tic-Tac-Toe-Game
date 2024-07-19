public class Agent {

    private boolean isXAgent;

    public Agent(boolean isXAgent) {
        this.isXAgent = isXAgent;
    }

    int[] state = new int[2];
    int bot_turn = 0;
    int best_move = 0;

    int nodes_explored = 0;

    public int get_Move(int X_state, int O_state) {
        state[0] = O_state;
        state[1] = X_state;

        bot_turn = isXAgent ? 1 : 0;
        best_move = 0;
        nodes_explored = 0;

        minimax(bot_turn, true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        System.err.println("Minimax checked: " + nodes_explored + " nodes");
        return best_move;
    }

    private int minimax(int turn, boolean root,int beta,int alpha) {
        nodes_explored++;

        int game_Over = Tictactoe.isGameEnd(state[1], state[0]);
        if (game_Over != 2) { return isXAgent ? game_Over : -game_Over; }

        boolean max_node = turn == bot_turn ? true : false;

        int best_score = max_node ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < Tictactoe.MOVE_SET.length; i++) {
            int move = Tictactoe.MOVE_SET[i];
            if (Tictactoe.isValidMove(state[0], state[1], move)) {
                state[turn] ^= move;
                int cnt_score = minimax(turn^1, false, beta, alpha);
                state[turn] ^= move;

                if (max_node && cnt_score > best_score) {
                    if (root) { best_move = move; }
                    if (cnt_score >= beta) { return beta; }
                    best_score = cnt_score;
                    alpha = Math.max(alpha, cnt_score);
                } else if (!max_node && cnt_score < best_score) {
                    if (cnt_score <= alpha) { return alpha; }
                    best_score = cnt_score;
                    beta = Math.min(beta, cnt_score);
                }
            }
        }

        return best_score;
    }
}
