package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Implementacja interfejsu aplikacji typu Messenger
// korzystajacej z kolejki priorytetowej 
public class MyMessenger {
    // kolejka wiadomosci
    private MyPriorityQueue<String> messageQueue;
    // liczba pakietow/linii
    private int numberOfLines;

    // Konstruktor domyslny
    public MyMessenger() {
        this.messageQueue = new MyPriorityQueue<>();
        numberOfLines = 0;
    }

    // Metoda dodajaca wiadomosc do kolejki
    public void enqueue(String message) {
        this.messageQueue.enqueue(message, numberOfLines);
        numberOfLines++;
    }

    // Metoda czyszczaca kolejke wiadomosci
    public void clear() {
        while(!messageQueue.isEmpty()) {
            messageQueue.dequeue();
        }
        // kazde wyslanie wiadomosci resetuje sesje kolejki
        numberOfLines = 0;
    }

    // Metoda (testowa) pozwalajaca na wczytanie wiadomosci z pliku
    public void readFromFile(String filename) {
        try {
            File messageFile = new File(filename);
            Scanner myReader = new Scanner(messageFile);
            while(myReader.hasNextLine()) {
                String line = myReader.nextLine();
                messageQueue.enqueue(line, numberOfLines);
                ++numberOfLines;
            }
            myReader.close();
        }
        catch(FileNotFoundException exception) {
            System.out.println("An exception has occured - file not found");
            exception.printStackTrace();
        }
    }

    // Metoda symulujaca mieszanie sie wiadomosci
    public void shuffle() {
        messageQueue = messageQueue.shuffle();
    }

    // Metoda symulujaca odbieranie wiadomosci
    public void sort() {
        messageQueue = messageQueue.sort();
    }
    
    // Metoda wypisujaca zawartosc wiadomosci
    public void printMessage() {
        messageQueue.printAll();
        System.out.println("\n");
    }
    public static void main(String[] args) {
        MyMessenger mess = new MyMessenger();
        mess.readFromFile("res/message.txt");
        
        System.out.println("Oryginalna wiadomosc: ");
        mess.printMessage();

        System.out.println("Pomieszana wiadomosc: ");
        mess.shuffle();
        mess.printMessage();

        System.out.println("Posortowana wiadomosc: ");
        mess.sort();
        mess.printMessage();
    }
}
