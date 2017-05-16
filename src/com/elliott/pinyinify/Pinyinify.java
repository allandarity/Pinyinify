package com.elliott.pinyinify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by elliott on 15/05/17.
 */
public class Pinyinify {

    ArrayList<String> sentence = new ArrayList<>();
    String vowel;
    int tone;
    boolean found = false;

    char[][] charTone = {
            {'f'},
            {'a', 'ā', 'á', 'ǎ', 'à'},
            {'e', 'ē', 'é', 'ě', 'è'},
            {'i', 'ī', 'í', 'ǐ', 'ì'},
            {'o', 'ō', 'ó', 'ǒ', 'ò'},
            {'u', 'ū', 'ú', 'ǔ', 'ù'},
            {'u', 'ǖ', 'ǘ', 'ǚ', 'ǜ'},
    };

    public Pinyinify(String input) {
        String[] splitInput = input.split(" ");
        sentence.addAll(Arrays.asList(splitInput));
        for(String s : sentence) {
            System.out.print(addTone(s)+" ");
        }

    }

    String addTone(String word) {
        found = false;
        StringBuilder sb = new StringBuilder();
            String[] wordArray = word.split("");
            for (int i = 0; i < wordArray.length; i++) {
                if(!found) {
                    if ((wordArray[i].equals(vowelTone(word)))) {
                        this.vowel = wordArray[i];
                        this.tone = getTone(word);
                        sb.append(charTone[getVowelPlace(vowel)][tone]);
                        found = true;
                        continue;
                    }
                }
                if (wordArray[i].matches("[0-9]+")) {
                    continue;
                }
                sb.append(wordArray[i]);
            }
        return sb.toString();
    }

    String vowelTone(String input) {
        String[] wordArray = input.split("");
        for(int i=0; i<wordArray.length; i++) {
            if ((wordArray[i].equals("a")) || (wordArray[i].equals("e"))) {
                return wordArray[i].equals("a") ? "a" : "e";
            }
            if ((wordArray[i].equals("o")) || (wordArray[i].equals("u"))) {
                return wordArray[i].equals("o") ? "o" : "u";
            }
            if(i == wordArray.length-1) {
                Pattern pattern = Pattern.compile(".*([aeiouvü])", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(input);
                if (matcher.find()) {
                    return String.valueOf(matcher.group(1));
                }
            }
        }
        return "no";
    }

    int getTone(String word) {
        String[] wordList = word.split("");
        return Integer.valueOf(wordList[word.length()-1]);
    }

    int getVowelPlace(String vowel) {
        switch(vowel) {
            case "a":
                return 1;
            case "e":
                return 2;
            case "i":
                return 3;
            case "o":
                return 4;
            case "u":
                return 5;
            case "v":
                return 6;
        }
        return 0;
    }

    public static void main(String[] args) {
        new Pinyinify("Ni2 jiao4 shenme2 mingzi2 nv3");
    }

}
