package org.example;

public class PrettyArray {
    public static boolean isPretty(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean result = false;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j] + 1 || arr[i] == arr[j] - 1){
                    result = true;
                }
            }
            if (!result) {
                return false;
            }
        }
        return true;
    }
}
