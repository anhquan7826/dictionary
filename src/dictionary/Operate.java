package dictionary;

import dictionary.manager.EVDictMgr;
import dictionary.manager.FavoriteMgr;
import dictionary.manager.VEDictMgr;
import dictionary.manager.word.Word;
import dictionary.manager.TranslateMgr;
import dictionary.manager.TextToSpeechMgr;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Operate {
    private static EVDictMgr EVdict = new EVDictMgr();
    private static VEDictMgr VEdict = new VEDictMgr();
    private static FavoriteMgr fav = new FavoriteMgr();
    private static TranslateMgr trans = new TranslateMgr();

    public static void initOperation() {}
    public static class  TextToSpeech{
        public static void Speak(String text){
            TextToSpeechMgr.Speak(text);
        }
    }

    public static class Translate {
        public static String translate(String langFrom, String langTo, String text) throws IOException {
            return trans.translate(langFrom, langTo, text);
        }
    }

    public static class EVDictionary {
        public static Word getWord(String key) {
            return EVdict.getWord(key);
        }

        public static List<String> searchWord(String query) {
            return EVdict.searchWord(query);
        }

        public static void addWord(String word, String meaning) {
            EVdict.addWord(word, meaning);
        }

        public static void editWord(String target, String explain) {
            EVdict.editWord(target, explain);
            if (fav.getDataFavorite().containsKey(target)) {
                fav.getDataFavorite().replace(target, EVdict.getWord(target));
            }
        }

        public static void deleteWord(String word) {
            EVdict.deleteWord(word);
        }

        public static void updateData() throws IOException {
            EVdict.updateData();
        }

        public static Map<String, Word> getData() {
            return EVdict.getDataDictionary();
        }
    }

    public static class VEDictionary {
        public static Word getWord(String key) {
            return VEdict.getWord(key);
        }

        public static List<String> searchWord(String query) {
            return VEdict.searchWord(query);
        }

        public static void addWord(String word, String meaning) {
            VEdict.addWord(word, meaning);
        }

        public static void editWord(String target, String explain) {
            VEdict.editWord(target, explain);
            if (fav.getDataFavorite().containsKey(target)) {
                fav.getDataFavorite().replace(target, VEdict.getWord(target));
            }
        }

        public static void deleteWord(String word) {
            VEdict.deleteWord(word);
        }

        public static void updateData() throws IOException {
            VEdict.updateData();
        }

        public static Map<String, Word> getData() {
            return VEdict.getDataDictionary();
        }
    }

    public static class Favorite {
        public static Word getWord(String key) {
            return fav.getWord(key);
        }

        public static void addWord(Word word) {
            fav.addWord(word);
        }

        public static void removeWord(String word) {
            fav.removeWord(word);
        }

        public static Map<String, Word> getData() {
            return fav.getDataFavorite();
        }

        public static void updateData() throws IOException {
            fav.updateData();
        }
    }
    public static void main(String[] args){}

}
