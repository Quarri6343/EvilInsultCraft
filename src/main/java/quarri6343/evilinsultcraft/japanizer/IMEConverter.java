package quarri6343.evilinsultcraft.japanizer;
/*
 * @author     ucchy
 * @license    LGPLv3
 * @copyright  Copyright ucchy 2020
 * Modified by Quarri6343
 */

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import com.google.common.io.CharStreams;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * ひらがなのみの文章を、IMEを使用して変換します。
 * 使用される変換候補は全て第1候補のため、正しくない結果が含まれることもよくあります。
 *
 * @author ucchy
 */
public class IMEConverter {

    private static final String insultURL = "https://evilinsult.com/generate_insult.php?lang=ja&type=json";
    
    //出典：50人クラフトwiki 2023/2/11
    private static final String dict = "おぱいち,おぱいち,オパイチ,名詞\n" +
            "23時,23時,ニジュウサンジ,名詞\n" +
            "あーけん,あーけん,アーケン,名詞\n" +
            "あるごん,あるごん,アルゴン,名詞\n" +
            "あろーね,あろーね,アローネ,名詞\n" +
            "あっしー,あっしー,アッシー,名詞\n" +
            "ぼー,ぼー,アッシー,名詞\n" +
            "ぼさつ,ぼさつ,アッシー,名詞\n" +
            "DD,DD,アッシー,名詞\n" +
            "ベノム,ベノム,アッシー,名詞\n" +
            "ゴリラビ,ゴリラビ,アッシー,名詞\n" +
            "クリボー,クリボー,アッシー,名詞\n" +
            "ブン,ブン,アッシー,名詞\n" +
            "かえる,かえる,アッシー,名詞\n" +
            "ちゅっぱ,ちゅっぱ,アッシー,名詞\n" +
            "だみとも,だみとも,アッシー,名詞\n" +
            "でで,でで,アッシー,名詞\n" +
            "できおこ,できおこ,アッシー,名詞\n" +
            "でんでん,でんでん,アッシー,名詞\n" +
            "でぽん,でぽん,アッシー,名詞\n" +
            "ド偏見,ド偏見,アッシー,名詞\n" +
            "ドルボス,ドルボス,アッシー,名詞\n" +
            "えびふらい,えびふらい,アッシー,名詞\n" +
            "ふぁけぼ,ふぁけぼ,アッシー,名詞\n" +
            "ファマス,ファマス,アッシー,名詞\n" +
            "フェルナンド,フェルナンド,アッシー,名詞\n" +
            "ふにゃんとりー,ふにゃんとりー,アッシー,名詞\n" +
            "げじげじくん,げじげじくん,アッシー,名詞\n" +
            "ぎん,ぎん,アッシー,名詞\n" +
            "こうたん,こうたん,アッシー,名詞\n" +
            "ひまじん,ひまじん,アッシー,名詞\n" +
            "ハゲネズミ,ハゲネズミ,アッシー,名詞\n" +
            "ハム,ハム,アッシー,名詞\n" +
            "ヘルスカ,ヘルスカ,アッシー,名詞\n" +
            "ヒスイ,ヒスイ,アッシー,名詞\n" +
            "いかお,いかお,アッシー,名詞\n" +
            "いけこ,いけこ,アッシー,名詞\n" +
            "いもむし,いもむし,アッシー,名詞\n" +
            "陰キャ転生,陰キャ転生,アッシー,名詞\n" +
            "いにん,いにん,アッシー,名詞\n" +
            "姫路城,姫路城,アッシー,名詞\n" +
            "よっしー,よっしー,アッシー,名詞\n" +
            "カイキング,カイキング,アッシー,名詞\n" +
            "かきかま,かきかま,アッシー,名詞\n" +
            "かめすた,かめすた,アッシー,名詞\n" +
            "カナリア,カナリア,アッシー,名詞\n" +
            "あさひ,あさひ,アッシー,名詞\n" +
            "かんたすけ,かんたすけ,アッシー,名詞\n" +
            "まっこ,まっこ,アッシー,名詞\n" +
            "このあ,このあ,アッシー,名詞\n" +
            "こたろー,こたろー,アッシー,名詞\n" +
            "こうちゃ,こうちゃ,アッシー,名詞\n" +
            "くも,くも,アッシー,名詞\n" +
            "ラーバ,ラーバ,アッシー,名詞\n" +
            "まちゃかり,まちゃかり,アッシー,名詞\n" +
            "まるたそ,まるたそ,アッシー,名詞\n" +
            "メタボ,メタボ,アッシー,名詞\n" +
            "マイク,マイク,アッシー,名詞\n" +
            "えむえす,えむえす,アッシー,名詞\n" +
            "むぎ,むぎ,アッシー,名詞\n" +
            "ながつき,ながつき,アッシー,名詞\n" +
            "なんじ,なんじ,アッシー,名詞\n" +
            "なっしー,なっしー,アッシー,名詞\n" +
            "なつ,なつ,アッシー,名詞\n" +
            "ニューファング,ニューファング,アッシー,名詞\n" +
            "二重顎,二重顎,アッシー,名詞\n" +
            "ノエル,ノエル,アッシー,名詞\n" +
            "腐肉,腐肉,アッシー,名詞\n" +
            "のるのる,のるのる,アッシー,名詞\n" +
            "にゃんどる,にゃんどる,アッシー,名詞\n" +
            "おちば,おちば,アッシー,名詞\n" +
            "オビワン,オビワン,アッシー,名詞\n" +
            "ぽーん,ぽーん,アッシー,名詞\n" +
            "うんう,うんう,アッシー,名詞\n" +
            "ペニガキ,ペニガキ,アッシー,名詞\n" +
            "ぴんたろう,ぴんたろう,アッシー,名詞\n" +
            "ぽてち,ぽてち,アッシー,名詞\n" +
            "くぁーりぃ,くぁーりぃ,アッシー,名詞\n" +
            "らーめん,らーめん,アッシー,名詞\n" +
            "りけも,りけも,アッシー,名詞\n" +
            "のじゃじゃ,のじゃじゃ,アッシー,名詞\n" +
            "KUN,KUN,アッシー,名詞\n" +
            "ラビット,ラビット,アッシー,名詞\n" +
            "りょうへい,りょうへい,アッシー,名詞\n" +
            "さきちゃん,さきちゃん,アッシー,名詞\n" +
            "さんどうぃち,さんどうぃち,アッシー,名詞\n" +
            "しもさわ,しもさわ,アッシー,名詞\n" +
            "しんご,しんご,アッシー,名詞\n" +
            "しらそる,しらそる,アッシー,名詞\n" +
            "すずき,すずき,アッシー,名詞\n" +
            "ただしゃか,ただしゃか,アッシー,名詞\n" +
            "たぼん,たぼん,アッシー,名詞\n" +
            "たこわさ,たこわさ,アッシー,名詞\n" +
            "たいやき,たいやき,アッシー,名詞\n" +
            "たくみ,たくみ,アッシー,名詞\n" +
            "たなべ,たなべ,アッシー,名詞\n" +
            "たわけ,たわけ,アッシー,名詞\n" +
            "鉄狸,鉄狸,アッシー,名詞\n" +
            "チルノ,チルノ,アッシー,名詞\n" +
            "トム,トム,アッシー,名詞\n" +
            "トーマス,トーマス,アッシー,名詞\n" +
            "トミー,トミー,アッシー,名詞\n" +
            "とんがり,とんがり,アッシー,名詞\n" +
            "とし,とし,アッシー,名詞\n" +
            "ウマヅラハギ,ウマヅラハギ,アッシー,名詞\n" +
            "うみにゃ,うみにゃ,アッシー,名詞\n" +
            "うるんるー,うるんるー,アッシー,名詞\n" +
            "ビビッド,ビビッド,アッシー,名詞\n" +
            "わどるど,わどるど,アッシー,名詞\n" +
            "やち,やち,アッシー,名詞\n" +
            "やなー,やなー,アッシー,名詞\n" +
            "やにゃー,やにゃー,アッシー,名詞\n" +
            "ゆーどん,ゆーどん,アッシー,名詞\n" +
            "ざぐー,ざぐー,アッシー,名詞\n";

