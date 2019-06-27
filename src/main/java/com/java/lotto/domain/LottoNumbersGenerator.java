package com.java.lotto.domain;

import com.java.lotto.exception.OutOfLottoNumberRangeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbersGenerator {
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    public static List<Integer> automaticNumbersGenerator() {
        List<Integer> lottoNumberRange = new ArrayList<>();
        for (int i = LOTTO_MIN_NUMBER; i <= LOTTO_MAX_NUMBER; i++) {
            lottoNumberRange.add(i);
        }
        return shuffle(lottoNumberRange);
    }

    public static List<Integer> manualNumbersGenerator(String manualNumber) {
        String[] numbers = manualNumber.split(",");

        List<Integer> lottoNumbers = new ArrayList<>();
        for (String number : numbers) {
            lottoNumbers.add(LottoNumberRangeValidation(numberValidation(number.trim())));
        }
        return lottoNumbers;
    }

    private static List<Integer> shuffle(List<Integer> LottoNumberRange) {
        Collections.shuffle(LottoNumberRange);
        return sort(LottoNumberRange.subList(0, 6));
    }

    private static List<Integer> sort(List<Integer> lottoNumbers) {
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    private static int numberValidation(String number) {
        if (isNumber(number)) {
            return Integer.parseInt(number);
        }
        throw new IllegalArgumentException();

    }

    private static boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int LottoNumberRangeValidation(int number) {
        if (LOTTO_MAX_NUMBER >= number && number >= LOTTO_MIN_NUMBER) {
            return number;
        }
        throw new OutOfLottoNumberRangeException();
    }
}
