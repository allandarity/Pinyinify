package com.elliott.pinyinify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by elliott on 15/05/17.
 */
public class Pinyinify {

    private char[][] charTone = {
            {'a', 'ā', 'á', 'ǎ', 'à'},
            {'e', 'ē', 'é', 'ě', 'è'},
            {'i', 'ī', 'í', 'ǐ', 'ì'},
            {'o', 'ō', 'ó', 'ǒ', 'ò'},
            {'u', 'ū', 'ú', 'ǔ', 'ù'},
            {'u', 'ǖ', 'ǘ', 'ǚ', 'ǜ'},
    };

    private Pinyinify(String input) {
        String[] splitInput = input.split(" ");
        ArrayList<String> sentence = new ArrayList<>();
        sentence.addAll(Arrays.asList(splitInput));
        for (String s : sentence) System.out.print(addTone(s) + " ");
    }

    public static void main(String[] args) {
        new Pinyinify("Ni3 jiao4 shenme2 mingzi2 nv3 ma");
    }

    private String addTone(String word) {
        boolean found = false;
        StringBuilder sb = new StringBuilder();
        String[] splitString = word.split("");
        for (String current : splitString) {
            if (!found) {
                if ((current.equals(vowelTone(word)))) {
                    int tone = getTone(word);
                    sb.append(charTone[getVowelPlace(current)][tone]);
                    found = true;
                    continue;
                }
            }
            if (current.matches("[0-9]+")) continue;
            sb.append(current);
        }
        return sb.toString();
    }

    private String vowelTone(String input) {
        String[] splitString = input.split("");
        for (int i = 0; i < splitString.length; i++) {
            if ((splitString[i].equals("a")) || (splitString[i].equals("e"))) {
                return splitString[i].equals("a") ? "a" : "e";
            }
            if ((splitString[i].equals("o")) || (splitString[i].equals("u"))) {
                return splitString[i].equals("o") ? "o" : "u";
            }
            if (i == splitString.length - 1) {
                Pattern pattern = Pattern.compile(".*([aeiouvü])", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(input);
                if (matcher.find()) {
                    return String.valueOf(matcher.group(1));
                }
            }
        }
        return "";
    }

    private int getTone(String word) {
        String[] wordList = word.split("");
        try {
            Integer.parseInt(wordList[word.length() - 1]);
        } catch (NumberFormatException e) {
            return 0;
        }
        return Integer.valueOf(wordList[word.length() - 1]);
    }

    private int getVowelPlace(String vowel) {
        switch (vowel) {
            case "a":
                return 0;
            case "e":
                return 1;
            case "i":
                return 2;
            case "o":
                return 3;
            case "u":
                return 4;
            case "v":
                return 5;
            default:
                return -1;
        }
    }

}
