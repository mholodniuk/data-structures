

class DoublyLinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        private T data;
        private Node<T> prev, next;
    
        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next; 
        }
    
        public T getElement() { return data; }
        public Node<T> getNext() { return next; }
        public Node<T> getPrev() { return prev; }
        public void setPrev(Node<T> prev) { this.prev = prev; }
        public void setNext(Node<T> next) { this.next = next; }
    }

    private Node<T> head = null, tail = null;
    private int size = 0;

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

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void pushBack(T elem) {
        if(isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            tail.setNext(new Node<T>(elem, tail, null));
            tail = tail.getNext();
        }
        size++;
    }

    public void pushFront(T elem) {
        if(isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.setPrev(new Node<T>(elem, null, head));
            head = head.getPrev();
        }
        size++;
    }

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

    public T peekFirst() throws RuntimeException {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.getElement();
    }

    public T peekLast() throws RuntimeException {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.getElement();
    }

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

    public T at(int index) {
        if(index < 0 || index > size) 
            throw new IndexOutOfBoundsException("index out of bound");
        Node<T> current = head;
        while(current != null && index-- > 0)
            current = current.getNext();
        return current.getElement();
    }

    public boolean remove(Object obj) {
        Node<T> trav = head;
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.getNext()) {
                if (trav.getElement() == null) {
                    remove(trav);
                    return true;
                }
            }
        } else {
            for (trav = head; trav != null; trav = trav.getNext()) {
                if (obj.equals(trav.getElement())) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

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
}