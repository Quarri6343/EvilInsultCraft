package quarri6343.evilinsultcraft.japanizer;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 「ローマ字」から「かな文字」へ正確に変換するクラス
 *
 * @author YukiLeafX
 * Modified by Quarri6343
 * @see <a href="https://support.microsoft.com/ja-jp/help/883232">参考</a>
 */
public class YukiKanaConverter {

    private static final ImmutableMap<String, String> MAP;

    private static final String[] ROMAJI_LIST;
    private static final String[] HIRAGANA_LIST;

    protected YukiKanaConverter() {
    }

    static {
        ImmutableSortedMap.Builder<String, String> builder = ImmutableSortedMap.reverseOrder();

        // ひらがな
        builder.put("a", "あ");
        builder.put("i", "い").put("yi", "い");
        builder.put("u", "う").put("wu", "う").put("whu", "う");
        builder.put("e", "え");
        builder.put("o", "お");

        builder.put("wha", "うぁ");
        builder.put("whi", "うぃ").put("wi", "うぃ");
        //
        builder.put("whe", "うぇ").put("we", "うぇ");
        builder.put("who", "うぉ");

        //
        builder.put("wyi", "ゐ");
        //
        builder.put("wye", "ゑ");
        //

        builder.put("la", "ぁ").put("xa", "ぁ");
        builder.put("li", "ぃ").put("xi", "ぃ").put("lyi", "ぃ").put("xyi", "ぃ");
        builder.put("lu", "ぅ").put("xu", "ぅ");
        builder.put("le", "ぇ").put("xe", "ぇ").put("lye", "ぇ").put("xye", "ぇ");
        builder.put("lo", "ぉ").put("xo", "ぉ");

        //
        builder.put("ye", "いぇ");
        //
        //
        //

        builder.put("ka", "か").put("ca", "か");
        builder.put("ki", "き");
        builder.put("ku", "く").put("cu", "く").put("qu", "く");
        builder.put("ke", "け");
        builder.put("ko", "こ").put("co", "こ");

        builder.put("kya", "きゃ");
        builder.put("kyi", "きぃ");
        builder.put("kyu", "きゅ");
        builder.put("kye", "きぇ");
        builder.put("kyo", "きょ");

        builder.put("qya", "くゃ");
        //
        builder.put("qyu", "くゅ");
        //
        builder.put("qyo", "くょ");

        builder.put("qwa", "くぁ").put("qa", "くぁ").put("kwa", "くぁ");
        builder.put("qwi", "くぃ").put("qi", "くぃ").put("qyi", "くぃ");
        builder.put("qwu", "くぅ");
        builder.put("qwe", "くぇ").put("qe", "くぇ").put("qye", "くぇ");
        builder.put("qwo", "くぉ").put("qo", "くぉ").put("kwo", "くぉ");

        builder.put("ga", "が");
        builder.put("gi", "ぎ");
        builder.put("gu", "ぐ");
        builder.put("ge", "げ");
        builder.put("go", "ご");

        builder.put("gya", "ぎゃ");
        builder.put("gyi", "ぎぃ");
        builder.put("gyu", "ぎゅ");
        builder.put("gye", "ぎぇ");
        builder.put("gyo", "ぎょ");

        builder.put("gwa", "ぐぁ");
        builder.put("gwi", "ぐぃ");
        builder.put("gwu", "ぐぅ");
        builder.put("gwe", "ぐぇ");
        builder.put("gwo", "ぐぉ");

        builder.put("lka", "ヵ").put("xka", "ヵ");
        //
        //
        builder.put("lke", "ヶ").put("xke", "ヶ");
        //

        builder.put("sa", "さ");
        builder.put("si", "し").put("ci", "し").put("shi", "し");
        builder.put("su", "す");
        builder.put("se", "せ").put("ce", "せ");
        builder.put("so", "そ");

        builder.put("sya", "しゃ").put("sha", "しゃ");
        builder.put("syi", "しぃ");
        builder.put("syu", "しゅ").put("shu", "しゅ");
        builder.put("sye", "しぇ").put("she", "しぇ");
        builder.put("syo", "しょ").put("sho", "しょ");

        builder.put("swa", "すぁ");
        builder.put("swi", "すぃ");
        builder.put("swu", "すぅ");
        builder.put("swe", "すぇ");
        builder.put("swo", "すぉ");

        builder.put("za", "ざ");
        builder.put("zi", "じ").put("ji", "じ");
        builder.put("zu", "ず");
        builder.put("ze", "ぜ");
        builder.put("zo", "ぞ");

        builder.put("zya", "じゃ").put("ja", "じゃ").put("jya", "じゃ");
        builder.put("zyi", "じぃ")/*              */.put("jyi", "じぃ");
        builder.put("zyu", "じゅ").put("ju", "じゅ").put("jyu", "じゅ");
        builder.put("zye", "じぇ").put("je", "じぇ").put("jye", "じぇ");
        builder.put("zyo", "じょ").put("jo", "じょ").put("jyo", "じょ");

        builder.put("ta", "た");
        builder.put("ti", "ち").put("chi", "ち");
        builder.put("tu", "つ").put("tsu", "つ");
        builder.put("te", "て");
        builder.put("to", "と");

        builder.put("tya", "ちゃ").put("cha", "ちゃ").put("cya", "ちゃ");
        builder.put("tyi", "ちぃ")/*               */.put("cyi", "ちぃ");
        builder.put("tyu", "ちゅ").put("chu", "ちゅ").put("cyu", "ちゅ");
        builder.put("tye", "ちぇ").put("che", "ちぇ").put("cye", "ちぇ");
        builder.put("tyo", "ちょ").put("cho", "ちょ").put("cyo", "ちょ");

        builder.put("tsa", "つぁ");
        builder.put("tsi", "つぃ");
        //
        builder.put("tse", "つぇ");
        builder.put("tso", "つぉ");

        builder.put("tha", "てゃ");
        builder.put("thi", "てぃ");
        builder.put("thu", "てゅ");
        builder.put("the", "てぇ");
        builder.put("tho", "てょ");

        builder.put("twa", "とぁ");
        builder.put("twi", "とぃ");
        builder.put("twu", "とぅ");
        builder.put("twe", "とぇ");
        builder.put("two", "とぉ");

        builder.put("da", "だ");
        builder.put("di", "ぢ");
        builder.put("du", "づ");
        builder.put("de", "で");
        builder.put("do", "ど");

        builder.put("dya", "ぢゃ");
        builder.put("dyi", "ぢぃ");
        builder.put("dyu", "ぢゅ");
        builder.put("dye", "ぢぇ");
        builder.put("dyo", "ぢょ");

        builder.put("dha", "でゃ");
        builder.put("dhi", "でぃ");
        builder.put("dhu", "でゅ");
        builder.put("dhe", "でぇ");
        builder.put("dho", "でょ");

        builder.put("dwa", "どぁ");
        builder.put("dwi", "どぃ");
        builder.put("dwu", "どぅ");
        builder.put("dwe", "どぇ");
        builder.put("dwo", "どぉ");

        //
        //
        builder.put("ltu", "っ").put("xtu", "っ").put("ltsu", "っ").put("xtsu", "っ");
        //
        //

        builder.put("na", "な");
        builder.put("ni", "に");
        builder.put("nu", "ぬ");
        builder.put("ne", "ね");
        builder.put("no", "の");

        builder.put("nya", "にゃ");
        builder.put("nyi", "にぃ");
        builder.put("nyu", "にゅ");
        builder.put("nye", "にぇ");
        builder.put("nyo", "にょ");

        builder.put("ha", "は");
        builder.put("hi", "ひ");
        builder.put("hu", "ふ").put("fu", "ふ");
        builder.put("he", "へ");
        builder.put("ho", "ほ");

        builder.put("hya", "ひゃ");
        builder.put("hyi", "ひぃ");
        builder.put("hyu", "ひゅ");
        builder.put("hye", "ひぇ");
        builder.put("hyo", "ひょ");

        builder.put("fwa", "ふぁ").put("fa", "ふぁ");
        builder.put("fwi", "ふぃ").put("fi", "ふぃ").put("fyi", "ふぃ");
        builder.put("fwu", "ふぅ");
        builder.put("fwe", "ふぇ").put("fe", "ふぇ").put("fye", "ふぇ");
        builder.put("fwo", "ふぉ").put("fo", "ふぉ");

        builder.put("fya", "ふゃ");
        //
        builder.put("fyu", "ふゅ");
        //
        builder.put("fyo", "ふょ");

        builder.put("ba", "ば");
        builder.put("bi", "び");
        builder.put("bu", "ぶ");
        builder.put("be", "べ");
        builder.put("bo", "ぼ");

        builder.put("bya", "びゃ");
        builder.put("byi", "びぃ");
        builder.put("byu", "びゅ");
        builder.put("bye", "びぇ");
        builder.put("byo", "びょ");

        builder.put("va", "ヴぁ");
        builder.put("vi", "ヴぃ");
        builder.put("vu", "ヴ");
        builder.put("ve", "ヴぇ");
        builder.put("vo", "ヴぉ");

        builder.put("vya", "ヴゃ");
        builder.put("vyi", "ヴぃ");
        builder.put("vyu", "ヴゅ");
        builder.put("vye", "ヴぇ");
        builder.put("vyo", "ヴょ");

        builder.put("pa", "ぱ");
        builder.put("pi", "ぴ");
        builder.put("pu", "ぷ");
        builder.put("pe", "ぺ");
        builder.put("po", "ぽ");

        builder.put("pya", "ぴゃ");
        builder.put("pyi", "ぴぃ");
        builder.put("pyu", "ぴゅ");
        builder.put("pye", "ぴぇ");
        builder.put("pyo", "ぴょ");

        builder.put("ma", "ま");
        builder.put("mi", "み");
        builder.put("mu", "む");
        builder.put("me", "め");
        builder.put("mo", "も");

        builder.put("mya", "みゃ");
        builder.put("myi", "みぃ");
        builder.put("myu", "みゅ");
        builder.put("mye", "みぇ");
        builder.put("myo", "みょ");

        builder.put("ya", "や");
        //
        builder.put("yu", "ゆ");
        //
        builder.put("yo", "よ");

        builder.put("lya", "ゃ").put("xya", "ゃ");
        //
        builder.put("lyu", "ゅ").put("xyu", "ゅ");
        //
        builder.put("lyo", "ょ").put("xyo", "ょ");

        builder.put("ra", "ら");
        builder.put("ri", "り");
        builder.put("ru", "る");
        builder.put("re", "れ");
        builder.put("ro", "ろ");

        builder.put("rya", "りゃ");
        builder.put("ryi", "りぃ");
        builder.put("ryu", "りゅ");
        builder.put("rye", "りぇ");
        builder.put("ryo", "りょ");

        builder.put("wa", "わ");
        //
        //
        //
        builder.put("wo", "を");

        builder.put("lwa", "ゎ").put("xwa", "ゎ");
        //
        //
        //
        //

        builder.put("n", "ん").put("nn", "ん").put("n'", "ん").put("xn", "ん");

        // 促音を追加する
        for ( Map.Entry<String, String> entry : builder.build().entrySet() ) {
            String romaji = entry.getKey();
            String hiragana = entry.getValue();
            if ( canStartFromSokuon(romaji) ) {
                builder.put(romaji.charAt(0) + romaji, "っ" + hiragana);
            }
        }

        // 記号とか
        builder.put("-", "ー");
        builder.put(",", "、");
        builder.put(".", "。");
        builder.put("?", "？");
        builder.put("!", "！");
        builder.put("[", "「").put("]", "」");
        builder.put("<", "＜").put(">", "＞");
        builder.put("&", "＆");
        builder.put("\"", "”");
        builder.put("(", "（").put(")", "）");

        MAP = builder.build();

        ROMAJI_LIST = MAP.keySet().toArray(new String[0]);
        HIRAGANA_LIST = MAP.values().toArray(new String[0]);
    }

