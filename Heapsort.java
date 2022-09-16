public class Heapsort {
    public static void sort(Comparable[] data) {
        // ADD YOUR CODE HERE.
        for(int i = data.length/2; i>=0;i--){
            sink(data, data.length,i);
        }
    }

    static void sink(Comparable[] heap, int size, int k) {
        // ADD YOUR CODE HERE.
        int rightNode = (k+1)*2;
        int leftNode = (k+1)*2-1;
        if(size==2){
            if(heap[k-1].compareTo(heap[leftNode])<0){
                exch(heap,k,leftNode);
            }
        }
        while(leftNode<size){
            int leftDif = heap[k].compareTo(heap[leftNode]);
            if(!(rightNode<size)){
                if(leftDif<0){
                    exch(heap,k,leftNode);
                    k = leftNode;
                    rightNode = (k+1)*2;
                    leftNode = (k+1)*2-1;
                }
            }else{
                int rightDif = heap[k].compareTo(heap[rightNode]);
                if(leftDif<0^rightDif<0){
                    if(leftDif<0){
                        exch(heap,k,leftNode);
                        k = leftNode;
                        rightNode = (k+1)*2;
                        leftNode = (k+1)*2-1;
                    }else{
                        exch(heap,k,rightNode);
                        k = rightNode;
                        rightNode = (k+1)*2;
                        leftNode = (k+1)*2-1;
                    }
                } else if(leftDif<0&&rightDif<0){
                    if(heap[rightNode].compareTo(heap[leftNode])>0){
                        exch(heap,k,rightNode);
                        k = rightNode;
                        rightNode =(k+1)*2;
                        leftNode =(k+1)*2-1;
                    }else{
                        exch(heap,k,leftNode);
                        k = leftNode;
                        rightNode =(k+1)*2;
                        leftNode = (k+1)*2-1;
                    }
                }else{
                    break;
                }
            }
        }
    }

    static void exch(Comparable[] heap, int i, int j) {
        // ADD YOUR CODE HERE.
        Comparable temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}

class Runner
{
	public static Integer[] runTest()
	{
		Integer data[] = {2, 9, 3, 4, 5, 7, 6, 0, 1, 8};
		Heapsort.sort(data);
		return data;
	}

	public static void main(String[] args)
	{
		Integer[] returnVal = runTest();
	}
}
