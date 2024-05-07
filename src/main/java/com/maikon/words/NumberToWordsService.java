package com.maikon.words;

import org.springframework.stereotype.Service;

@Service
public class NumberToWordsService {

    private static final String[] units = {
            "", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez",
            "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"
    };

    private static final String[] tens = {
            "", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"
    };

    private static final String[] hundreds = {
            "", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"
    };

    public String convertNumberToWords(int number) {
        if (number == 0) {
            return "zero";
        }
        if (number < 0) {
            return "menos " + convertNumberToWords(-number);
        }

        String words = "";
        int count = 0;
        while (number > 0) {
            if (number % 1000 != 0) {
                words = convertLessThanOneThousand(number % 1000) + getScaleWord(count) + " " + words;
            }
            number /= 1000;
            count++;
        }
        return words.trim();
    }

    private String convertLessThanOneThousand(int number) {
        if (number == 100) {
            return "cem";
        } else if (number >= 100) {
            return hundreds[number / 100] + (number % 100 != 0 ? " e " + convertLessThanOneHundred(number % 100) : "");
        } else {
            return convertLessThanOneHundred(number);
        }
    }

    private String convertLessThanOneHundred(int number) {
        if (number < 20) {
            return units[number];
        } else {
            return tens[number / 10] + (number % 10 != 0 ? " e " + units[number % 10] : "");
        }
    }

    private String getScaleWord(int count) {
        return switch (count) {
            case 1 -> " mil";
            case 2 -> " milhão";
            case 3 -> " bilhão";
            case 4 -> " trilhão";
            default -> "";
        };
    }
}




