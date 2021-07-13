package com.cims.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Shingles {

    public static void main(String[] args) {
        System.out.println("Percent : " + getSimilarityPercent("tracelink", "tracelink"));
    }

    public static int getSimilarityPercent(String s1, String s2) {
        int shared = 0;

        List<List<String>> s = new ArrayList<>();
        s.add(getShingles(s1));
        s.add(getShingles(s2));
        System.out.println(s);

        Set<String> shingles = new HashSet<>();
        for (List<String> x : s) {
            for (String shingle : x) {
                if (shingles.contains(shingle)) {
                    ++shared;
                } else {
                    shingles.add(shingle);
                }
            }
        }
        return shared * 100 / shingles.size();
    }

    private static List<String> getShingles(String str) {
        List<String> shingles = new ArrayList<>();

        int i = 0;
        while (i < str.length() - 2) {
            shingles.add(str.substring(i, i + 3));
            ++i;
        }

        return shingles;
    }
}
