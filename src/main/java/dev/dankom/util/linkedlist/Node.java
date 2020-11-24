package dev.dankom.util.linkedlist;

public class Node<T> {
    private final T value;
    private Node next;

    public Node(T value) {
        this.value = value;
    }

    public Node setNext(Node next) {
        this.next = next;
        return next;
    }

    public Node next() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
