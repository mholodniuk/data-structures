import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyMessenger {
    private MyPriorityQueue<String> messageQueue;
    private int numberOfLines;
    
    public void setMessageQueue(MyPriorityQueue<String> message) {
        this.messageQueue = message;
    }

    public MyMessenger() {
        this.messageQueue = new MyPriorityQueue<>();
        numberOfLines = 0;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

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
            System.out.println("An exception has occured");
            exception.printStackTrace();
        }
    }

    public void shuffle() {
        messageQueue = messageQueue.shuffle();
    }

    public void printMessage() {
        messageQueue.printAll();
    }

    public static void main(String[] args) {
        MyMessenger mess = new MyMessenger();
        mess.readFromFile("message.txt");
        System.out.println("oryginalna wiadomosc");
        mess.printMessage();
        System.out.println("Number of lines read: " + mess.getNumberOfLines() + "\n");

        System.out.println("pomieszana wiadomosc");
        mess.shuffle();
        mess.printMessage();

        //System.out.println("posortowana wiadomosc");
    }
}
