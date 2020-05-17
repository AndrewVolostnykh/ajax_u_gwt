package own.qualifying.utils;

public class QuickSort {
    private Integer[] array;

    public QuickSort(Integer[] array){
        this.array = array;
    }

    public Integer[] getResultArray(){
        int startIndex = 0, endIndex = array.length - 1;
        functionOfSorting(startIndex, endIndex, array);
        return array;
    }

    public Integer[] getCurrentArray(){
        return array;
    }

    private void functionOfSorting(int startIndex, int endIndex , Integer[] array)
    {
        if (startIndex >= endIndex)
            return;
        int i = startIndex, j = endIndex;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i] <= array[cur])) {
                i++;
            }
            while (j > cur && (array[cur] <= array[j])) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }

        functionOfSorting(startIndex, cur, array);
        functionOfSorting(cur + 1, endIndex, array);
    }
}
