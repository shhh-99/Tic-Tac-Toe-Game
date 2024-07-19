public class Tictactoe {
    //public static int[] MOVE_SET = {1, 2, 4, 8, 16, 32, 64, 128, 256};
    
    public static final int[] MOVE_SET = {
        0b000000001,
        0b000000010,
        0b000000100,
        0b000001000,
        0b000010000,
        0b000100000,
        0b001000000,
        0b010000000,
        0b100000000,
    };
    
    //public static int[] WIN_SET = {448, 56, 7, 73, 146, 292, 84, 273};
    
    public static final int[] WIN_SET = {
        0b111000000,
        0b000111000,
        0b000000111,
        0b100010001,
        0b001010100,
        0b100100100,
        0b010010010,
        0b001001001
    };

    public static final int DRAW = 0b111111111;
    
    private int X_state, O_state;

    public Tictactoe() {
        X_state = 0;
        O_state = 0;
    }

    public void drawGame(Tictactoe game) {
        int dumX = game.X_state;
        int dumO = game.O_state;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {                
                if((dumX & 1) == 1) {System.out.print("X");}
                else if((dumO & 1) == 1) {System.out.print("O");}
                else {System.out.print(".");}

                dumX = dumX >> 1;
                dumO = dumO >> 1;
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public void applyMove(int move, boolean isX) {
        if(isX)
            X_state |= move;
        else
            O_state |= move;
    }

    public boolean checkGameEnd(Tictactoe game) {
        int tmp = Tictactoe.isGameEnd(game.X_state, game.O_state);
        return (tmp != 2);
    }

    public static int isGameEnd(int X_state, int O_state) {
        for(int i = 0; i < Tictactoe.WIN_SET.length; i++) {
            if((X_state & Tictactoe.WIN_SET[i]) == Tictactoe.WIN_SET[i]) {return 1;}
            else if((O_state & Tictactoe.WIN_SET[i]) == Tictactoe.WIN_SET[i]) {return -1;}
        }

        if ( (X_state ^ O_state) == DRAW) {return 0;}

        return 2;
    }

    public static boolean isValidMove(int X_state, int O_state, int move) {
        return (((X_state & move) | (O_state & move)) == 0);
    }

    public int getX_State() {
        return X_state;
    }

    public int getO_State() {
        return O_state;
    }
}