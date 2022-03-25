import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyMessenger {
    private MyPriorityQueue<String> messageQueue;
    private int numberOfLines;
    
    public MyMessenger() {
        this.messageQueue = new MyPriorityQueue<>();
        numberOfLines = 0;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void readFromFile() {
        try {
            File messageFile = new File("message.txt");
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

    public static void main(String[] args) {
        MyMessenger mess = new MyMessenger();
        mess.readFromFile();
        mess.messageQueue.printAll();
        System.out.println("Number of lines read:" + mess.getNumberOfLines());
    }

}
