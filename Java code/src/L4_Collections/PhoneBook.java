package L4_Collections;

import java.util.*;

public class PhoneBook {
    HashMap<Integer, String> phonebook = new HashMap<>();

    public void add(Integer phone, String name) { // Ключом делаем телефонные номера, т.к. они уникальны, иначе HashMap перезапишет одинаковые фамилии (если они будут ключом)
        phonebook.put(phone, name);
    }

    public List get(String name) {
        List valueList = new ArrayList(); // Создаем ArrayList
        for (Map.Entry<Integer, String> i : phonebook.entrySet()) { // Бежим по списку Map.Entry внутри HashMap

            if (name == i.getValue()) { // проверяем, если фамилия совпадает со значением, взятым из корзины по i элементу
                valueList.add(i.getKey()); // то записываем в ArrayList ключ i элемента
            }
        }
        return valueList;
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        PhoneBook phoneBook = new PhoneBook();
        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList("Svetlakov", "Svetlakov", "Kirkorov", "Stalin"));
        for (int i = 0; i < stringArrayList.size(); i++) {
            phoneBook.add((rnd.nextInt(1000000) + 100000), stringArrayList.get(i)); // создадим рандомные номера телефонов с помощью метода add
        }
        System.out.println("Телефонная книга: " + phoneBook.phonebook);
        System.out.println(phoneBook.get("Svetlakov")); // Вытащим по  дублирующейся фамилии номера телефонов через get


    }


}



