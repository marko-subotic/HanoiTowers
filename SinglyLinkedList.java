public class SinglyLinkedList {
    static class Node {
        int data;
        Node next;
        
        Node() {
            // ADD YOUR CODE HERE.
        }
        
        Node(int value) {
            // ADD YOUR CODE HERE.
            data = value;
        }
        
        Node(int value, Node nextNode) {
            // ADD YOUR CODE HERE.
            data = value;
            next = nextNode;
        }
    }
    
    private Node head;
    
    public SinglyLinkedList() {
        // ADD YOUR CODE HERE.
        //head = null;
    }
    
    public SinglyLinkedList(int value) {
        // ADD YOUR CODE HERE.
        head = new Node(value);
    }
    
    public boolean isEmpty() {
        // ADD YOUR CODE HERE.
        if(head==null){
            return true;
        }
        return false;
    }
    
    public int size() {
        // ADD YOUR CODE HERE.
        int counter = 0;
        while(head!=null){
            head = head.next;
            counter ++;
        }
        return counter;
    }
    
    public String toString() {
        // ADD YOUR CODE HERE.
        String rtrn  = "";
        Node current = head;
        while(current!=null){
            rtrn += (current.data + " --> ");
            current = current.next;
        }
        rtrn += ("null");
        return rtrn;
    }
    
    public int get(int index) {
        // ADD YOUR CODE HERE.
        int counter = 0;
        Node current = head;
        while(counter<index && current!=null){
            current = current.next;
            counter ++;
        }
        if(current==null){
            throw new IndexOutOfBoundsException();
        }
        return current.data;
    }
    
    public boolean contains(int value) {
        // ADD YOUR CODE HERE.
        while(head!=null){
            if(head.data==value){
                return true;
            }
            head = head.next;
        }
        return false;
    }
    
    public void add(int value) {
        // ADD YOUR CODE HERE.
        Node current = head;
        Node previous = null;
        while(current!=null){
            previous = current;
            current = current.next;
        }
        if(current==null && previous == null){
            Node temp = new Node(value);
            this.head = temp;
        }
        else if(current==null && previous != null){
            Node now = new Node(value);
            previous.next = now;
        }
    }
    
    public void add(int index, int value) {
        // ADD YOUR CODE HERE.
        Node current = head;
        Node previous = null;
        int counter = 0;
        while(current!=null && counter<index){
            previous = current;
            current = current.next;
            counter++;
        }
        if(current==null && previous == null){
            head = new Node(value);
        }else if(current!=null &&previous == null){
            Node now = new Node(value,head);
            head = now;
        }else{
            Node now = new Node(value,current);
            previous.next = now;
        }
        
        if(current==null){
            throw new IndexOutOfBoundsException();
        }
    }
    
    public int remove() {
        // ADD YOUR CODE HERE.
        head= head.next;
        return head.data;
    }
    
    public int remove(int index) {
        // ADD YOUR CODE HERE.
        return head.data;
    }
    

    public static void main(String[] args)
	{
		SinglyLinkedList emptyList = new SinglyLinkedList();
		
		emptyList.add(0,-3);
		emptyList.add(0,-4);
		emptyList.add(-5);
		emptyList.add(-7);
        emptyList.add(3, -6);
	}
}