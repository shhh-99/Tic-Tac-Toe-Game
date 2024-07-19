import java.util.Scanner;

public class Play {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tictactoe game = new Tictactoe();
        
        System.out.println("1 or 0");
        int ch;
        do {
            ch = sc.nextInt();
        }while(ch != 1 && ch != 0);
        boolean isX = ch == 1 ? true : false;
        
        Agent agent = new Agent(!isX);
        int turn = ch;

        while(!(game.checkGameEnd(game))) {
            if(turn == 1) {
                int move;
                do{
                    System.out.print("Player turn: ");
                    move = sc.nextInt();
                }while(move < 0 || move > 8 || !Tictactoe.isValidMove(game.getX_State(), game.getO_State(), 1 << move));
                game.applyMove(1 << move, isX);
                game.drawGame(game);
            }
            else {
                System.out.println("Computer turn:");
                //game.applyMove(agent.findOptimalMove(game.getX_State(), game.getO_State()), !isX);
                game.applyMove(agent.get_Move(game.getX_State(), game.getO_State()), !isX);
                game.drawGame(game);
            }
            
            turn ^= 1;
        }

        sc.close();
    }
}
