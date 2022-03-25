

// klucz: od najmniejszwgo do najwiekszego
public class MyPriorityQueue<T> {
    static class PrioData<T> {
        T data;
        int priority;
        PrioData(T data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }
    private DoublyLinkedList<PrioData<T>> queue = new DoublyLinkedList<>();
    
    // Metoda dodaje element zgodnie z wartoscia klucza
    public void enqueue(T newElement, int priority) {
        PrioData<T> newNode = new PrioData<>(newElement, priority);
        int idx = 0;
        for(PrioData<T> elem: queue) {
            if(priority < elem.priority)
                break;
            idx++;
        }
        queue.insert(newNode, idx);
    }

    // Metoda usuwa ostatni element z kolejki
    public T dequeue() {
        T data = queue.peekLast().data;
        queue.popBack();
        return data;
    }

    public void printAll() {
        for(PrioData<T> elem: queue) {
            System.out.println(elem.data);
        }
    }

    public DoublyLinkedList<PrioData<T>> shuffle() {
        DoublyLinkedList<PrioData<T>> list = new DoublyLinkedList<>();

        return list;
    }

    
    public static void main(String[] args) {
        MyPriorityQueue<String> queue = new MyPriorityQueue<String>();

        queue.enqueue("dwa", 20);
        queue.enqueue("jeden", 5);
        queue.enqueue("trzy", 30);
        queue.printAll();

        System.out.println("Usunieto ostatni element z kolejki: " + queue.dequeue().toString()); 
        queue.printAll();

    }
    
}