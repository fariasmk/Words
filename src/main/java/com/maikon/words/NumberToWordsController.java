package com.maikon.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberToWordsController {

    private final NumberToWordsService numberToWordsService;

    @Autowired
    public NumberToWordsController(NumberToWordsService numberToWordsService) {
        this.numberToWordsService = numberToWordsService;
    }

    @GetMapping("/api/number-to-words/{number}")
    public ResponseEntity<String> convertNumberToWords(@PathVariable int number) {
        String words = numberToWordsService.convertNumberToWords(number);
        return new ResponseEntity<>(words, HttpStatus.OK);
    }
}
