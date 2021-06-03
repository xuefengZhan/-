package com.mj.sort.cmp;

import com.mj.sort.Sort;

public class BubbleSort1<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int i = 0; i < array.length; i++) {
            for(int j = 0;j<array.length - i - 1;j++){
                if(cmp(j,j+1) >0){
                    swap(j,j+1);
                }
            }
        }
    }
}
