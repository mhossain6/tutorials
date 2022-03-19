package com.example.rest.sample;

import java.util.ArrayList;
import java.util.List;

public class HackerrankProductOfArray {

    public static void main(String[] args) {

        Integer[] nums = {1, -2, -3, 4};

        List<Integer> output = printOutput(nums);
        output.forEach(op -> {
            System.out.print(op);
        });
    }

    private static List<Integer> printOutput(Integer[] nums) {
        List<Integer> op = new ArrayList<>();
        Integer numZeros[] = new Integer[1];
        numZeros[0] = 0;
        Integer prod = getProduct(nums, numZeros);

        System.out.println(numZeros);
        // print all zeros
        if (numZeros[0] >= 1) {
            if (numZeros[0] > 1) return getAllzeroArray(nums.length) ;
            else {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == 0)
                        op.add(prod);
                    else {
                        op.add(0);
                    }
                }
            }
        } else {
            for (int i = 0; i < nums.length; i++) {
                op.add(prod / nums[i]);
            }
        }

        return op;
    }

    private static List<Integer> getAllzeroArray(int length) {
        ArrayList<Integer> ret = new ArrayList<>();
        for(int i =0 ;i< length;i++) {
            ret.add(  Integer.valueOf(0));
        }
        return  ret;
    }

    private static Integer getProduct(Integer[] nums, Integer numZeros[]){

        Integer prod = 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                numZeros[0]++;
                continue;
            }
            prod *= nums[i];
        }
        return prod;
    }
}
