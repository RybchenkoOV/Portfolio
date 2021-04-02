package L4_Collections;

import java.util.*;

public class CheckDuplicate {


    public static void main(String[] args) {

        /** Создадим ArrayList из 20 слов с повторениями */
        Random rnd = new Random();
        String[] wordsArray = new String[]{"apple", "rainbow", "Newton", "cat", "road"};
        ArrayList<String> wordsArrayList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            int index = rnd.nextInt(wordsArray.length);
            wordsArrayList.add(wordsArray[index]);
        }
        System.out.println("Массив из 20 слов с повторами: " + wordsArrayList);

        /** Передадим данные в Set, чтобы не было повторов (просто для наглядности и демонстрации работы с Set)*/
        HashSet<String> wordsHashSet = new HashSet<>(wordsArrayList);
        System.out.println("Массив из 20 слов без повторов: " + wordsHashSet);

        /** Создадим HashMap, чтобы получить результаты */
        HashMap<String, Integer> wordsHashMap = new HashMap<>();
        for (int i = 0; i < wordsArrayList.size(); i++) { // бежим по списку слов
            int indexCounter = 0; // вводим счетчик
            for (int j = 0; j < wordsArrayList.size(); j++) { // для каждого элемента 1ого цикла пробегаемся по каждому элементу по 2ом цикле
                if (wordsArrayList.get(j) == wordsArrayList.get(i)) { // сравниваем каждое значение 2 цикла со значение 1 цикла
                    indexCounter++; // при совпадении увеличиваем счетчик
                }
            }
            wordsHashMap.put(wordsArrayList.get(i), indexCounter); // выводим пары ключ-значение, где ключ - название элемента, а значение - сумма по счетчику
        }

        System.out.println(wordsHashMap);

    }

}
