import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyMessenger {
    MyPriorityQueue<String> messageQueue;
    
    public MyMessenger() {
        this.messageQueue = new MyPriorityQueue<>();
    }

    public void readFromFile() {
        try {
            int idx = 0;
            File messageFile = new File("message.txt");
            Scanner myReader = new Scanner(messageFile);
            while(myReader.hasNextLine()) {
                String line = myReader.nextLine();
                messageQueue.enqueue(line, idx);
                ++idx;
            }
            myReader.close();
        }
        catch(FileNotFoundException exception) {
            System.out.println("An exception has occured");
            exception.printStackTrace();
        }
    }

    public static void main() {
        MyMessenger mess = new MyMessenger();
        mess.readFromFile();
        mess.messageQueue.printAll();
    }

}
