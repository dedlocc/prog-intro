# Тесты к курсу «Введение в программирование»

[Условия домашних заданий](http://www.kgeorgiy.info/courses/prog-intro/homeworks.html)

## Домашнее задание 6. Подсчет слов++

Исходный код тестов:

* [WordStatIndexTest.java](java/wordStat/WordStatIndexTest.java)
* [WordStatIndexChecker.java](java/wordStat/WordStatIndexChecker.java)

Откомпилированные тесты: [WordStatIndexTest.jar](artifacts/wordStat/WordStatIndexTest.jar)


## Домашнее задание 5. Свой сканнер

Исходный код тестов:

* [FastReverseTest.java](java/reverse/FastReverseTest.java)

Откомпилированные тесты: [FastReverseTest.jar](artifacts/reverse/FastReverseTest.jar)
Модификации
 * *Hex* (32-33)
    * Во вводе и выводе используются числа в шестнадцатеричной системе счисления
    * Класс должен иметь имя `ReverseHex`
    * [Исходный код тестов](java/reverse/FastReversHexTest.java)
    * [Откомпилированные тесты](artifacts/reverse/FastReversHexTest.jar)
 * *Abc* (34-35)
    * Во вводе и выводе используются числа, записаные буквами:
      нулю соответствует буква `a`, единице – `b` и так далее
    * [Исходный код тестов](java/reverse/FastReversAbcTest.java)
    * [Откомпилированные тесты](artifacts/reverse/FastReversAbcTest.jar)


## Домашнее задание 4. Подсчет слов

Исходный код тестов:

* [WordStatInputTest.java](java/wordStat/WordStatInputTest.java)
* [WordStatChecker.java](java/wordStat/WordStatChecker.java)

Откомпилированные тесты: [WordStatInputTest.jar](artifacts/wordStat/WordStatInputTest.jar)

Модификации
 * *InputPrefix*
    * Выходной файл должен содержать все различные префиксы длины 3
      слов встречающихся во входном файле, в порядке их появления.
      Слова длины меньшей 3 игнорируются.
    * Класс должен иметь имя `WordStatInputPrefix`
    * [Исходный код тестов](java/wordStat/WordStatInputPrefixTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatInputPrefixTest.jar)
 * *InputShingles*
    * Выходной файл должен содержать все различные подстроки длины 3
      слов встречающихся во входном файле, в порядке их появления.
      Слова длины меньшей 3 игнорируются.
    * Класс должен иметь имя `WordStatInputShingles`
    * [Исходный код тестов](java/wordStat/WordStatInputShinglesTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatInputShinglesTest.jar)
 * *WordsPrefix* (для 36, 37)
    * Выходной файл должен содержать все различные префиксы длины 3
      слов встречающихся во входном файле, в лексикографическом порядке.
      Слова длины меньшей 3 игнорируются.
    * Класс должен иметь имя `WordStatWordsPrefix`
    * [Исходный код тестов](java/wordStat/WordStatWordsPrefixTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatWordsPrefixTest.jar)
 * *CountShingles* (для 38, 39)
    * Выходной файл должен содержать все различные подстроки длины 3
      слов встречающихся во входном файле, упорядоченые по возрастанию числа
      вхождений, а при равном числе вхождений – по порядку первого вхождения
      во входном файле.
    * Класс должен иметь имя `WordStatCountShingles`
    * [Исходный код тестов](java/wordStat/WordStatCountShinglesTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatCountShinglesTest.jar)


## Домашнее задание 3. Реверс

Исходный код тестов:

* [ReverseTest.java](java/reverse/ReverseTest.java)
* [ReverseChecker.java](java/reverse/ReverseChecker.java)

Откомпилированные тесты: [ReverseTest.jar](artifacts/reverse/ReverseTest.jar)

Модификации:
 * *Max* (для 32, 33)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите максимум из чисел в его столбце и строке
    * Класс должен иметь имя `ReverseMax`
    * [Исходный код тестов](java/reverse/ReverseMaxTest.java)
    * [Откомпилированные тесты](artifacts/reverse/ReverseMaxTest.jar)
 * *Min* (для 34, 35)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите минимум из чисел в его столбце и строке
    * Класс должен иметь имя `ReverseMin`
    * [Исходный код тестов](java/reverse/ReverseMinTest.java)
    * [Откомпилированные тесты](artifacts/reverse/ReverseMinTest.jar)
 * *Avg* (для 36-39)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите среднее из чисел в его столбце и строке
    * Класс должен иметь имя `ReverseAvg`
    * [Исходный код тестов](java/reverse/ReverseAvgTest.java)
    * [Откомпилированные тесты](artifacts/reverse/ReverseAvgTest.jar)


## Домашнее задание 2. Сумма чисел

Для того, чтобы протестировать исходную программу:

 1. Скачайте откомпилированные тесты ([SumTest.jar](artifacts/sum/SumTest.jar))
 1. Откомпилируйте `Sum.java`
 1. Проверьте, что создался `Sum.class`
 1. В каталоге, в котором находится `Sum.class` выполните команду
    ```
       java -jar <путь к SumTest.jar>
    ```
    * Например, если `SumTest.jar` находится в текущем каталоге, выполните команду
    ```
        java -jar SumTest.jar
    ```

Исходный код тестов:

* [SumTest.java](java/sum/SumTest.java)
* [SumChecker.java](java/sum/SumChecker.java)
* [Базовые классы](java/base/)

Модификации:
 * *Long* (для 32, 33)
    * Входные данные являются 64-битными целыми числами
    * Класс должен иметь имя `SumLong`
    * [Исходный код тестов](java/sum/SumLongTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumLongTest.jar)
 * *Float* (для 34, 35)
    * Входные данные являются 32-битными числами с формате с плавающей точкой
    * Класс должен иметь имя `SumFloat`
    * [Исходный код тестов](java/sum/SumFloatTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumFloatTest.jar)
 * *LongSpace* (для 36, 37)
    * Входные данные являются 64-битными целыми числами
    * Класс должен иметь имя `SumLongSpace`
    * Числа разделяются [пробелами-разделителями](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Character.html#SPACE_SEPARATOR)
    * [Исходный код тестов](java/sum/SumLongSpaceTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumLongSpaceTest.jar)
 * *BigIntegerSpace* (для 38, 39)
    * Входные данные помещаются в тип [BigInteger](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/math/BigInteger.html)
    * Класс должен иметь имя `SumBigIntegerSpace`
    * Числа разделяются [пробелами-разделителями](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Character.html#SPACE_SEPARATOR)
    * [Исходный код тестов](java/sum/SumBigIntegerSpaceTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumBigIntegerSpaceTest.jar)



## Домашнее задание 1. Запусти меня!

 1. Скачайте исходный код ([RunMe.java](java/RunMe.java))
 1. Откомпилируйте код (должен получиться `RunMe.class`)
 1. Запустите класс `RunMe` с выданными вам аргументами командной строки
 1. Следуйте выведенной инструкции
