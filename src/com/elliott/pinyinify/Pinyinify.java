package com.elliott.pinyinify;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by elliott on 15/05/17.
 */
public class Pinyinify {

    private Map<String, Characters> chz = new HashMap<>(Map.of(
            "a", new Characters(new ArrayList<>(Arrays.asList("ā", "á", "ǎ", "à"))),
            "e", new Characters(new ArrayList<>(Arrays.asList("ē", "é", "ě", "è"))),
            "i", new Characters(new ArrayList<>(Arrays.asList("ī", "í", "ǐ", "ì"))),
            "o", new Characters(new ArrayList<>(Arrays.asList("ō", "ó", "ǒ", "ò"))),
            "u", new Characters(new ArrayList<>(Arrays.asList("ū", "ú", "ǔ", "ù"))),
            "v", new Characters(new ArrayList<>(Arrays.asList("ǖ", "ǘ", "ǚ", "ǜ")))
    ));

    private Pinyinify(String input) {
        ArrayList<String> sentence = new ArrayList<>(Arrays.asList(input.split(" ")));
        sentence.forEach((String word) -> System.out.println(addTone(word)));
    }

    public static void main(String[] args) {
        new Pinyinify("Ni3 jiao4 shenme2 mingzi2 nv3 ma");
    }

    private String addTone(String word) {
        boolean found = false;
        StringBuilder sb = new StringBuilder();
        List<String> split = new ArrayList<>(Arrays.asList(word.split("")));
        for (String current : split) {
            int tone = getTone(word)-1;
            if(tone == -1) continue;
            if ((!found) && (current.equals(vowelTone(word)))) {
                sb.append(chz.get(current).getVariants().get(tone));
                found = true;
                continue;
            }
            if (current.matches("[0-9]+")) continue;
            sb.append(current);
        }
        return sb.toString();
    }

//    private String addTone(String word) {
//        StringBuilder sb = new StringBuilder();
//        List<String> split = new ArrayList<>(Arrays.asList(word.split("")));
//        split.stream().filter(vowel -> charTone).findFirst();
//        split.forEach(ch -> {
//           if(ch.equals(vowelTone(word))) {
//               int tone = getTone(word);
//               sb.append(charTone[getVowelPlace(ch)][tone]);
//           } else {
//               sb.append(ch);
//           }
//        });
//        return sb.toString();
//    }

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
