import java.util.*;

public class Tree<T> {
    Node<T> root;
    public static void main(String[] args) {
        //    4
        //   / \
        //  3   6
        // / \   \
        //7   1   10 
        //     \
        //      9
        Node<Integer> root = new Node<>(
            4,
            new Node<>(3, new Node<>(7), new Node<>(1, null, new Node<>(9))),
            new Node<>(6, null, new Node<>(10))
        );
        Tree<Integer> actuallyATree = new Tree<Integer>();
        actuallyATree.root = root;

        System.out.println(Tree.breadthFirstMax(actuallyATree));       

    }

    // find max
    // breadth first returned an array of strings
    // compared everything in the array to find the max

    // create a variable max
    // compare values on the nodes you iterate through
    // replace the max if the value is bigger
    // using breadth first

    public static int breadthFirstMax(Tree<Integer> tree){
        // if(!tree.instanceOf(new Tree<Integer>())) {
        //     throw new Exception("tree must have ints");
        // }
        if(tree.isEmpty()){
            throw new Exception("tree must have some vals");
        }

        int max = Integer.MIN_VALUE;

        Queue q = new Queue<Node<Integer>>();
        q.enqueue(tree.root);

        while(!q.isEmpty()){
            Node<Integer> temp = q.dequeue();
            // work

            // compare temp's value to max
            max = Math.max(max, temp.value);

            //finishing the iteration
            if(temp.left != null){
                q.enqueue(temp.left);
            } 
            if(temp.right != null){
                q.enqueue(temp.right);
            }

        }

        return max;


    }


    public List<T> breadthFirstSearch() {
        return breadthFirstSearch(this.root);
    }
    public static <T> List<T> breadthFirstSearch(Node<T> root) {
        Queue<Node<T>> nodesToProcess = new LinkedList<>();
        List<T> answer = new LinkedList<>();
        // start with the root node as the node to process
        nodesToProcess.add(root);
        // as long as there are still nodes to process, process them
        while (!nodesToProcess.isEmpty()) {
            Node<T> currentNode = nodesToProcess.eremove();
            // add current node's value to answer
            answer.add(currentNode.value);
            // plan to process the left and the right nodes
            if (currentNode.left != null) {
                nodesToProcess.add(currentNode.left);
            }
            if (currentNode.right != null) {
                nodesToProcess.add(currentNode.right);
            }
        }
        return answer;
    }
}

class Node<T> {
    T value;
    Node<T> left;
    Node<T> right;

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(T value) {
        this(value, null, null);
    }

    public Node () {}
}
