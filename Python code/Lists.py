# Создание рандомных списков
from random import randint
my_list = []
for i in range (50):
    my_list.append(randint(-10,10))
print("Список:", my_list)

# выбор рандомных значений из списка
import random
from random import randint
my_list = []
for i in range (5):
    my_list.append(randint(-10,10))
print("Список:", my_list)
rnd_choice = random.choice(my_list)
print("Выбрано случайное значение из списка:", rnd_choice)

# Подсчет уникальных элементов в списке
from random import randint
my_list = []
result = []
for i in range (50):
    my_list.append(randint(-10,10))
print("Список:", my_list)

for i in my_list[:]:
    if my_list.count(i) == 1:
        result.append(i)
print("Список уникальных значений:", result)

