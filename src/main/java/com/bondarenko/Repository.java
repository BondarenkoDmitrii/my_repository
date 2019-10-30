package com.bondarenko;

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

    public void output(){
        for (int i = 0; i< size; i++){
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
}
