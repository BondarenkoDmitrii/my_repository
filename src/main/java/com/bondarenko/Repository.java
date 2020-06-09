package com.bondarenko;

import com.bondarenko.xml.JaxbWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.lab.repository.IRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Repository<T> implements IRepository<T>{

    private static final Logger logger = LoggerFactory.getLogger(
            JaxbWorker.class);

    private T [] array = (T []) new Object[10];
    private int size = 0;

    @Injector()
    ISorter sorter;

    /**
     * This method adds a person to the repository
     * @param person class T instance
     */
    @Override
    public void add(T person){

        array[size] = person;
        size++;

        if (size == array.length){
            resize();
        }
        logger.info("Person added");
    }

    @Override
    public void add(int index, T person){
        for (int i =size; i >= index; i--){
            array[i+1] = array[i];
        }
        array[index] = person;
        size++;

        if (size == array.length){
            resize();
        }
        logger.info("Person added in position " + index);
    }

    @Override
    public T delete(int index){
        T temp = array[index];
        for (int i = index; i < size+1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        logger.info("Person with index " + index + " deleted");
        return temp;
    }

    @Override
    public T set(int index, T person){
        T temp = array[index];
        array[index] = person;
        logger.info("Person with index " + index + " changed");
        return temp;
    }

    @Override
    public T get(int index){
        return array[index];
    }

    @Override
    public List<T> toList(){
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++){
            if (array[i]!=null){
            arrayList.add(array[i]);}
        }
        logger.info("Array is listed");
        return arrayList;
    }

    public void outputArray(T [] array){
        array = arrayOverwrite(array);
        for (int i = 0; i < array.length; i++){
            System.out.println(i + " " + array[i].toString());
        }
    }

    private void resize() {
       T [] tempArray = (T []) new Object[array.length + 5];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
        logger.info("Array increased");
    }

    public T [] arrayOverwrite(T [] array){
        int index = 0;
        for (int i=0; i < array.length; i++){
            if (array[i] != null){
                index++;
            }
            else {
                i = array.length;
            }
        }
        T [] arraySize = (T []) new Object[index];
        for (int i=0; i < index; i++){
            arraySize[i] = array[i];
        }
        return arraySize;
    }

    public int getSize(){
        return size;
    }

    @Override
    public void sortBy(Comparator<T> comparator ){
        T [] tempArray = arrayOverwrite(array);
        int low = 0;
        int high = tempArray.length - 1;

       // sorter = new QuickSort();
        sorter.sort(tempArray, low, high, comparator);

        for (int i=0; i < size; i++){
            array[i] = tempArray[i];
        }
    }

    //(Person p) -> p.getFirstName().equals(element to search))&&

    @Override
    public Repository<T> searchBy(Predicate<T> condition){
        Repository<T> repository = new Repository<>();
        for (T person: arrayOverwrite(array)){
            if (condition.test(person)){
                repository.add(person);
            }
        }
        return repository;
    }

    public T [] getArray() {
        return array;
    }

    public void setArray(T [] array) {
        this.array = array;
    }
}
