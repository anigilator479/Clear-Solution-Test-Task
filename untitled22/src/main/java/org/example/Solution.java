package org.example;

import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Arrays.stream(nums1)
                .filter(num -> Arrays.stream(nums2).noneMatch());
    }
}