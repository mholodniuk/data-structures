package src;

// Implementacja listy dwukierunkowej
// wzbogacona o interfejs Iterable w celu latwiejszej implementacji kolejki
class DoublyLinkedList<T> implements Iterable<T> {
    // podklasa reprezentujaca wezel w liscie dwukierunkowej
    private static class Node<T> {
        private T data;
        private Node<T> prev, next;
        // konstruktor domyslny
        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next; 
        }
        // metody dostepowe
        public T getElement() { return data; }
        public Node<T> getNext() { return next; }
        public Node<T> getPrev() { return prev; }
        public void setPrev(Node<T> prev) { this.prev = prev; }
        public void setNext(Node<T> next) { this.next = next; }
    }
    // wskazniki na "glowe" i "ogon" listy
    private Node<T> head = null, tail = null;
    private int size = 0;

    // Metoda usuwa cala zawartosc listy
    public void clearAll() {
        Node<T> current = head;
        while(current != null) {
            Node<T> next = current.getNext();
            current.setPrev(null);
            current.setNext(null);
            current.data = null;
            current = next;
        }
        head = tail = current = null;
        size = 0;
    }
    // Metoda zawraca rozmiar listy 
    public int getSize() {
        return size;
    }
    // Metoda zwraca czy lista jest pusta
    public boolean isEmpty() {
        return size == 0;
    }
    // Metoda dodajaca element na koniec listy
    public void pushBack(T elem) {
        if(isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            tail.setNext(new Node<T>(elem, tail, null));
            tail = tail.getNext();
        }
        size++;
    }
    // Metoda dodajaca element na poczatek listy
    public void pushFront(T elem) {
        if(isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.setPrev(new Node<T>(elem, null, head));
            head = head.getPrev();
        }
        size++;
    }
    // Metoda dodajaca element w podane miejsce w liscie
    public void insert(T elem, int index) throws IndexOutOfBoundsException {
        if(index < 0 || index > size) 
            throw new IndexOutOfBoundsException("index out of bound");
        if(index == 0) {
            pushFront(elem);
            return;
        }    
        if(index == size) {
            pushBack(elem);
            return;
        }
        Node<T> tmp = head;
        for(int i=0; i < index-1; ++i)
            tmp = tmp.getNext();
        Node<T> newNode = new Node<T>(elem, tmp, tmp.getNext());
        tmp.getNext().setPrev(newNode);
        tmp.setNext(newNode);
        
        size++;
    }
    // Metoda pozwala "zagladnac"/sprawdzic pierwszy element
    public T peekFirst() throws RuntimeException {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.getElement();
    }
    // Metoda pozwala "zagladnac"/sprawdzic ostatni element
    public T peekLast() throws RuntimeException {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.getElement();
    }
    // Metoda usuwajaca pierwszy element listy
    public T popFront() throws RuntimeException {
        if(isEmpty()) 
            throw new RuntimeException("Empty list");
        T data = head.getElement();
        head = head.getNext();
        size--;
        if(isEmpty()) 
            tail = null;
        else
            head.setPrev(null);
        return data;
    }
    // Metoda usuwajaca ostatni element listy
    public T popBack() throws RuntimeException {
        if(isEmpty()) 
            throw new RuntimeException("Empty list");
        T data = tail.getElement();
        tail = tail.getPrev();
        size--;
        if(isEmpty()) 
            head = null;
        else
            tail.setNext(null);
        return data;
    }
    // Metoda zwracajaca element o zadanym indeksie
    public T at(int index) {
        if(index < 0 || index > size) 
            throw new IndexOutOfBoundsException("index out of bound");
        Node<T> current = head;
        while(current != null && index-- > 0)
            current = current.getNext();
        return current.getElement();
    }
    // Implementacja iteratora dla listy dwukierunkowej
    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;
  
            @Override
            public boolean hasNext() {
                return trav != null;
            }
  
            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
  
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.pushBack(1);
        list.pushBack(3);
        list.pushBack(4);
        list.pushFront(0);
        list.insert(2, 2);

        for(Integer elem: list)
            System.out.println(elem);

        System.out.println("Element o indeksie 1: " + list.at(1) + "\n");

        list.popBack();
        list.popFront();
        System.out.println("Stan po operacji popBack() i popFront()");
        for(Integer elem: list)
            System.out.println(elem);

    }
}