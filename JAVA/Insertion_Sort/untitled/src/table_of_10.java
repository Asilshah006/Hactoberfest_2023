public class MultiplicationTable {
    public static void main(String[] args) {
        int number = 10;
        
        System.out.println("Multiplication Table for 10:");
        for (int i = 1; i <= 10; i++) {
            int result = number * i;
            System.out.println(number + " x " + i + " = " + result);
        }
    }
}
