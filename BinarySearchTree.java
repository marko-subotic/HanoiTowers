import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearchTree<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
    }

    Node root;

    public TValue insert(TKey key, TValue value) {
        // ADD YOUR CODE HERE.
        Node newN = new Node();
        newN.value = value;
        newN.key = key;
        if(root==null){
            root = newN;
            return null;
        }else if(root.key.compareTo(key)>0){
            if(root.left==null){
                root.left = newN;
                return null;
            }
            return insert(root,newN);
        }else if(root.key.compareTo(key)<0){
            if(root.right==null){
                root.right = newN;
                return null;
            }
            return insert(root,newN);
        }else{
            TValue temp = root.value;
            root.value = value;
            return temp;
        }
    }

    private TValue insert(Node current, Node newNode) {
        // ADD YOUR CODE HERE.
        if(current==null){
            current = newNode;
            return null;
        }else if(current.key.compareTo(newNode.key)>0){
            if(current.left==null){
                current.left = newNode;
                return null;
            }
            return insert(current.left, newNode);
        }else if(current.key.compareTo(newNode.key)<0){
            if(current.right==null){
                current.right = newNode;
                return null;
            }
            return insert(current.right, newNode);
        }else{
            TValue temp = current.value;
            current.value = newNode.value;
            return temp;
        }
    }

    public TValue find(TKey key) {
        // ADD YOUR CODE HERE.
        if(root==null){
            return null;
        }if(root.key.compareTo(key)>0){
            return find(root.left, key);
        } if(root.key.compareTo(key)<0){
            return find(root.right,key);
        }else{
            return root.value;
        }
    }

    private TValue find(Node current, TKey key) {
        // ADD YOUR CODE HERE.
        if(current==null){
            return null;
        } if(current.key.compareTo(key)>0){
            return find(current.left, key);
        } if(current.key.compareTo(key)<0){
            return find(current.right, key);
        }else{
            return current.value;
        }
    }

    public double avgHeight() {
        // ADD YOUR CODE HERE.
        ArrayList<Integer> depths = new ArrayList<Integer>();
        if(root==null){
            return 0;
        } if(root.right!=null){
            depthCounter(root.right, 1, depths);
        } if(root.left!=null){
            depthCounter(root.left, 1, depths);
        }
        double counter =0;
        for(int i = 0; i<depths.size();i++){
            counter += depths.get(i);
        }
        System.out.print("Average Height: " + (counter/depths.size()));
        return counter/depths.size();
    }

    private void depthCounter(Node current, int counter, ArrayList<Integer> depths) {
        // ADD YOUR CODE HERE.
        if(current.right!=null){
            depthCounter(current.right, counter +1, depths);
        } if(current.left!=null){
            depthCounter(current.left, counter +1, depths);
        } else if(current.right==null && current.left==null){
            depths.add(counter);
            System.out.println("Key: " + current.key + " counter: "+ counter);
        }
    }

    public static void main(String[]args){
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String[] lineArr = line.split(", ");
        int adder;
        //System.out.println(lineArr.toString());
        for(String number: lineArr){
            tree.insert(Integer.parseInt(number), 0);
            System.out.print(number+ ",");
        }
        /*while(input.hasNextInt()){
            adder = input.nextInt();
            tree.insert(adder, 0);
            System.out.println(adder);
            
        }*/
        input.close();
        double result = tree.avgHeight();
        
    }
}