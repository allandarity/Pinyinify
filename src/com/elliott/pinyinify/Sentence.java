package com.elliott.pinyinify;

import java.util.*;
import java.util.stream.IntStream;

class Sentence {

    private final String sent;

    private Map<String, Tones> chz = new HashMap<>(Map.of(
            "a", new Tones(new ArrayList<>(Arrays.asList(" ", "ā", "á", "ǎ", "à"))),
            "e", new Tones(new ArrayList<>(Arrays.asList(" ", "ē", "é", "ě", "è"))),
            "i", new Tones(new ArrayList<>(Arrays.asList(" ", "ī", "í", "ǐ", "ì"))),
            "o", new Tones(new ArrayList<>(Arrays.asList(" ", "ō", "ó", "ǒ", "ò"))),
            "u", new Tones(new ArrayList<>(Arrays.asList(" ", "ū", "ú", "ǔ", "ù"))),
            "v", new Tones(new ArrayList<>(Arrays.asList(" ", "ǖ", "ǘ", "ǚ", "ǜ")))
    ));

    private Map<Integer, Word> words;
    private StringBuilder sb;

    Sentence(String sent) {
        this.sent = sent;
        this.words = new LinkedHashMap<>();
        this.replace();
        this.rebuild();
    }

    void replace() {
        String[] split = sent.split(" ");
        IntStream.range(0, split.length).forEach(i -> words.putIfAbsent(i, new Word(split[i])));
    }

    void rebuild() {
        sb = new StringBuilder();
        words.forEach((k, v) -> {
            sb.append(v.replaceWord(chz)).append(" ");
        });
    }

    @Override
    public String toString() {
        return sb.toString().trim();
    }


}
