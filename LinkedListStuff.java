public class LinkedListStuff{
    static public class Node{
        public int data;
        public Node next;
        public Node(int data){
            this.data = data;
        }
        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
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
    public static void print(Node root){
        while(root!=null){
            System.out.print(root.data + "--> ");
            root = root.next;
        }
        System.out.println("\b\b\b\b   ");
    }
    public static void main(String[]args){
        //This is to test an insertion with 0 elements in the list
        Node root = insert(null, 5);
        print(root);
        //This is a test for insertion with 1 and a smaller value to insert
        root = insert(root, 4);
        print(root);
        //This is a test for insertion with 1 and a larger value to insert
        root = insert(null, 1);
        root = insert(root, 2);
        print(root);
    }
}