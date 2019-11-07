package com.bondarenko;

import java.util.ArrayList;
import java.util.Comparator;

public class Repository {

    private Person [] array = new Person[10];
    private int size = 0;

    public void add(Person person){

        array[size] = person;
        size++;

        if (size == array.length){
            resize();
        }
    }

    public void delete(int pos){
        for (int i = pos; i < size+1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    public void outputArray(Person [] array){
        array = arrayOverwrite(array);
        int size = array.length;
        for (int i = 0; i < size; i++){
            System.out.println(i + " " + array[i].toString());
        }
    }

    private void resize() {
        Person[] tempArray = new Person[array.length + 5];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
    }

    private Person[] arrayOverwrite(Person [] array){
        int index = 0;
        for (int i=0; i < array.length; i++){
            if (array[i] != null){
                index++;
            }
            else {
                i = array.length;
            }
        }
        Person [] arraySize = new Person[index];
        for (int i=0; i < index; i++){
            arraySize[i] = array[i];
        }
        return arraySize;
    }

    private void quickSort(Person [] array, int low, int high, Comparator<Person> comparator){
        if (array.length == 0)
            return;//завершить выполнение если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middleIndex = low + (high - low) / 2;
        Person middle = array[middleIndex];
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
                Person temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j, comparator);

        if (high > i)
            quickSort(array, i, high, comparator);
    }

    public Person[] sorting(Person [] array, Comparator<Person> comparator){
        Person [] tempArray = arrayOverwrite(array);
        int low = 0;
        int high = tempArray.length - 1;
        quickSort(tempArray, low, high, comparator);
        return tempArray;
    }

    public ArrayList<Person> binarySearch(Person [] array, Person elementToSearch, Comparator<Person> comparator) {

        ArrayList<Person> arrayList = new ArrayList<>();
        int low = 0;
        int high = array.length - 1;

        // условие прекращения
        while(low <= high) {
            int middleIndex = (low + high) / 2;
            // если средний элемент - целевой элемент, вернуть его индекс
            if (comparator.compare(array[middleIndex], elementToSearch) == 0) {
                arrayList.add(array[middleIndex]);
                int tempMiddle1 = middleIndex+1;
                int tempMiddle2 = middleIndex-1;
                if (tempMiddle1 < array.length) {
                    while (comparator.compare(array[tempMiddle1], elementToSearch) == 0) {
                        arrayList.add(array[tempMiddle1]);
                        if (tempMiddle1 == array.length - 1) {
                            return arrayList;
                        } else {
                            tempMiddle1++;
                        }
                    }
                }
                if (tempMiddle2 >= 0) {
                    while (comparator.compare(array[tempMiddle2], elementToSearch) == 0) {
                        arrayList.add(0, array[tempMiddle2]);
                        if (tempMiddle2 == 0) {
                            return arrayList;
                        } else {
                            tempMiddle2--;
                        }
                    }
                }
                return arrayList;
            }

            // если средний элемент меньше
            // направляем индекс в middle+1, убирая первую часть из рассмотрения
            else if (comparator.compare(array[middleIndex], elementToSearch) < 0)
                low = middleIndex + 1;

                // если средний элемент больше
                // направляем индекс в middle-1, убирая вторую часть из рассмотрения
            else if (comparator.compare(array[middleIndex], elementToSearch) > 0)
                high = middleIndex - 1;

        }
        return arrayList;
    }

    public Person[] getArray() {
        return array;
    }

    public void setArray(Person[] array) {
        this.array = array;
    }
}
