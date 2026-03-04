/*
Angelo Saginario 863003188
05/11/24
*/
public class PrinterTester {
    public static void main(String[] args) {

        Printer printer1 = new Printer("Thread 1");
        Printer printer2 = new Printer("Thread 2");

        printer1.start();
        printer2.start();


    }
}
