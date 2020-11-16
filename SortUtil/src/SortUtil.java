import org.jcp.xml.dsig.internal.dom.DOMUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class SortUtil {
    private static  LinkedList<String> bestPath ;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();
            int len = sc.nextInt();
            String result = str;
            int mostCG = 0;
            if (len > 0 && str.length() > 0) {
                if (len >= str.length()) {
                    System.out.println(str);
                } else {
                    int cgCount = 0;
                    int[] arr = new int[str.length() - len +1];

                    for (int i = 0; i < len; i++) {
                        if (str.charAt(i) == 'C' || 'G' == str.charAt(i)) {
                            cgCount++;
                        }
                    }
                    result = str.substring(0, len);
                    arr[0] = cgCount;
                    mostCG = cgCount;
                    for (int i = 1; i < str.length() - len + 1; i++) {
                        if (str.charAt(i - 1) == 'C' || str.charAt(i - 1) == 'G') {
                            cgCount--;
                        }
                        if (str.charAt(i + len - 1) == 'C' || 'G' == str.charAt(i + len - 1)) {
                            cgCount++;
                        }
                        arr[i] = cgCount;
                        if (cgCount > mostCG) {
                            mostCG = cgCount;
                            result = str.substring(i, i + len);
                        }
                    }
                }
                if (len > 0)
                    System.out.println(result);
            }
        }
    }
    public static double getBestScore(double[] score, int index, int[] visited, double[][] scores, LinkedList<String> path){
        if(index == visited.length) {
            if (score[1] < score[0]) {
                bestPath = new LinkedList<>(path);
            }
                return score[0];

        }
        for(int i = 0; i < visited.length; i++){
            if(visited[i]==0){
                visited[i] = 1;
                score[0] +=scores[index][i];
                path.add((index + 1) + " " + (i+1));
                double total = getBestScore(score, index+1, visited, scores, path);
                path.remove(index);
                score[1] = score[1] < total ? total : score[1];
                score[0]-=scores[index][i];
                visited[i] = 0;
            }
        }

        return score[0];
    }
    public static boolean canMatch(int i, int j, String str1, String str2){
        if(i == str1.length() && j == str2.length()){
            return true;
        }else if((i == str1.length()  && j < str2.length() ) || (j == str2.length() && i < str1.length())){
            return false;
        }
        if(str1.charAt(i)=='*' || str2.charAt(j) =='*'){
                return canMatch(i + 1, j+ 1, str1, str2) || canMatch(i+1, j, str1, str2) || canMatch(i, j +1, str1, str2);
        }else if(str1.charAt(i)=='?' || str2.charAt(j) == '?'){
            return canMatch(i + 1, j + 1, str1, str2);
        }else if(str1.charAt(i) == str2.charAt(j)){
            return canMatch(i+1, j +1, str1, str2);
        }
        return false;
    }
    public static String getResult(LinkedList list, int index){
        String result = "fasle";
        if(list.get(index-1).equals("true") && list.get(index+1).equals("true")){
            result="true";
        }
        return result;
    }
    //public static void main(String[] args) {

//        String str = "hell world";
//        String substring = str.substring(1, str.length() - 1);
//        Scanner sc = new Scanner(System.in);
//        int[] arr =new int[2];
//
//        LinkedList<Integer> list = new LinkedList<>();
//        Iterator<Integer> iterator = list.iterator();
//        while(iterator.hasNext()){
//            System.out.print(iterator.next());
//        }
//        String inputStr = sc.nextLine();
//        String[] splitInput = inputStr.split(",");
//
//        int[] inputInt = new int[splitInput.length];
//        for(int i = 0; i < splitInput.length; i++){
//            inputInt[i] = Integer.parseInt(splitInput[i]);
//        }
//
//       // HeapSort(inputInt);
//        //MergeSort(inputInt, 0, inputInt.length-1);
//        QuickSort(inputInt, 0, inputInt.length-1);
//        for(int i:inputInt){
//            System.out.print(" " + i);
//        }
 //   }
    private static void QuickSort(int[] arr, int low, int high){
        int i = low + 1, j=high;
        int standard = arr[low];

        while(0<=i && i <=j && j < arr.length){
            while(i <= j && arr[i] < standard){
                arr[i-1] = arr[i];
                i++;
            }
            while(j >=i && arr[j] >=standard){
                j--;
            }
            if(i >= 0 && i < arr.length && j >=0 && j <= arr.length) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[i-1] = standard;

        if(i-2 > low) {
            QuickSort(arr, low, i - 2);
        }
        if(high > i)
        QuickSort(arr, i , high);

    }

    /**
     * 归并排序
     * @param arr
     * @param low
     * @param high
     */
    private static void MergeSort(int[] arr, int low, int high){
        int middle = (low + high) / 2 ;
        if (low < high){
            MergeSort(arr,low, middle);
            MergeSort(arr, middle + 1, high);
            Merge(arr, middle, low, high);
        }
    }
    private static void Merge(int[] arr, int middle, int low, int high){
        int[] temp = new int[high - low + 1];
        int i = low, j = middle+1;
        int index = 0;
        while(i <= middle && j <= high){
            if(arr[i] > arr[j]){
                temp[index++] = arr[j];
                j++;
            }else{
                temp[index] = arr[i];
                index++;
                i++;
            }
        }

        while(i <= middle){
            temp[index] = arr[i];
            index++;
            i++;
        }
        while(j <= high){
            temp[index] = arr[j];
            index++; j++;
        }

        for(i = 0; i < temp.length; i++){
            arr[low++] = temp[i];
        }
        temp=null;
    }

    /**
     * 堆排序：建造大顶堆
     * @param arr
     */
    private static void  HeapSort(int[] arr){
        int len = arr.length;
        for (int  nodeIndex = len /2 -1 ; nodeIndex >= 0; nodeIndex--){
            adjustHeap(arr, nodeIndex , len);
        }
        for(int nodeIndex = len-1 ; nodeIndex >= 0; nodeIndex--){
            swap(arr, 0, nodeIndex  );
            adjustHeap(arr, 0, nodeIndex );
        }
        System.out.println("end");
    }
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static void adjustHeap(int[] arr, int nodeIndex, int len){
        while (nodeIndex <= (len/ 2 -1)){
            int leftChild = arr[nodeIndex * 2 +1];
            if(nodeIndex * 2 + 2 < len && arr[nodeIndex *2 +2] > leftChild && arr[nodeIndex] < arr[nodeIndex * 2 + 2]){
                swap(arr, nodeIndex*2+2, nodeIndex);
                nodeIndex = nodeIndex * 2+2;
            }else  if(arr[nodeIndex] < leftChild){
                swap(arr, nodeIndex * 2 +1, nodeIndex);
                nodeIndex = nodeIndex * 2+1;
            }else {
                break;
            }
        }
    }
}
