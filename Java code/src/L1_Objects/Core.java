package L1_Objects;

import L1_Objects.Obstacles.Obstacles;
import L1_Objects.Obstacles.Track;
import L1_Objects.Obstacles.Wall;
import L1_Objects.Participants.Cat;
import L1_Objects.Participants.Human;
import L1_Objects.Participants.Party;
import L1_Objects.Participants.Robot;

import java.util.ArrayList;
import java.util.Random;

public class Core {

    public static void main(String[] args) {
        Random RND = new Random();
        int var = RND.nextInt(2)+1; // Используем рэндом, чтобы сделать параметры персонажей каждый раз разными
        final int baseJump = 5; // Зададим базовые значения прыжка для всех
        final int baseRun = 50; // И бега тоже

        /** Обычного массива тут недостаточно, т.к. удалить из него элемент в последствии будет крайне тяжело, поэтому используем ArrayList */
        ArrayList<Party> participants = new ArrayList<>();
        Cat cat = new Cat("Кот Том",baseJump*var,(int)(baseRun*var*1.5));
        Human human = new Human("Дворник Василий",(int)(baseJump*var*1.5),baseRun*var);
        Robot robot = new Robot("Андроид Игн@т", (int)(baseJump*var*1.2),(int)(baseRun*var*1.2));
        /** Добавим участников */
        participants.add((cat));
        participants.add((human));
        participants.add((robot));
        /** Для удобства выведем сгенерированные способности */
        System.out.println("Способности участников:");
        for (int i = 0; i < participants.size(); i++) {
            System.out.println(participants.get(i).whoIs()+ " может бегать на " + participants.get(i).run() + "m. и прыгать на " + participants.get(i).jump() + "m.");
        }
        /** Создадим препятствия */
        Obstacles[] obstacles = {
                new Wall((int)(baseJump*var*1.1)),
                new Track((int)(baseRun*var*1.1)),
                new Wall((int)(baseJump*var*1.2)),
                new Track((int)(baseRun*var*1.2)),
                new Wall((int)(baseJump*var*1.3)),
                new Track((int)(baseRun*var*1.3))};
        /** Теперь выведем список препятствий */
        System.out.println();
        System.out.println("Препятствия:");
        for (int i = 0; i < obstacles.length; i++) {
            System.out.println((i+1) + ". Высота:" + obstacles[i].getHeight() + ". Длина:" + obstacles[i].getLength());
        }
        /** Начнем соревнования*/
        System.out.println("--------------------------------");
        for (int j = 0; j < obstacles.length; j++) {
            for (int i = 0; i < participants.size(); i++) {
                System.out.println("Препятствие № " + (j+1));
                if (obstacles[j].checkJump(participants.get(i))) {
                    System.out.println(participants.get(i).whoIs() + " успешно преодолел!");
                } else {
                    System.out.println(participants.get(i).whoIs() + " не осилил! И оформил отбытие!");
                    participants.remove(participants.get(i));
                }
            }
        }
    }
}