    //出典　https://github.com/EvilInsultGenerator/media/blob/57707437de3ef8c83aff17743c495bc2cf35205f/Miscellaneous/Database/insults.sql
    private static final List<String> insults = Arrays.asList(
            "ビッチ", "クソ野郎", "バカ","マヌケ","アホ","生きる価値のない","マヌケ","アホ","変態","死にぞこない","カス","クズ","クソ","ゴミ","デブ","ババア","ジジイ",
            "老害","アバズレ","ハゲ","負け犬","ブス","キモイ","嘘つき","腰抜け","弱虫","オタク","ダサい","ずるい","ケチ","根性なし","尻軽女","いかさま","出来損ない","ドジ","ボケナス","卑怯者","人でなし","無礼者",
            "極悪","狂人","のろま","鈍感","元カノ30人","チー牛","豚","ガイジ","トロール","大沼","社不");
    
    /**
     * GoogleIMEを使って変換する
     *
     * @param org 変換元
     * @return 変換後
     */
    public static String convByGoogleIME(String org) {
        return conv(org);
    }

    // 変換の実行
    private static String conv(String org) {

        if (org.length() == 0) {
            return "";
        }

        //日本語の形態素分析
        Tokenizer.Builder builder = new Tokenizer.Builder();
        Tokenizer tokenizer = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(dict.getBytes("utf-8"));
            tokenizer = builder.userDictionary(inputStream).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Token> tokens = tokenizer.tokenize(org);
        StringBuilder stringBuilder = new StringBuilder();
        for (Token token : tokens) {
            if (token.getPartOfSpeechLevel1().equals("名詞")) {
                stringBuilder.append(insults.get(new Random().nextInt(insults.size())));
            }
            stringBuilder.append(token.getSurface());
        }

        return stringBuilder.toString();
    }
}