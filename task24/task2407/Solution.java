package com.javarush.task.task24.task2407;

import java.util.List;

/* 
Реализация интерфейса используя локальный класс
*/

/**
 * Это - механизм адаптирования к другому интерфейсу - Sayable
 * Внутри метода toSayable создайте class CatPet, реализующий интерфейс Sayable
 * Логика метода say:
 * Если i < 1, то вывести на экран, что кот спит. Пример, "Васька спит."
 * Иначе вывести фразу: "имя_кота говорит мяу!". Пример для i=3, "Васька говорит мяяяу!"
 * <p/>
 * <b>Пример вывода:</b>
 * Мурзик спит.
 * Васька говорит мяяу!
 * Кошка говорит мяяяяяу!
 * Мышь пищит.
 * Томас говорит мяу!
 * <p/>
 *
 * @param " количество букв 'я' в слове мяу
 * @return экземпляр класса CatPet
 */

public class Solution {
    public static void main(String[] args) {
        List<Pet> pet = Util.getPets();
        List<Sayable> pets = Util.convertPetToSayable(pet);
        Util.printDialog(pets);
    }
}
