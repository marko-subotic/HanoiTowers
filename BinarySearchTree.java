
class BinarySearchTree<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
    }

    Node root;

    /**
     * Deletes the minimum node in the binary search tree.
     */
    public void deleteMin() {
        // ADD YOUR CODE HERE.
        if(root==null){
            return;
        }
        Node min = min(root);
        TKey key = min.key;
        if(root.key.compareTo(key)==0){
            if(root.right==null){
                root=null;
            }else{
                    root=root.right;
                }
        }else if(root.key.compareTo(key)>0){
            root.left = deleteMin(root.left);
        }
    }

    /**
     * Deletes the minimum node in the subtree with root {@param node}.
     * 
     * Returns the root of the new subtree at {@param node}'s initial position.
     */
    private Node deleteMin(Node node) {
        // ADD YOUR CODE HERE.
        Node min = min(node);
        TKey key = min.key;
        if(node.key.compareTo(key)==0){
            if(node.right==null){
                node=null;
            }else{
                node=node.right;
            }
        }else if(node.key.compareTo(key)>0){
            node.left = deleteMin(node.left);
        }
        return node;
    }

    /**
     * Deletes the node with key equal to {@param key} in the binary search tree.
     */
    public void delete(TKey key) {
        // ADD YOUR CODE HERE.
        if(root==null){
            return;
        }if(root.key.compareTo(key)==0){
            if(root.right==null&&root.left==null){
                root=null;
            }else if(root.right!=null^root.left!=null){
                if(root.right!=null){
                    root=root.right;
                }else{
                    root=root.left;
                }
            }else{
                Node min = min(root.right);
                root.value = min.value;
                root.key = min.key;
                root.right = delete(min, min.key);
            }
        }else if(root.key.compareTo(key)<0){
            root.right = delete(root.right,key);
        }else if(root.key.compareTo(key)>0){
            root.left = delete(root.left,key);
        }
    }

    /**
     * Deletes the node with key equal to {@param key} in the subtree with root {@param node}.
     * 
     * Returns the root of the new subtree at {@param node}'s initial position.
     */
    private Node delete(Node node, TKey key) {
        // ADD YOUR CODE HERE.
        if(node.key.compareTo(key)==0){
            if(node.right==null&&node.left==null){
                node=null;
            }else if(node.right!=null^node.left!=null){
                if(node.right!=null){
                    node=node.right;
                }else{
                    node=node.left;
                }
            }else{
                Node min = min(node.right);
                node.value = min.value;
                node.key = min.key;
                min = null;
            }
        }else if(node.key.compareTo(key)<0){
            node.right = delete(node.right,key);
        }else if(node.key.compareTo(key)>0){
            node.left = delete(node.left,key);
        }
        return node;
    }

    /**
     * Finds and returns the minimum node starting at the subtree with root {@param node}.
     * 
     * Hint: Use this to implement Case 3 (2 children) in Hibbard deletion.
     */
    private Node min(Node current) {
        // ADD YOUR CODE HERE.
        if(current.left!= null){
            return min(current.left);
        }else{
            return current;
        }
    }

    // This is used by our test code. Do not change.
    Node newNode(TKey key, TValue value, Node left, Node right) {
        Node node = new Node();
        node.key = key;
        node.value = value;
        node.left = left;
        node.right = right;
        return node;
    }
}

