import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class SocketService {
    final int port = 8081;
    ServerSocket serverSocket;
    Socket conectUser;
    BufferedReader in;
    PrintWriter out;
    List<String> greetings = Arrays.asList("hello",
            "hi",
            "привет",
            "привіт",
            "доброго дня",
            "добрый день",
            "здраствуйте");

    public void initConnect() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Сервер чекає підключення користувача" + port + "...");
            conectUser = serverSocket.accept();
            System.out.println("Коритсувач приєднався з адреси " + conectUser.getInetAddress().getHostAddress());
            in = new BufferedReader(new InputStreamReader(conectUser.getInputStream()));
            out = new PrintWriter(conectUser.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean russianWord(String greeting) {
        if (greeting.equals("привет") || greeting.matches(".*[ЁЙЫЭЮЯёйыэюя].*")) return true;
        return false;
    }

    public void timeOnScreen() {
        try {
            out.println("Доброго дня !");
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            out.println("Дата: " + currentDate + ", Час: " + currentTime);
            out.println("Програма завершена, бувайте ");
            conectUser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void examUser(String greeting) {
        try {
            System.out.println("Користувач сказав: " + greeting);
            if (russianWord(greeting)) {
                out.println("Що таке паляниця?");
                String answer = in.readLine();
                if (answer.equalsIgnoreCase("хлеб")
                        || answer.equalsIgnoreCase("хліб")
                        || answer.equalsIgnoreCase("bread")) {
                    out.println("Перевірка пройдена !");
                    timeOnScreen();
                } else {
                    out.println("Відповідь невірна ! відключаюсь");
                    conectUser.close();
                }
            } else {
                timeOnScreen();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}