import java.util.*;
public class play {
    public static final int SIZE = 7;

    //This is the main method, where I handle user input and call functions for playing
    public static void main(String[]args){
        Board game = new Board();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Connect 4. Enter 1 for Player vs Player and 2 for Player vs Computer.");
        String user = input.next();
        while(!(user.equals("1") || user.equals("2"))){
            System.out.println("Please enter a valid input.");
            user = input.next();
        }
        int type = Integer.parseInt(user);
        int p1 =1 , p2 =1;
        if(type==2){
            int check = setUp(input);
            if(check==1){
                p2 = 2;
            }else{
                p1 = 2;
            }
        }
        GameLoop(input, game, p1, p2);
    }

    //This method will set up the game, ask the player when playing against the computer whether they would like to
    //go first or second, and is used to determine the p1 and p2 for the gameLoop function
    public static int setUp(Scanner input){
        System.out.println("Enter 1 if you wish to go 1st, enter 2 if you wish to go 2nd.");
        String user = input.next();
        while(!(user.equals("1") || user.equals("2"))){
            System.out.println("Please enter a valid input.");
            user = input.next();
        }
        int type = Integer.parseInt(user);
        return type;
    }

    //This is the main loop method that will running the game, will see who is who, if player 1 is
    //computer or human. 1 for human, 2 for computer 
    public static void GameLoop(Scanner input, Board game, int p1, int p2){
        int counter = 0;
        int end = game.checkWin();
        while(end<1){
            game.printBoard();
            if(counter%2==0){
                if(p1==1){
                    int index = checkInput(input, p1, game);
                    game.placeToken(1, index-1);
                    
                }else{
                    game.placeToken(1, computerTurn(game, counter, 1));
                }
            }else{
                if(p2==1){
                    int index = checkInput(input, 2, game);
                    game.placeToken(2, index-1);
                }else{
                    game.placeToken(2, computerTurn(game, counter, p2));
                }
                
            }
            end = game.checkWin();
            counter ++;       
        }
        game.printBoard();
        if(end==1 && p1==1){
            System.out.println("Congratulations, Player 1 wins!");
        }
        else if(end==2 && p2==1){
            System.out.println("Congratulations, Player 2 wins!");
        }
        else{
            System.out.print("Sorry, you lose.");
        }
    }

    //This is the computer playing a spot
    public static int computerTurn(Board game, int counter, int player){
        double max = 0.0;
        int index = 0;
        for(int i = 0; i<SIZE; i++){
            double currentMoveVal = evalutation(0, game, counter, i, player);
            if(max<currentMoveVal){
                max = currentMoveVal;
                index = i;
            }
        }
        return index;
    }

    //This is the recursive funciton that plays some number of moves ahead,
    //calculating based on percentage and not worst and best case scenario
    public static double evalutation(int depth, Board game, int counter, int move, int player){
        if(depth==5){
            return .5;
        }
        if(!game.checkPlace(move) || game.checkTie()){
            return -1;
        }
        int tok;
        if(counter%2==0){
            tok = 1;
        }else{
            tok = 2;
        }
        Board futureBoard = game.copyBoard();
        futureBoard.placeToken(tok, move);
        int checker = futureBoard.checkWin();
        if(checker!=0){
            if(checker==player){
                return 1;
            }if(checker!=player){
                return 0;
            }
        }
        double sum = 0;
        int count = 0;
        for(int i=0; i<SIZE;i++){
            double moveValue = evalutation(depth+1, futureBoard, counter+1, i, player);
            if(moveValue!=-1){
                sum += moveValue;
                count ++;
            }
        }
        return sum/count;

    }

    //This is a while loop that checks whether then input is valid, checking first that
    //it's in between 1 and 7, and then that the row isn't full
    public static int checkInput(Scanner input, int player, Board game){
        System.out.printf("Player %d, please enter a number between 1 and 7 to play your turn.",player);
        while(true){
            String user = input.next();
            int index = Integer.parseInt(user);
            if(index>=1 && index<=SIZE){
                if(game.checkPlace(index-1)){
                    return index;
                }else{
                    System.out.println("This column is full, please play in another column.");
                }
            }else{
                System.out.print("Please enter a valid input between 1 and 7.");
            }
        }
    }
}