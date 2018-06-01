package com.elliott.pinyinify;

import java.util.ArrayList;
import java.util.List;

public class Characters {

    private List<String> variants;

    Characters(ArrayList<String> variants) {
        this.variants = new ArrayList<>(variants);
    }

    public List<String> getVariants() {
        return this.variants;
    }

}
