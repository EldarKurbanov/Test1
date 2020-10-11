package com.company;

import java.util.Scanner;
//условие задачи: Вывести на экран значение самого часто повторяющегося элемента массива.
// Если в массиве несколько таких элементов, вывести из них тот, который имеет самый меньший номер.

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        final int arrayLength = userInput.nextInt(); // Количество чисел, вводимых пользователем
        final int[] array = new int[arrayLength]; // Числа, вводимые пользователем
        final int[] arrayOfUniqueNumbers = new int[arrayLength]; // Здесь будем хранить уникальные числа массива
        int uniqueElementsQuantity = 0; // Также будем хранить количество таких уникальных чисел

        // На данном этапе мы будем спрашивать у пользователя число и сразу же вычленять оттуда уникальные элементы
        // Проходимся по массиву array пока он не закончится или переменная k по какой-то причине тоже выйдет за свой предел
        // Переменная k выполняет ту же роль, что и переменная i, но k предзначается для массива уникальных чисел
        for (int i = 0, k = 0; i < arrayLength && k < arrayLength; i++) {
            // Спрашиваем у пользователя число, записываем в массив и в отдельную константу для удобства
            final int currentElement = array[i] = userInput.nextInt();
            // Определяем: число, которое сейчас ввёл пользователь уже было введено до этого?
            boolean isCurrentElementUnique = true;
            // Если оно уже встречалось в ранее вводимых пользователем числах, то элемент не уникальный и в массив уникальных мы его не пишем
            for (int j = i - 1; j >= 0; j--) {
                if (currentElement == array[j]) {
                    isCurrentElementUnique = false;
                    break;
                }
            }
            if (isCurrentElementUnique) {
                uniqueElementsQuantity++;
                arrayOfUniqueNumbers[k++] = currentElement;
            }
        }

        // Следующий этап: Создаём два массива размером с количество уникальных чисел в массиве
        // Эти массивы напрямую связаны друг с другом и с массивом уникальных чисел

        // Первый хранит информацию о том, сколько раз это уникальное число повторяется в массиве пользователя
        final int[] quantityOfRepetitionOfUniqueNumbers = new int[uniqueElementsQuantity];
        // Второй массив хранит номер уникального элемента в массиве пользователя (причём самый первый)
        final int[] firstNumberOfUniqueNumbersInArray = new int[uniqueElementsQuantity];

        // Всё эти данные высчитываются здесь
        for (int i = 0; i < uniqueElementsQuantity; i++) {
            boolean firstMeetOfThisUniqueNumber = true;
            for (int j = 0; j < arrayLength; j++) {
                if (arrayOfUniqueNumbers[i] == array[j]) {
                    quantityOfRepetitionOfUniqueNumbers[i]++;
                    if (firstMeetOfThisUniqueNumber) {
                        firstNumberOfUniqueNumbersInArray[i] = j;
                        firstMeetOfThisUniqueNumber = false;
                    }
                }
            }
        }

        // Таким образом мы имеем следующую информацию:
        // 1) Все уникальные элементы массива
        // 2) Сколько раз они встречаются
        // 3) Когда они встречаются в первый раз в массиве
        // Все эти данные связаны между собой тем что лежат в одинаковом порядке

        // Теперь задача сводится к поиску максимума в массиве с количеством повторений
        int max = 0;
        int numberOfMax = -1;
        for (int i = 0; i < uniqueElementsQuantity; i++) {
            if (max < quantityOfRepetitionOfUniqueNumbers[i]) {
                max = quantityOfRepetitionOfUniqueNumbers[i];
                numberOfMax = i;
            }
            // Если количество повторений одинаково, то мы сравниваем номера элементов, когда они встретились
            else if (max == quantityOfRepetitionOfUniqueNumbers[i]) {
                // Если индекс текущего элемента меньше, то максимум становится именно он
                if (firstNumberOfUniqueNumbersInArray[i] < firstNumberOfUniqueNumbersInArray[numberOfMax]) {
                    numberOfMax = i;
                }
            }
        }
        // Печатаем этот элемент из массива. Формула массивПользователя[НомераПервойВстречиЭлементовВМассиве[НомерМаксимальноПовторяющегосяЭлемента]]
        System.out.println(array[firstNumberOfUniqueNumbersInArray[numberOfMax]]);
    }
}

