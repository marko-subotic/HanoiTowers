package please;
import java.util.*;

public class QuickSort{
    
    public static void main (String[]args){
        int[] tester = {1,5,3,6,7,2};

        quickSort(tester);

        System.out.println(tester.toString());
    }

    public static void quickSort(int[] sorter){
        if(sorter.length>0){
            quickSort(sorter, 0, sorter.length-1);
        }

        return;
    }

    public static void quickSort(int[] sorter, int back, int front){
        if(back>front){
            return;
        }
        int starter = sorter[back];
        int iBack = back;
        int iFront = front;
        while(back<front){
            while(sorter[starter]>sorter[back]){
                back++;
                if(back>=front){
                    break;
                }
            }

            while(sorter[starter]<=sorter[front]){
                front--;
                if(back>=front){
                    break;
                }
            }
            swap(sorter, back, front);
        }
        swap(sorter, back, iBack);
        quickSort(sorter, iBack, back);
        quickSort(sorter, front, iFront);
    }

    private static void swap( int[] array, int one, int two){

        int temp = array[one];
        array[one] = array[two];
        array[two] = temp;
        
    }
}