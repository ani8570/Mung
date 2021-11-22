package me.Mung.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LACrawling {
    private static final Logger LOGGER = LoggerFactory.getLogger(LACrawling.class);

    public static Double FindLevel(String charName) {
        Double d = null;
        String URL = "https://lostark.game.onstove.com/Profile/Character/";
        URL += charName;

        Connection conn = Jsoup.connect(URL);
        Document html = null;
        try {
            html = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements fileBlocks = html.getElementsByClass("level-info2__item");
        if (fileBlocks.size() != 0) {
            String[] L = fileBlocks.select("span").get(1).toString().split("<small>|</small>");
            String i = (L[2] + L[3]).replaceAll(",", "");
            d = Double.parseDouble(i);
        }
        return d;
    }

}
