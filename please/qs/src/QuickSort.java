import java.util.*;

public class QuickSort{
    
    public static void main (String[]args){
        

        int size = 1000;

        int[] tester = new int[size];
        int[] testerC = new int[size];
        for(int i = 0; i<size; i++){
            tester[i] = (int)(Math.random()*size+1);
            testerC[i] = tester[i];
        }
        quickSort(tester);
        int max = Integer.MIN_VALUE;
        boolean fail = false;
        int index = -1;
        for(int i = 0; i<tester.length;i++){
            if(tester[i]>=max){
                max = tester[i];
            }else{
                fail = true;
                index = i;
            }
            System.out.print(tester[i]+ ", ");
        }
        System.out.print("\n");

        if(fail){
            System.out.println("index = " + index);
            for(int i = 0; i<size; i++){
                System.out.print(testerC[i]+ ", ");
            }
        }

    }

    public static void quickSort(int[] sorter){
        if(sorter.length>0){
            quickSort(sorter, 0, sorter.length-1);
        }

        return;
    }

    public static void quickSort(int[] sorter, int back, int front){
        if(back>=front){
            return;
        }
        int starter = sorter[back];
        int iBack = back;
        int iFront = front;
        while(back<front){
            while(starter<sorter[front] && !(back>=front)){
                front--;
                
            }

            while(starter>=sorter[back] && !(back>=front)){
                back++;
                
            }

            
            swap(sorter, back, front);
        }
        swap(sorter, back, iBack);
        quickSort(sorter, iBack, back-1);
        quickSort(sorter, front+1, iFront);
    }

    private static void swap( int[] array, int one, int two){

        int temp = array[one];
        array[one] = array[two];
        array[two] = temp;
        
    }
}