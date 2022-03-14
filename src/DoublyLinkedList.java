package src;

// doubly linked list
// todo: skip nulls in toString()
public class DoublyLinkedList<T> {
    private static class Node<T> { // static bo ma być dostępny z poziomu instancji
        private T data;
        private Node<T> prev;
        private Node<T> next;

        public Node(T elem, Node<T> prev_, Node<T> next_) {
            data = elem;
            prev = prev_;
            next = next_; 
        }

        public T getElement() { return data; }
        public Node<T> getNext() { return next; }
        public Node<T> getPrev() { return prev; }
        public void setPrev(Node<T> prev_) { prev = prev_; }
        public void setNext(Node<T> next_) { next = next_; }
        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, null);
        head.setNext(tail);
    }
    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }
    public T first() {
        if(isEmpty()) return null;
        return head.getNext().getElement();
    }
    public T last() {
        if(isEmpty()) return null;
        return tail.getPrev().getElement();
    }
    private void addBetween(T elem, Node<T> before, Node<T> after) {
        Node<T> newNode = new Node<>(elem, before, after);
        before.setNext(newNode);
        after.setPrev(newNode);
        ++size;
    }
    public void insert(T elem, int index) {
        if(index < 0 || index > size) {
            System.out.println("index out of range");
            return;
        }
        if(index == 0)
            pushFront(elem);
        else if(index == size)
            pushBack(elem);
        else {
            Node<T> current = head;
            Node<T> node = new Node<T>(elem, null, null);
            for(int i=0; i<index; ++i) {
                current = current.getNext();
            }
            node.setNext(current.getNext());
            current.getNext().setPrev(node);
            current.setNext(node);
            current.setPrev(current);
        }
        ++size;
    }
    public void pushFront(T elem) {
        addBetween(elem, head, head.getNext());
    }
    public void pushBack(T elem) {
        addBetween(elem, tail.getPrev(), tail);
    }
    private T remove(Node<T> node) {
        Node<T> before = node.getPrev();
        Node<T> after = node.getNext();
        before.setNext(after);
        after.setPrev(before);
        --size;
        return node.getElement();
    }
    public T popBack() {
        if(isEmpty()) return null;
        return remove(tail.getPrev());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.getElement());
            if(trav.getNext() != null) {
                sb.append(", ");
            }
            trav = trav.getNext();
        }
        sb.append(" ]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> lista = new DoublyLinkedList<>();
        lista.pushBack(2);
        lista.pushBack(3);
        lista.pushFront(1);
        lista.pushBack(4);
        System.out.println(lista.toString());
        lista.insert(99, 2);
        System.out.println(lista.toString());
    }
}