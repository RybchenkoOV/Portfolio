package L7_L8_ProjectWeather;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserIntefaceView {

    Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите название города");
            String city = scanner.nextLine();

            System.out.println("Введите 1 для получения текущей погоды, " +
                    "введите 2 для получения прогноза погоды на 5 дней, " +
                    "введите 3 для выгрузки из БД, " +
                    "для выхода введите \"выход\"");
//TODO: поддержать 3 пункт меню (вывод из базы) в интерфейсе // --------------
            String command = scanner.nextLine();

//            if (checkUserInput(command) == false) {             // Не получилось реализовать код. Чувствую, что был очень близко (снизу реализация метода)
//                System.out.println("Неверный ввод, попробуйте еще раз!");
//                continue;
//            }

            try {
                controller.getWeather(command, city);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                continue;
            }
        }

    }

//TODO: задание со звездочкой - обработать ввод пользователя (выбросить эксепш и прервать выполнение, либо предложить // Не получилось. Хотя я чувствую, что на верном пути
// пользователю попробовать снова)

//    public boolean checkUserInput(String userInput) {
//        Functionality[] enumValues = Functionality.values();
//        for (Functionality counter : enumValues)
//            if (counter.equals(Functionality.valueOf(userInput))) {
//                return true;
//            }
//
//        return false;
//    }
}
