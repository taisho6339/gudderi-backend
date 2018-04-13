package com.gudderi.gudderibatch.repository.impl;

import com.gudderi.gudderibatch.domain.Artist;
import com.gudderi.gudderibatch.domain.Live;
import com.gudderi.gudderibatch.domain.LiveSchedule;
import com.gudderi.gudderibatch.enums.Prefecture;
import com.gudderi.gudderibatch.repository.ArtistLiveExtractRepository;
import com.gudderi.gudderibatch.utils.DomUtils;

import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Lists;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ArtistLiveScrapingExtractRepository implements ArtistLiveExtractRepository {
    private static final String ROOT_URL = "https://ticket.st";
    private static final String SOURCE_URL = ROOT_URL + "/category/johnnys-concert-list";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    private static final Pattern PREFECTURE_PATTERN = Pattern.compile(".*[0-9]{1,2}:[0-9]{1,2} (.*)");

    @Override
    public List<Artist> getArtistLiveList() {
        List<ArtistScrapingInfo> artistScrapingInfoList = scrapeArtistInfo();
        if (artistScrapingInfoList == null) {
            return null;
        }
        return artistScrapingInfoList
                .stream()
                .map(this::scrapeArtistLive)
                .filter(item -> item.getLiveList() != null && !item.getLiveList().isEmpty())
                .collect(Collectors.toList());
    }

    private List<ArtistScrapingInfo> scrapeArtistInfo() {
        Document document = DomUtils.scrapeDocumentFromUrl(SOURCE_URL);
        if (document == null) {
            return null;
        }

        Element listElement = document.getElementsByClass("listing_box").get(0);
        Elements elements = listElement.getElementsByTag("li");
        return elements.stream().map(element -> {
            String artistName = element.getElementsByClass("alt_list__inner_hitarea_p").get(0).childNode(0).outerHtml();
            String detailLink = element.getElementsByTag("a").get(0).attr("href");
            return new ArtistScrapingInfo(artistName, detailLink);
        }).collect(Collectors.toList());
    }

    private Artist scrapeArtistLive(ArtistScrapingInfo artistScrapingInfo) {
        Document document = DomUtils.scrapeDocumentFromUrl(ROOT_URL + artistScrapingInfo.getDetailLink());
        if (document == null) {
            return Artist.builder()
                    .artistName(artistScrapingInfo.getArtistName())
                    .build();
        }

        Elements liveSectionElements = document
                .getElementsByClass("schedule hide-for-small-only");
        if (liveSectionElements == null || liveSectionElements.isEmpty()) {
            log.error("live Parse Failed. link = {}", artistScrapingInfo.getDetailLink());
            return Artist.builder()
                    .artistName(artistScrapingInfo.getArtistName())
                    .build();
        }

        Element liveSection = liveSectionElements.get(0);
        Elements liveTitleElements = liveSection.getElementsByTag("dt");
        Elements liveListElements = liveSection.getElementsByTag("dd");

        //dtとddが対になっているので紐付ける
        List<Pair<Element, Element>> zippedElements = Lists.mutable.of(
                liveTitleElements.toArray(new Element[liveTitleElements.size()])
        ).zip(
                Lists.mutable.of(
                        liveListElements.toArray(new Element[liveListElements.size()])
                )
        );
        List<Live> liveList = zippedElements.stream().map(pair -> {
            Element liveTitleElement = pair.getOne();
            String title = liveTitleElement.getElementsByTag("a").get(0).text();

            Element liveListElement = pair.getTwo();
            List<LiveSchedule> liveScheduleList = liveListElement.getElementsByAttributeValue("itemprop", "event")
                    .stream()
                    .map(this::scrapeLiveSchedule)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            return Live.builder()
                    .liveName(title)
                    .liveScheduleList(liveScheduleList)
                    .build();
        }).collect(Collectors.toList());

        return Artist.builder()
                .artistName(artistScrapingInfo.getArtistName())
                .liveList(liveList)
                .build();
    }

    private LiveSchedule scrapeLiveSchedule(Element element) {
        // ライブ日程
        String liveDateString = element
                .getElementsByAttributeValue("itemprop", "startDate")
                .get(0)
                .attr("content");
        Date liveDate;
        try {
            liveDate = DATE_FORMAT.parse(liveDateString);
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null;
        }

        // 会場住所
        Elements locationElements = element.getElementsByClass("hide");
        String livePlace = locationElements
                .get(0)
                .getElementsByAttributeValue("itemprop", "name")
                .attr("content");

        // 会場都道府県
        String prefectureContent = element.getElementsByAttributeValue("itemprop", "url")
                .get(0)
                .text();
        Matcher matcher = PREFECTURE_PATTERN.matcher(prefectureContent);
        if (!matcher.find()) {
            log.error("livePrefecture Parse Failed. livePrefecture = {}", prefectureContent);
            return LiveSchedule.builder()
                    .livePrefecture(Prefecture.UNKNOWN)
                    .livePlace(livePlace)
                    .liveDate(liveDate)
                    .build();
        }

        Prefecture prefecture = Prefecture.findByLabel(matcher.group(1));
        return LiveSchedule.builder()
                .livePrefecture(prefecture)
                .livePlace(livePlace)
                .liveDate(liveDate)
                .build();
    }

    @AllArgsConstructor
    @Getter
    private class ArtistScrapingInfo {
        private String artistName;
        private String detailLink;
    }
}
