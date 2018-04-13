package com.gudderi.gudderibatch.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Prefecture {
    HOKKAIDO(0, "北海道"),
    AOMORI(1, "青森県"),
    IWATE(2, "岩手県"),
    AKITA(3, "秋田県"),
    MIYAGI(4, "宮城県"),
    YAMAGATA(5, "山形県"),
    FUKUSHIMA(6, "福島県"),
    NIIGATA(7, "新潟県"),
    TOYAMA(8, "富山県"),
    ISHIKAWA(9, "石川県"),
    FUKUI(10, "福井県"),
    NAGANO(11, "長野県"),
    YAMANASHI(12, "山梨県"),
    TOKYO(13, "東京都"),
    KANAGAWA(14, "神奈川県"),
    SAITAMA(15, "埼玉県"),
    CHIBA(16, "千葉県"),
    IBARAKI(17, "茨城県"),
    GUMMA(18, "群馬県"),
    TOCHIGI(19, "栃木県"),
    AICHI(20, "愛知県"),
    SHIZUOKA(21, "静岡県"),
    GIFU(22, "岐阜県"),
    MIE(23, "三重県"),
    OSAKA(24, "大阪府"),
    HYOGO(25, "兵庫県"),
    KYOTO(26, "京都府"),
    NARA(27, "奈良県"),
    SHIGA(28, "滋賀県"),
    WAKAYAMA(29, "和歌山県"),
    TOTTORI(30, "鳥取県"),
    SHIMANE(31, "島根県"),
    OKAYAMA(32, "岡山県"),
    HIROSHIMA(33, "広島県"),
    YAMAGUCHI(34, "山口県"),
    TOKUSHIMA(35, "徳島県"),
    KAGAWA(36, "香川県"),
    EHIME(37, "愛媛県"),
    KOCHI(38, "高知県"),
    FUKUOKA(39, "福岡県"),
    SAGA(40, "佐賀県"),
    NAGASAKI(41, "長崎県"),
    KUMAMOTO(42, "熊本県"),
    OITA(43, "大分県"),
    MIYAZAKI(44, "宮崎県"),
    KAGOSHIMA(45, "鹿児島県"),
    OKINAWA(46, "沖縄県"),
    OTHER(47, "海外");

    private Integer code;
    private String label;
}