package com.elliott.pinyinify;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Word {

    private final String word;

    Word(String word) {
        this.word = word;
    }

    String replaceWord(Map<String, Tones> chz) {
        int tone = this.getTone().get(0);
        String replaceChar = this.characterToReplace();
        return word.replaceFirst(replaceChar, chz.get(replaceChar).
                getVariants().get(tone)).replaceAll("[0-9]+", "");
    }

    String characterToReplace() {
        String[] split = word.split("");
        for (int i = 0; i < split.length; i++) {
            if ((split[i].equals("a")) || (split[i].equals("e"))) {
                return split[i].equals("a") ? "a" : "e";
            }
            if ((split[i].equals("o")) || (split[i].equals("u"))) {
                return split[i].equals("o") ? "o" : "u";
            }
            if (i == split.length - 1) {
                Pattern pattern = Pattern.compile(".*([aeiouvü])", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(word);
                if (matcher.find()) {
                    return String.valueOf(matcher.group(1));
                }
            }
        }
        return "";
    }


    List<Integer> getTone() {
        return Arrays.stream(word.split(""))
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
