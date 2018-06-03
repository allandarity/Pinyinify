package com.elliott.pinyinify;

import java.util.ArrayList;
import java.util.List;

public class Tones {

    private List<String> variants;

    Tones(ArrayList<String> variants) {
        this.variants = new ArrayList<>(variants);
    }

    public List<String> getVariants() {
        return variants;
    }

}
