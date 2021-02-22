import java.util.Arrays;
public class Queens{
    public static void findPos(char[][] board, int n){
        int nY = 0;
        while(nY<board.length){
            if(canPlace(board, n, nY)){
                if(n==board[0].length-1){
                    board[nY][n]='Q';
                    for (int i = 0; i < board.length; i++)
                        System.out.println(Arrays.toString(board[i]));
                    System.out.println();
                    eraseColumn(board, n);
                    return;
                }
                board[nY][n]='Q';
                findPos(board, n+1);
                eraseColumn(board, n);
            }
            nY++;
        }
        return;
    }
    public static boolean canPlace(char[][] board, int x, int y){
        for(int i = 0; i < x; i++){
            for(int j = 0; j <board.length; j++){
                if(board[j][i]!='-'){
                    if(Math.abs(x-i)==Math.abs(y-j)){
                        return false;
                    }
                }
            }
            if(board[y][i]!='-'){
                return false;
            }
        }
        return true;
    }
    public static void eraseColumn(char[][] board, int column){
        for(int i = 0; i < board.length; i ++)
            board[i][column] = '-';
    }
    public static void main(String[]args){
        char[][] board = new char[8][8];
        for(int r = 0; r<board.length;r++){
            for(int c = 0; c <board[r].length; c++){
                board[r][c] = '-';
            }
        }
        findPos(board,0);
    }
}