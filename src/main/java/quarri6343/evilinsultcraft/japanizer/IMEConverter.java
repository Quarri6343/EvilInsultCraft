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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * ひらがなのみの文章を、IMEを使用して変換します。
 * 使用される変換候補は全て第1候補のため、正しくない結果が含まれることもよくあります。
 * @author ucchy
 */
public class IMEConverter {
    
    private static final String insultURL = "https://evilinsult.com/generate_insult.php?lang=ja&type=json";
    
    /**
     * GoogleIMEを使って変換する
     * @param org 変換元
     * @return 変換後
     */
    public static String convByGoogleIME(String org) {
        return conv(org);
    }

    // 変換の実行
    private static String conv(String org) {

        if ( org.length() == 0 ) {
            return "";
        }
        
        //日本語の形態素分析
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(org);
        StringBuilder builder = new StringBuilder();
        for (Token token : tokens) {
            if(token.getPartOfSpeechLevel1().equals("名詞")){
                builder.append(getEvilInsult() + token.getSurface());
            }
            else{
                builder.append(token.getSurface());
            }
        }
        
        return builder.toString();
    }
    
    private static String getEvilInsult(){
        HttpURLConnection urlconn = null;
        BufferedReader reader = null;
        HttpURLConnection urlconn2 = null;
        BufferedReader reader2 = null;
        try {
            String baseurl;
            String encode;
            baseurl = insultURL;
            encode = "UTF-8";
            URL url = new URL(baseurl);

            urlconn = (HttpURLConnection)url.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(true);
            urlconn.connect();

            reader = new BufferedReader(
                    new InputStreamReader(urlconn.getInputStream(), encode));

            String json = CharStreams.toString(reader);
            String parsed = GoogleIME.parseJson(json);

            return parsed;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if ( urlconn != null ) {
                urlconn.disconnect();
            }
            if ( reader != null ) {
                try {
                    reader.close();
                } catch (IOException e) { // do nothing.
                }
            }
        }

        return "";
    }
}