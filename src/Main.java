import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SocketService socketService = new SocketService();
        String greeting;
        boolean isValidGreeting = false;
        socketService.initConnect();
        socketService.out.println("Будь ласка, привітайтесь");
        do {

            try {
                greeting = socketService.in.readLine().toLowerCase();
                if (socketService.greetings.contains(greeting)) {
                    socketService.examUser(greeting);
                    isValidGreeting = true;
                } else {
                    socketService.out.println("Невірне привітання. Спробуйте ще");
                }
            } catch (Exception e) {
                e.printStackTrace();
                isValidGreeting = false;
            }
        } while (!isValidGreeting);
    }
}