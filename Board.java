public class Board{
    //This will be the constant to determine the size of the board
    public static final int SIZE = 7;

    //This will be the actual board that is a 2D array of for now empty spots
    private char[][] board;
    public static void main(String[]args){
        char[][] test = {
                    {'-','-','-','-','-','-','-'},
                    {'-','-','-','-','-','-','-'},
                    {'-','-','-','-','-','-','-'},
                    {'-','-','-','-','-','-','X'},
                    {'-','-','-','-','-','X','-'},
                    {'-','-','-','-','X','-','-'},
                    {'X','X','X','-','-','-','-'}};
        Board yes = new Board(test);
        int checker = yes.checkWin();
        System.out.println(checker);
        yes.printBoard();
        //yes.printBoard();
        //yes.placeToken('X',5);
        //yes.printBoard();
    }

    //This will be the constructor where I will make all of the parts a dash mark
    public Board(){
        board = new char[SIZE][SIZE];
        for(int i =0; i< board.length;i++){
            for(int j =0; j<board[i].length;j++){
                board[i][j] = '-';
            }
        }
    }

    //This is a new constructor that takes an array as a parameter, used for unit testing
    public Board(char[][] test){
        board = new char[SIZE][SIZE];
        for(int i =0; i< board.length;i++){
            for(int j =0; j<board[i].length;j++){
                board[i][j] = test[i][j];
            }
        }
    }

    //This will be the method where you can copy the board and use a new one,
    // made for the recursive method
    public Board copyBoard(){
        Board rtrn = new Board();
        for(int r =0; r< rtrn.board.length;r++){
            for(int c =0; c<rtrn.board[0].length;c++){
                rtrn.board[r][c] = board[r][c];
            }
        }
        return rtrn;
    }

    //This method will simply print out the board
    public void printBoard(){
        System.out.print("  ");
        for(int i = 0; i<board.length;i++){
            System.out.print(i+1+ " ");
        }
        System.out.println();
        for(int i =0; i< board.length;i++){
            System.out.print("| ");
            for(int j =0; j<board[i].length;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.print("  ");
        for(int i = 0; i<board.length;i++){
            System.out.print(i+1 + " ");
        }
        System.out.println();
        System.out.println();
    }

    //This method will be used to check if the token can be placed, if it can't then it returns false
    public boolean checkPlace(int column){
        if(board[0][column]!='-'){
            return false;
        }
        return true;
    }
    //This method will place a token in the parameter slot, and will return false and won't
    //place a token anywhere if the desired slot is full.
    public void placeToken(int place, int column){
        char tok;
        if(place==1){
            tok = 'X';
        }else{
            tok = 'O';
        }
        if(board[SIZE-1][column]=='-'){
            board[SIZE-1][column] = tok;
            return;
        }
        for(int i =0; i < SIZE;i++){
            if(board[i][column]!='-'){
                board[i-1][column]=tok;
                return;
        }
        
    }
    }

    //This method checks if the board is full, resulting in a tie
    public boolean checkTie(){
        for(int i = 0; i<SIZE; i++){
            if(board[0][i]=='-'){
                return false;
            }
        }
        return true;
    }

    //check win will call its submethods to check on all 4 of the axes that you can win by, and then
    //will return an integer, 0 meaning nobody won, 1 meaning X won, 2 meaning O won
    public int checkWin(){
        
        char tok;
        for(int r = 0; r< board.length;r++){
            for(int c=0; c<board[r].length;c++){
                tok = board[r][c];
                boolean checkResult = false;
                if(tok!='-'){
                    if(c<=SIZE-4){
                            checkResult = checkRight(tok,r,c);                            
                    }
                    if(c<=SIZE-4 && r<=SIZE-4 && !checkResult){
                            checkResult = checkDownRight(tok,r,c);
                    }
                    if(c>=3 && r<=SIZE-4 && !checkResult){
                        checkResult = checkDownLeft(tok,r,c);
                    }
                    if(r<=SIZE-4 && !checkResult){
                        checkResult = checkDown(tok,r,c);
                    }
                    if(checkResult){
                        if(tok=='X'){
                            return 1;
                        }else{
                            return 2;
                        }
                    }
                }

            }
        }
        return 0;
    }
    public boolean checkRight(char token, int r, int c){
        int counter = 1;
        if(board[r][c+1]==token){
            counter++;
            if(board[r][c+2]==token){
                counter++;
                if(board[r][c+3]==token){
                    counter++;
                }
            }
        }
        if(counter==4){
            return true;
        }
        return false;
    }

    public boolean checkDownRight(char token, int r, int c){
        int counter = 1;
        if(board[r+1][c+1]==token){
            counter++;
            if(board[r+2][c+2]==token){
                counter++;
                if(board[r+3][c+3]==token){
                    counter++;
                }
            }
        }
        if(counter==4){
            return true;
        }
        return false;
    }

    public boolean checkDown(char token, int r, int c){
        int counter = 1;
        if(board[r+1][c]==token){
            counter++;
            if(board[r+2][c]==token){
                counter++;
                if(board[r+3][c]==token){
                    counter++;
                }
            }
        }
        if(counter==4){
            return true;
        }
        return false;
    }

    public boolean checkDownLeft(char token, int r, int c){
        int counter = 1;
        if(board[r+1][c-1]==token){
            counter++;
            if(board[r+2][c-2]==token){
                counter++;
                if(board[r+3][c-3]==token){
                    counter++;
                }
            }
        }
        if(counter==4){
            return true;
        }
        return false;
    }

}