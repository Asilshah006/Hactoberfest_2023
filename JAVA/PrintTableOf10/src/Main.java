public class Main {
    public static void main(String[] args) {
        printTableOf(10);
    }

    public static void printTableOf(int num){
        int index = 1;
        while(index<=10){
            System.out.println(num + " x " + index + " = " + num * index++);
        }
    }
}