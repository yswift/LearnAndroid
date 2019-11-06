package cn.edu.uoh.learnandroid.contacts;

import java.util.regex.Pattern;

class FormatPattern {
    // 用于替换的匹配表达式
    Pattern matcherPattern;
    // 替换文本
    String replace;

    static FormatPattern[] formatPatterns = {
            // 6位手机短号
            new FormatPattern("^(\\d{2})(\\d{4})$", "$1-$2"),
            // 11位手机号
            new FormatPattern("^(1\\d{2})(\\d{4})(\\d{4})$", "$1-$2-$3"),
            // 固定电话，3位区号（010，02X）, 7位电话，共10位
            new FormatPattern( "^(0[12]\\d{1})(\\d{3})(\\d{4})$", "$1-$2-$3"),
            // 固定电话，3位区号（010，02X）, 8位电话，共11位
            new FormatPattern("^(0[12]\\d{1})(\\d{4})(\\d{4})$", "$1-$2-$3"),
            // 固定电话，4位区号（0871等）, 7位电话，共11位
            new FormatPattern("^(0[3-9]\\d{2})(\\d{3})(\\d{4})$", "$1-$2-$3"),
            // 固定电话，4位区号（0871等）, 8位电话，共12位
            new FormatPattern("^(0[3-9]\\d{2})(\\d{4})(\\d{4})$", "$1-$2-$3"),
    };

    FormatPattern(String matcherRegex, String replace) {
        matcherPattern = Pattern.compile(matcherRegex);
        this.replace = replace;
    }
}
