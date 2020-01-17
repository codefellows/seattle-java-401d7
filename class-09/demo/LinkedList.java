import java.util.StringJoiner;

public class LinkedList {
    Node head;
    static String description = "All linked lists are cool";

    public static void main(String[] args) {
        // countdown(5);
        LinkedList list = new LinkedList();
        list.insert(3);
        list.insert(2);
        list.insert(1);
        System.out.println(list.size() + " (should be 3)");

        LinkedList list2 = new LinkedList();
        list2.insert(14);
        list2.insert(13);
        list2.insert(12);
        list2.insert(11);
        System.out.println(list2.size() + " (should be 4)");

        LinkedList list3 = LinkedList.merge(list, list2);
        System.out.println(list3);

        // bad recursion
        // badRecursion();

        countDown(5);
    }

    public int size() {
        // int count = 0;
        // Node current = this.head;
        // while(current != null){
        //     count++;
        //     current = current.next;
        // }
        return size(this.head);
    }

    private int size(Node currentNode){
        if(currentNode == null){
            return 0;
        }
        return 1 + size(currentNode.next);
    };

    public void insert(int value) {
        this.head = new Node(value, this.head);
    }

    public String toString() {
        StringJoiner answer = new StringJoiner("->");
        Node current = head;
        while(current != null) {
            answer.add("" + current.value);
            current = current.next;
        }
        return answer.toString();
    }

    public static LinkedList merge(LinkedList list1, LinkedList list2) {
        // if (list1.head == null) {
        //     return list2;
        // }
        // Node current1 = list1.head;
        // Node current2 = list2.head;
        // while (current1 != null && current2 != null) {
        //     Node temp = current1.next;
        //     Node temp2 = current2.next;
        //     if (current1.next != null) {
        //         current2.next = current1.next;
        //     }
        //     current1.next = current2;
        //     current1 = temp;
        //     current2 = temp2;
        // }
        // return list1;

        Node head1 = list1.head;
        Node head2 = list2.head;
        list1.head = merge(head1, head2);
        return list1;

        // list1.head = merge(list1.head, list2.head);

    }

    private static Node merge(Node firstHead, Node secondHead){
        if(firstHead == null){
            return secondHead;
        } else{
            firstHead.next = merge(secondHead, firstHead.next);
            return firstHead;
        }
    }

    public static void badRecursion(){
        badRecursion();
    }

    public static void countDown(int n){
        // the base case: the point where my problem domain has gotten small or specific enough to finish
        if(n == 0){
            return;
        // recursive case : the work I do at each iteration, tries to make the problem smaller
        } else {
            System.out.println(n);
            countDown(n - 1);
        }
    }

}
