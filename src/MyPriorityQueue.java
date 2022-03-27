package src;
import java.util.concurrent.ThreadLocalRandom;

// Implementacja kolejki priorytetowej
// klucz: od najmniejszwgo do najwiekszego
public class MyPriorityQueue<T> {
    // podklasa reprezatujaca strukture zawarta w kolejce
    class PrioData {
        T data;
        int priority;
        public PrioData(T data, int priority) {
            this.data = data;
            this.priority = priority;
        }
        public int getPriority() {
            return this.priority;
        }
        public T getData() {
            return this.data;
        }
    }
    // implementacja kolejki za pomoca listy dwukierunkowej
    private DoublyLinkedList<PrioData> queue;

    // konstruktor domyslny
    public MyPriorityQueue() {
        this.queue = new DoublyLinkedList<>();
    }
    
    // Metoda dodaje element zgodnie z wartoscia klucza
    public void enqueue(T newElement, int priority) {
        PrioData newNode = new PrioData(newElement, priority);
        int idx = 0;
        for(PrioData elem: queue) {
            if(priority < elem.priority)
                break;
            idx++;
        }
        queue.insert(newNode, idx);
    }

    // Metoda usuwa ostatni element (dane + priorytet) z kolejki
    public PrioData dequeue() {
        T data = queue.peekLast().data;
        int priority = queue.peekLast().priority;
        queue.popBack();
        return new PrioData(data, priority);
    }

    // wypisanie zawartosci kolejki od poczatku
    public void printAll() {
        for(PrioData elem: queue) {
            System.out.println(elem.getData() + " " + elem.getPriority());
        }
    }

    // sprawdzenie czy kolejka jest pusta
    public boolean isEmpty() {
        if(queue.getSize() == 0) 
            return true;
        else 
            return false;
    }

    // symulacja przemieszania kolejnosci w kolejce
    public MyPriorityQueue<T> shuffle() {
        MyPriorityQueue<T> newQueue = new MyPriorityQueue<>();
		for(int i=0; i<queue.getSize(); ++i) {
            if(ThreadLocalRandom.current().nextInt() % 2 == 0)
                newQueue.queue.pushBack(queue.at(i));
            else 
                newQueue.queue.pushFront(queue.at(i));
        }
        return newQueue;
    }

    // sortowanie nieposortowanej kolejki
    public MyPriorityQueue<T> sort() {
        PrioData tmp;
        MyPriorityQueue<T> newQueue = new MyPriorityQueue<>();
        while(!queue.isEmpty()) {
            tmp = dequeue();
            newQueue.enqueue(tmp.getData(), tmp.getPriority());
        }
        return newQueue;
    }

    public static void main(String[] args) {
        MyPriorityQueue<String> queue = new MyPriorityQueue<String>();

        queue.enqueue("dwa", 1);
        queue.enqueue("jeden", 0);
        queue.enqueue("trzy", 2);
        queue.enqueue("piec", 4);
        queue.enqueue("cztery", 3);
        queue.printAll();
        System.out.println("\n");

        MyPriorityQueue<String> kolejka = queue.shuffle();
        kolejka.printAll();
        System.out.println("\n");

        kolejka = kolejka.sort();
        kolejka.printAll();
    }
}