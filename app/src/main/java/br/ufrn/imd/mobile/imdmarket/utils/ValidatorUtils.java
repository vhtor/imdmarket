package br.ufrn.imd.mobile.imdmarket.utils;

public class ValidatorUtils {
    public static boolean anyEmpty(String... inputs) {
        for (String input : inputs) {
            if (input.isEmpty()) {
                return true;
            }
        }

        return false;
    }
}
