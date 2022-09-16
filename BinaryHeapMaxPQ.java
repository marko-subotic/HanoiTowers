
class BinaryHeapMaxPQ<Key extends Comparable<Key>> {
    // ADD YOUR CODE HERE.
    private int size;
    private Key[] heap;
    public BinaryHeapMaxPQ() {
        // ADD YOUR CODE HERE.
        heap = (Key[]) new Comparable[2];
        size = 0;
    }

    public void insert(Key key) {
        // ADD YOUR CODE HERE.
        if(key==null){
            throw new IllegalArgumentException();
        }
        if(size+1==heap.length){
           resize(heap.length*2);
        }
        heap[size+1] = key;
        swim(size+1);
        size ++;
    }

    public Key delMax() {
        // ADD YOUR CODE HERE.
        Key hold = max();
        if(size==0){
            throw new IllegalStateException();
        }
        if(size==heap.length/4){
           resize(heap.length/2);
        }
        heap[1] = heap[size];
        heap[size] = null;
        size--;
        sink(1);
        return hold;
    }

    public Key max() {
        // ADD YOUR CODE HERE.
        if(size==0){
            throw new IllegalStateException();
        }
        return heap[1];
    }

    public boolean isEmpty() {
        // ADD YOUR CODE HERE.
        if(size==0){
            return true;
        }
        return false;
    }

    public int size() {
        // ADD YOUR CODE HERE.
        return size;
    }

    private void resize(int capacity) {
        // ADD YOUR CODE HERE.
        Key[] copy = (Key[]) new Comparable[capacity];
           for(int i = 0; i < Math.min(heap.length,copy.length); i++){
               copy[i] = heap[i];
           }
           heap = (Key[]) new Comparable[copy.length];
           for(int i = 0; i < heap.length; i++){
               heap[i] = copy[i];
           }
    }

    private void sink(int k) {
        // ADD YOUR CODE HERE.
        int rightNode = k*2+1;
        int leftNode = k*2;
        if(size==2){
            if(heap[k].compareTo(heap[leftNode])<0){
                exch(k,leftNode);
            }
        }
        while(leftNode<size||rightNode<size){
            int leftDif = heap[k].compareTo(heap[leftNode]);
            int rightDif = heap[k].compareTo(heap[rightNode]);
            if(leftDif<0^rightDif<0){
                if(leftDif<0){
                    exch(k,leftNode);
                    k = leftNode;
                    rightNode = k*2+1;
                    leftNode = k*2;
                }else{
                    exch(k,rightNode);
                    k = rightNode;
                    rightNode = k*2+1;
                    leftNode = k*2;
                }
            } else if(leftDif<0&&rightDif<0){
                if(heap[rightNode].compareTo(heap[leftNode])>0){
                    exch(k,rightNode);
                    k = rightNode;
                    rightNode = k*2+1;
                    leftNode = k*2;
                }else{
                    exch(k,leftNode);
                    k = leftNode;
                    rightNode = k*2+1;
                    leftNode = k*2;
                }
            }else{
                break;
            }
        }
        
    }

    private void swim(int k) {
        // ADD YOUR CODE HERE.
        boolean needStop = false;
        while(!needStop){
            if(k==1){
                break;
            }if(heap[k].compareTo(heap[k/2])>0){
                exch(k,k/2);
                k/=2;
            }else{
                break;
            }
        }
    }

    private void exch(int i, int j) {
        // ADD YOUR CODE HERE.
        Key temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}

class TestHelper {
    static <Key extends Comparable<Key>> Comparable[] getDataArray(BinaryHeapMaxPQ<Key> pq) {
        java.lang.reflect.Field contentsField = null;
        java.lang.reflect.Field[] fields = BinaryHeapMaxPQ.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().equals(Comparable[].class)) {
                contentsField = fields[i];
                break;
            }
        }
        if (contentsField == null) {
            throw new IllegalStateException("[fail] Missing Key[] field.");
        }
        contentsField.setAccessible(true);
        try {
            return (Comparable[]) contentsField.get(pq);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static <Key extends Comparable<Key>> void validateHeapInvariant(BinaryHeapMaxPQ<Key> pq) {
        validateHeapInvariant(getDataArray(pq), pq.size());
    }

    static void validateHeapInvariant(Comparable[] data, int size) {
        if (data.length < size) {
            System.out.println("[fail] a[] field must contain at least size elements; size: " + size + "; a[] field length: " + data.length);
            return;
        }
        for (int i = 1; i < size/2; ++i) {
            int leftChildIndex = 2*i;
            int rightChildIndex = 2*i + 1;
            Comparable key = data[i];
            Comparable leftChild = data[leftChildIndex];
            Comparable rightChild = (rightChildIndex < size) ? data[rightChildIndex] : null;
            if (key == null) {
                System.out.println("[fail] Unexpected null element at index: " + i);
                continue;
            }
            if (leftChild == null) {
                System.out.println("[fail] Unexpected null element at index: " + leftChildIndex);
                continue;
            }
            if (key.compareTo(leftChild) < 0) {
                System.out.println("[fail] Parent (" + key + ") is smaller than one of its children (" + leftChild + ")");
            }
            if (rightChild != null && key.compareTo(rightChild) < 0) {
                System.out.println("[fail] Parent (" + key + ") is smaller than one of its children (" + rightChild + ")");
            }
        }
    }

    static <Key extends Comparable<Key>> boolean validateHeapOrder(BinaryHeapMaxPQ<Key> pq, Key[] expected) {
        Comparable[] actual = TestHelper.getDataArray(pq);
        if (actual.length <= expected.length) {
            System.out.println("[fail] a[] size is too small; must be at least " + (expected.length + 1) + "; actual size: " + actual.length);
            return false;
        }
        if (actual[0] != null) {
            System.out.println("[fail] a[0] must be null");
            return false;
        }
        for (int i = 1; i <= expected.length; ++i) {
            if (actual[i] == null || !actual[i].equals(expected[i-1])) {
                System.out.println("[fail] expected and actual arrays differ; "
                    + "expected: " + java.util.Arrays.toString(expected)
                    + "; actual: " + java.util.Arrays.toString(java.util.Arrays.copyOfRange(actual, 1, pq.size() + 1)));
                return false;
            }
        }
        return true;
    }
}

class Tor
{
	public static String[] runTest()
	{
		BinaryHeapMaxPQ<String> pq = new BinaryHeapMaxPQ<String>();
		String[] results = new String[10];
		for (int i = 0; i < 10; i++) {
		    pq.insert(Integer.toString(i));
		}
		System.out.println("Max item in priority queue is: " + pq.max());
		TestHelper.validateHeapInvariant(pq);
		for (int i = 0; i < 10; i++) {
		    pq.insert(pq.delMax());
		}
		TestHelper.validateHeapInvariant(pq);
		for (int i = 0; i < 10; i++) {
		    results[i] = pq.delMax();
		}
		return results;
	}

	public static void main(String[] args)
	{
		String[] returnVal = runTest();
	}
}
