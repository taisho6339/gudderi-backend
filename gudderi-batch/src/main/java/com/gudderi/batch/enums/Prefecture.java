package com.gudderi.batch.enums;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Prefecture {
    HOKKAIDO(1, "北海道"),
    AOMORI(2, "青森県"),
    IWATE(3, "岩手県"),
    AKITA(4, "秋田県"),
    MIYAGI(5, "宮城県"),
    YAMAGATA(6, "山形県"),
    FUKUSHIMA(7, "福島県"),
    NIIGATA(8, "新潟県"),
    TOYAMA(9, "富山県"),
    ISHIKAWA(10, "石川県"),
    FUKUI(11, "福井県"),
    NAGANO(12, "長野県"),
    YAMANASHI(13, "山梨県"),
    TOKYO(14, "東京都"),
    KANAGAWA(15, "神奈川県"),
    SAITAMA(16, "埼玉県"),
    CHIBA(17, "千葉県"),
    IBARAKI(18, "茨城県"),
    GUMMA(19, "群馬県"),
    TOCHIGI(20, "栃木県"),
    AICHI(21, "愛知県"),
    SHIZUOKA(22, "静岡県"),
    GIFU(23, "岐阜県"),
    MIE(24, "三重県"),
    OSAKA(25, "大阪府"),
    HYOGO(26, "兵庫県"),
    KYOTO(27, "京都府"),
    NARA(28, "奈良県"),
    SHIGA(29, "滋賀県"),
    WAKAYAMA(30, "和歌山県"),
    TOTTORI(31, "鳥取県"),
    SHIMANE(32, "島根県"),
    OKAYAMA(33, "岡山県"),
    HIROSHIMA(34, "広島県"),
    YAMAGUCHI(35, "山口県"),
    TOKUSHIMA(36, "徳島県"),
    KAGAWA(37, "香川県"),
    EHIME(38, "愛媛県"),
    KOCHI(39, "高知県"),
    FUKUOKA(40, "福岡県"),
    SAGA(41, "佐賀県"),
    NAGASAKI(42, "長崎県"),
    KUMAMOTO(43, "熊本県"),
    OITA(44, "大分県"),
    MIYAZAKI(45, "宮崎県"),
    KAGOSHIMA(46, "鹿児島県"),
    OKINAWA(47, "沖縄県"),
    UNKNOWN(48, "未設定");

    public static Prefecture findByLabel(String label) {
        for (Prefecture prefecture : Prefecture.values()) {
            if (StringUtils.contains(prefecture.getLabel(), label)) {
                return prefecture;
            }
        }
        return Prefecture.UNKNOWN;
    }

    private Integer code;
    private String label;
}