    /**
     * 「っ」や「ッ」などの促音から開始できるかどうか
     *
     * @param romaji 検証したい「ローマ字」
     * @return 促音から開始できるかどうか
     * @since 2.8.10
     */
    private static boolean canStartFromSokuon(String romaji) {
        return !startsWithAny(romaji, "a", "i", "u", "e", "o", "n");
    }

    /**
     * 「ローマ字」から「かな文字」に変換する
     *
     * @param romaji 変換元の「ローマ字」
     * @return 変換後の「かな文字」
     * @since 2.8.10
     */
    public static String conv(String romaji) {
        return replaceEach(romaji, ROMAJI_LIST, HIRAGANA_LIST,false,0);
    }

    /**
     * 全角カッコを半角カッコに変換する (1.8以下のサーバーで全角カッコは表示できないため)
     *
     * @param text 変換元のテキスト
     * @return 全角カッコが半角カッコになったテキスト
     * @since 2.8.10
     */
    public static String fixBrackets(String text) {
        String[] full = new String[] { "（", "）" };
        String[] half = new String[] { "(", ")" };
        return replaceEach(text, full, half, false, 0);
    }

    //copied from org.apache.commons.lang3

    public static boolean startsWithAny(final CharSequence sequence, final CharSequence... searchStrings) {
        if (isEmpty(sequence) || isEmpty(searchStrings)) {
            return false;
        }
        for (final CharSequence searchString : searchStrings) {
            if (startsWith(sequence, searchString, false)) {
                return true;
            }
        }
        return false;
    }

