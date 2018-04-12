package com.gudderi.gudderibatch.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StringUtils;

import java.io.IOException;

public final class DomUtils {
    private DomUtils() {
    }

    /**
     * 与えられたURLからDOMを返す
     *
     * @param url
     * @return
     */
    public static Document scrapeDocumentFromUrl(final String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
