package com.bondarenko;

import java.util.Comparator;

public interface ISorter<T> {
    public void sort(T [] array, int low, int high, Comparator<T> comparator);
}
