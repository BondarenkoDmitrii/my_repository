package com.bondarenko;

import java.util.Comparator;

public class QuickSort<T> implements ISorter {
    @Override
    public void sort(Object[] array, int low, int high, Comparator comparator) {
        if (array.length == 0)
            return;//завершить выполнение если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middleIndex = low + (high - low) / 2;
        Object middle = array[middleIndex];
        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (comparator.compare(array[i], middle) < 0) {
                i++;
            }

            while (comparator.compare(array[j], middle) > 0) {
                j--;
            }

            if (i <= j) {//меняем местами
                Object temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            sort(array, low, j, comparator);

        if (high > i)
            sort(array, i, high, comparator);
    }
}