    private static boolean startsWith(final CharSequence str, final CharSequence prefix, final boolean ignoreCase) {
        if (str == null || prefix == null) {
            return str == prefix;
        }
        if (prefix.length() > str.length()) {
            return false;
        }
        return regionMatches(str, ignoreCase, 0, prefix, 0, prefix.length());
    }

    static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart,
                                 final CharSequence substring, final int start, final int length)    {
        if (cs instanceof String && substring instanceof String) {
            return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
        }
        int index1 = thisStart;
        int index2 = start;
        int tmpLen = length;

        // Extract these first so we detect NPEs the same as the java.lang.String version
        final int srcLen = cs.length() - thisStart;
        final int otherLen = substring.length() - start;

        // Check for invalid parameters
        if (thisStart < 0 || start < 0 || length < 0) {
            return false;
        }

        // Check that the regions are long enough
        if (srcLen < length || otherLen < length) {
            return false;
        }

        while (tmpLen-- > 0) {
            final char c1 = cs.charAt(index1++);
            final char c2 = substring.charAt(index2++);

            if (c1 == c2) {
                continue;
            }

            if (!ignoreCase) {
                return false;
            }

            // The real same check as in String.regionMatches():
            final char u1 = Character.toUpperCase(c1);
            final char u2 = Character.toUpperCase(c2);
            if (u1 != u2 && Character.toLowerCase(u1) != Character.toLowerCase(u2)) {
                return false;
            }
        }

        return true;
    }

    private static String replaceEach(
            final String text, final String[] searchList, final String[] replacementList, final boolean repeat, final int timeToLive) {

        // mchyzer Performance note: This creates very few new objects (one major goal)
        // let me know if there are performance requests, we can create a harness to measure

        // if recursing, this shouldn't be less than 0
        if (timeToLive < 0) {
            final Set<String> searchSet = new HashSet<>(Arrays.asList(searchList));
            final Set<String> replacementSet = new HashSet<>(Arrays.asList(replacementList));
            searchSet.retainAll(replacementSet);
            if (searchSet.size() > 0) {
                throw new IllegalStateException("Aborting to protect against StackOverflowError - " +
                        "output of one loop is the input of another");
            }
        }

        if (isEmpty(text) || isEmpty(searchList) || isEmpty(replacementList) || (!isEmpty(searchList) && timeToLive == -1)) {
            return text;
        }

        final int searchLength = searchList.length;
        final int replacementLength = replacementList.length;

        // make sure lengths are ok, these need to be equal
        if (searchLength != replacementLength) {
            throw new IllegalArgumentException("Search and Replace array lengths don't match: "
                    + searchLength
                    + " vs "
                    + replacementLength);
        }

        // keep track of which still have matches
        final boolean[] noMoreMatchesForReplIndex = new boolean[searchLength];

        // index on index that the match was found
        int textIndex = -1;
        int replaceIndex = -1;
        int tempIndex = -1;

        // index of replace array that will replace the search string found
        // NOTE: logic duplicated below START
        for (int i = 0; i < searchLength; i++) {
            if (noMoreMatchesForReplIndex[i] || isEmpty(searchList[i]) || replacementList[i] == null) {
                continue;
            }
            tempIndex = text.indexOf(searchList[i]);

            // see if we need to keep searching for this
            if (tempIndex == -1) {
                noMoreMatchesForReplIndex[i] = true;
            } else {
                if (textIndex == -1 || tempIndex < textIndex) {
                    textIndex = tempIndex;
                    replaceIndex = i;
                }
            }
        }
        // NOTE: logic mostly below END

        // no search strings found, we are done
        if (textIndex == -1) {
            return text;
        }

        int start = 0;

        // get a good guess on the size of the result buffer so it doesn't have to double if it goes over a bit
        int increase = 0;

        // count the replacement text elements that are larger than their corresponding text being replaced
        for (int i = 0; i < searchList.length; i++) {
            if (searchList[i] == null || replacementList[i] == null) {
                continue;
            }
            final int greater = replacementList[i].length() - searchList[i].length();
            if (greater > 0) {
                increase += 3 * greater; // assume 3 matches
            }
        }
        // have upper-bound at 20% increase, then let Java take over
        increase = Math.min(increase, text.length() / 5);

        final StringBuilder buf = new StringBuilder(text.length() + increase);

        while (textIndex != -1) {

            for (int i = start; i < textIndex; i++) {
                buf.append(text.charAt(i));
            }
            buf.append(replacementList[replaceIndex]);

            start = textIndex + searchList[replaceIndex].length();

            textIndex = -1;
            replaceIndex = -1;
            tempIndex = -1;
            // find the next earliest match
            // NOTE: logic mostly duplicated above START
            for (int i = 0; i < searchLength; i++) {
                if (noMoreMatchesForReplIndex[i] || searchList[i] == null ||
                        searchList[i].isEmpty() || replacementList[i] == null) {
                    continue;
                }
                tempIndex = text.indexOf(searchList[i], start);

                // see if we need to keep searching for this
                if (tempIndex == -1) {
                    noMoreMatchesForReplIndex[i] = true;
                } else {
                    if (textIndex == -1 || tempIndex < textIndex) {
                        textIndex = tempIndex;
                        replaceIndex = i;
                    }
                }
            }
            // NOTE: logic duplicated above END

        }
        final int textLength = text.length();
        for (int i = start; i < textLength; i++) {
            buf.append(text.charAt(i));
        }
        final String result = buf.toString();
        if (!repeat) {
            return result;
        }

        return replaceEach(result, searchList, replacementList, repeat, timeToLive - 1);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isEmpty(final String[] array) {
        return getLength(array) == 0;
    }

    public static boolean isEmpty(final CharSequence[] array) {
        return getLength(array) == 0;
    }

    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }
}
