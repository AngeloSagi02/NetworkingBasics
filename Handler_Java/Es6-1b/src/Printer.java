
/*
Angelo Saginario 863003188
05/11/24
*/
public class Printer implements Runnable {

    String message;

    public Printer(String message) {
        this.message = message;
    }

    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i + " " + message);
        }
    }
}