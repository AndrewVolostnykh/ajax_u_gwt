package own.qualifying;

import own.qualifying.utils.QuickSort;

import java.util.Random;

public class MainTest {
    public static void main(String[] args) {
        final int number = 12;
        Random random = new Random();
        Integer[] array = new Integer[number];

        for(int i = 0; i < number; i++){
            array[i] = random.nextInt(1000);
            System.out.println(array[i]);
        }
        System.out.println("=================");
        System.out.println("======reuslt=====");
        System.out.println("=================");
        QuickSort sort = new QuickSort(array);
        array = sort.getResultArray();

        for(int temp : array){
            System.out.println(temp);
        }
    }
}
