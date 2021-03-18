# Игра угадай число на деньги.
from random import randint
money = 1000
print("У вас ", money, "$")
print("1. Easy - Ставки по 50$")
print("2. Normal - Ставки по 100$")
print("3. Hard - Ставки по 200$")
while True:
    level = int(input("Выберите уровень сложности:"))
    if level == 1:
        bet = 50
        break
    elif level == 2:
        bet = 100
        break
    elif level == 3:
        bet = 200
        break
    else:
        print("Выберите правильный вариант!")
print("Компьютер загадал случайное число от 0 до 50. Угадайте какое?")
number = randint(0,50)
while True:
    answer = int(input("Ваш ответ:"))
    if answer == number:
        print("Вы победили!!! И у вас осталось еще:", money, "$")
        break
    elif answer > number:
        money -= bet
        print("Число меньше",answer,". У вас осталось:", money)
        if money <= 0:
            print("Вы проиграли все деньги!!!")
            break
    elif answer < number:
        money -= bet
        print("Число больше", answer, ". У вас осталось:", money)
        if money <= 0:
            print("Вы проиграли все деньги!!!")
            break
print("Спасибо за игру!")
