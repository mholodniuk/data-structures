
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
    
    public void enqueue(T newElement, int priority) {
        PrioData<T> newNode = new PrioData<>(newElement, priority);
        int idx = 0;
        for(PrioData<T> elem: queue) {
            if(priority > elem.priority)
                break;
            idx++;
        }
        queue.insert(newNode, idx);
    }

    public T dequeue() {
        T data = queue.peekFirst().data;
        queue.popFront();
        return data;
    }

    public void printAll() {
        for(PrioData<T> elem: queue) {
            System.out.println(elem.data);
        }
    }

    public static void main(String[] args) {
        MyPriorityQueue<String> queue = new MyPriorityQueue<String>();

        queue.enqueue("dwa", 20);
        queue.enqueue("trzy", 5);
        queue.enqueue("jeden", 30);

        queue.printAll();
    }
}
