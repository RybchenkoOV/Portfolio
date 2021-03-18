#Генератор рандомных дат календаря
import random
from random import randint
day_31 = ['01', '03', '05', '07', '08', '10', '12']
day_30 = ['04', '06', '09', '11']
day_28 = ['02']
print("Генерируем случайную дату с 2000 по 2021 г.")
year = randint(2000,2021)
month = random.choice(day_31+day_28+day_30)
if month in day_31:
    day = randint(0,31)
elif month in day_30:
    day = randint(0,30)
elif month in day_28 and year % 4 == 0: # учитываем високосный год
    day = randint(29)
else:
    day = randint(28)
print("Случайная дата:", day, ".", month, ".", year)