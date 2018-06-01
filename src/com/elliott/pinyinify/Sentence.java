package com.elliott.pinyinify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sentence {

    private final String sent;
    List<String> words;

    public Sentence(String sent) {
        this.sent = sent;
        this.words = new ArrayList<>(Arrays.asList(sent.split(" ")));
    }

    public String returnLetter(String toSearch) {
        return "";
    }



}
