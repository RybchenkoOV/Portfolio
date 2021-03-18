import random
print("Загадайте число от 0 до 100 и запишите его на листок")
input("Нажмите Enter чтобы продолжить...")
attempts = 0
answer = None
minimum = 0
maximum = 100
options = {1: "УГАДАНО", 2: "Больше", 3: "Меньше"}

while answer != options[1]:
    guess = random.randint(minimum, maximum)
    print(f"Попытка №: {attempts + 1}")
    print(f"Ваше число, {guess} ?")
    print(options)
    answer = int(input("Ответ: "))
    if answer == 3:
        maximum = guess
        guess = int(random.randint(minimum, guess))
        attempts += 1
    elif answer == 2:
        minimum = guess
        guess = int(random.randint(guess, maximum))
        attempts += 1
    elif answer not in options:
        print("Неправильный ввод. Попробуйте еще раз!")
    else:
        break
print(f"Число угадано!!! Кол-во попыток: {attempts+1}")