package org.example;

public class AddTwoNumbers {
    public static Node addTwoNumbers(Node list1, Node list2) {
        int carry = 0;
        Node node = new Node(0);
        Node pos;
        pos = node;
        while (list1 != null || list2 != null || carry != 0) {
            if (list1 != null) {
                carry += list1.data;
                list1 = list1.next;
            }
            if (list2 != null) {
                carry += list2.data;
                list2 = list2.next;
            }
           pos.next = new Node(carry % 10);
            carry /= 10;
            pos = pos.next;
        }
        return node.next;
}


static class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

}