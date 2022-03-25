

public class Main {
    public static void main(String[] args) {
        MyMessenger Jan = new MyMessenger();
        //MyMessenger Anna = new MyMessenger();

        // jan pisze wiadomosc
        Jan.readFromFile("message.txt");

        // pomieszana wiadomosc od jana dochodzi do anny
        //Anna.setMessageQueue(Jan.shuffle());
        
        // anna musi poskladac wiadomosc zgodnie z kolejnoscia

    }
}
