/*
Angelo Saginario 863003188
05/11/24
*/
public class PrinterTester {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Printer("Thread 1"));
        Thread t2 = new Thread(new Printer("Thread 2"));

        t1.start();
        t2.start();
    }
}
