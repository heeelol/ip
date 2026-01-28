import java.util.Scanner;

public class Yoru {
    public static void main(String[] args) {
        String line = "_______________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Yoru");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);


        while (true) {
            String reply = scanner.nextLine();
            System.out.println(line);

            if (reply.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            }

            System.out.println(reply);
            System.out.println(line);
        }
    }
}