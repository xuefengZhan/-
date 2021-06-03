package com.mj;

import com.mj.sort.Sort;
import com.mj.sort.cmp.BubbleSort1;
import com.mj.tools.Asserts;
import com.mj.tools.Integers;

import java.util.Arrays;

public class Main {
    // 在 main 函数里写测试代码
    public static void main(String[] args) {
        // 产生 20000 个数据,每个数据的范围是 1~10000
        Integer[] array = Integers.random(20000, 1, 10000);
        // 在这里面写要测试的代码
        testSorts(array, new BubbleSort1()		// 冒泡排序
        );
    }

    // 下面这个复制就可以
    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);
        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }

}