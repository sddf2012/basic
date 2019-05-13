package sort;

import java.util.Arrays;

/**
 * @author liu peng bo
 * @date 2019/3/15
 */
public class SortEx {
    static int[] data = {9, 8, 6, 12, 0, 4, 14, 7, 1, 2, 11, -1, 5, 3, 13};

    /**
     * 冒泡排序
     *
     * @param data
     */
    public static void maoPao(int[] data) {
        int[] newData = Arrays.copyOf(data, data.length);
        int length = newData.length;
        int i, j, a, b;
        for (i = 0; i < length; i++) {
            for (j = 0; j < length - i - 1; j++) {
                a = newData[j];
                b = newData[j + 1];
                if (a > b) {
                    newData[j] = b;
                    newData[j + 1] = a;
                }
            }
        }
        System.out.println("冒泡排序:" + Arrays.toString(newData));
    }

    /**
     * 插入排序
     *
     * @param data
     */
    public static void insert(int[] data) {
        int[] newData = Arrays.copyOf(data, data.length);
        int length = newData.length;
        int i, j, a, b;
        for (i = 1; i < length; i++) {
            j = i - 1;
            a = newData[i];
            while (j >= 0 && newData[j] > a) {
                newData[j + 1] = newData[j];
                j--;
            }
            newData[++j] = a;
        }
        System.out.println("插入排序:" + Arrays.toString(newData));
    }

    public static void kuaipai(int[] data){
        kuaipai(data,0,data.length-1);
        System.out.println("快速排序:" + Arrays.toString(data));
    }

    private static void kuaipai(int[] data,int head,int tail){
        int a=head,b=tail;
        if(data==null||data.length<=1||a>=b){
            return;
        }
        int pivot=data[(a+b)>>1];
        while (a<b){
            while (data[a]<pivot){
                a++;
            }
            while (data[b]>pivot){
                b--;
            }
            if(a<b){
                int temp=data[a];
                data[a]=data[b];
                data[b]=temp;
                a++;
                b--;
            }
        }
        kuaipai(data,head,a-1);
        kuaipai(data,b+1,tail);
    }

    public static void xuanze(int[] data){
        int min;
        int index;
        int temp;
        for (int i = 0; i < data.length; i++) {
             min=data[i];
             index=i;
            for (int j = i+1; j < data.length; j++) {
                if(min>data[j]){
                    min=data[j];
                    index=j;
                }
            }
            if(i!=index){
                temp=data[i];
                data[i]=data[index];
                data[index]=temp;
            }
        }
        System.out.println("选择排序:" + Arrays.toString(data));

    }

    public static void main(String[] args) {
        maoPao(data);
        insert(data);
        kuaipai(data);
        xuanze(data);
    }
}
