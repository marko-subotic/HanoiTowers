public class ProblemSet03 {
    static class Node {
        public int data;
        public Node next;
    
        public Node(int data) {
            this.data = data;
        }
        
        // Make a copy of the given node.
        public Node(Node node) {
            this.data = node.data;
            this.next = node.next;
        }
        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }
    public static Node getMin(Node head) {
        // ADD YOUR CODE HERE
        Node min = new Node(head);
        while(head!= null){
            if(head.data<min.data){
                min = head;
            }
            head = head.next;
        }
        return min;
    }

    public static Node insert(Node root, int content){
        Node current = root;
        Node previous = null;
        while(current!=null && current.data<content){
            previous = current;
            current = current.next;
        }
        //Empty list case
        if(current==null && previous == null){
            Node now = new Node(content);
            return now;
        }//Insert at the very first of the list
        else if(current!=null &&previous == null){
            Node now = new Node(content,root);
            return now;            
        }//Insert at the very end of the list
        else if(current==null && previous != null){
            Node now = new Node(content);
            previous.next = now;
            return root;
        }
        //insert somewhere into the middle of the list
        Node now = new Node(content,current);
        previous.next = now;
        return root;
    }
    public static Node mergeTwoLists(Node head1, Node head2) {
        // ADD YOUR CODE HERE
         while(head2!= null){
            head1 = insert(head1, head2.data);
            head2 = head2.next;
        }
        return head1;
    }

    public static Node reverseList(Node head) {
        // ADD YOUR CODE HERE
        Node current = new Node(head.data);
        Node previous = null;
        while(head!=null){
            if(previous!=null){
                current.next = previous;
            }
            previous = current;
            head = head.next;
            current = new Node(head.data);
        }
        return previous;
    }

    public static boolean isPalindrome(Node head) {
        // ADD YOUR CODE HERE
        return false;
    }
    public static void print(Node root){
        while(root!=null){
            System.out.print(root.data + "--> ");
            root = root.next;
        }
        System.out.println("\b\b\b\b   ");
    }
    public static void main(String[] args)
	{
		Node head1 = new Node(1);
		head1.next = new Node(3);
		head1.next.next = new Node(5);
		head1.next.next.next = new Node(7);
		
		Node head2 = new Node(2);
		head2.next = new Node(4);
		head2.next.next = new Node(6);
		head2.next.next.next = new Node(8);
        Node headOfMergedList = ProblemSet03.mergeTwoLists(head1, head2);
        print(headOfMergedList);
        
        
		
	}
}