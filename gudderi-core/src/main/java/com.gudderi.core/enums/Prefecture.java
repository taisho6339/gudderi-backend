package com.gudderi.core.enums;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Prefecture implements Convertible<Integer> {
    北海道(1, "北海道"),
    青森県(2, "青森県"),
    岩手県(3, "岩手県"),
    秋田県(4, "秋田県"),
    宮城県(5, "宮城県"),
    山形県(6, "山形県"),
    福島県(7, "福島県"),
    新潟県(8, "新潟県"),
    富山県(9, "富山県"),
    石川県(10, "石川県"),
    福井県(11, "福井県"),
    長野県(12, "長野県"),
    山梨県(13, "山梨県"),
    東京都(14, "東京都"),
    神奈川県(15, "神奈川県"),
    埼玉県(16, "埼玉県"),
    千葉県(17, "千葉県"),
    茨城県(18, "茨城県"),
    群馬県(19, "群馬県"),
    栃木県(20, "栃木県"),
    愛知県(21, "愛知県"),
    静岡県(22, "静岡県"),
    岐阜県(23, "岐阜県"),
    三重県(24, "三重県"),
    大阪府(25, "大阪府"),
    兵庫県(26, "兵庫県"),
    京都府(27, "京都府"),
    奈良県(28, "奈良県"),
    滋賀県(29, "滋賀県"),
    和歌山県(30, "和歌山県"),
    鳥取県(31, "鳥取県"),
    島根県(32, "島根県"),
    岡山県(33, "岡山県"),
    広島県(34, "広島県"),
    山口県(35, "山口県"),
    徳島県(36, "徳島県"),
    香川県(37, "香川県"),
    愛媛県(38, "愛媛県"),
    高知県(39, "高知県"),
    福岡県(40, "福岡県"),
    佐賀県(41, "佐賀県"),
    長崎県(42, "長崎県"),
    熊本県(43, "熊本県"),
    大分県(44, "大分県"),
    宮崎県(45, "宮崎県"),
    鹿児島県(46, "鹿児島県"),
    沖縄県(47, "沖縄県"),
    未設定(48, "未設定");

    public static Prefecture findByLabel(String label) {
        for (Prefecture prefecture : Prefecture.values()) {
            if (StringUtils.contains(prefecture.getLabel(), label)) {
                return prefecture;
            }
        }
        return Prefecture.未設定;
    }

    private Integer code;
    private String label;
}