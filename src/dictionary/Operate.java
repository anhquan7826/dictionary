package dictionary;

import java.util.List;

import dictionary.manager.EVDictMgr;
import dictionary.manager.FavoriteMgr;
import dictionary.manager.VEDictMgr;
import dictionary.manager.word.Word;

public class Operate {
    private static EVDictMgr EVdict = new EVDictMgr();
    private static VEDictMgr VEdict = new VEDictMgr();
    private static FavoriteMgr fav = new FavoriteMgr();

    public static void initOperation() {}

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

        public static void deleteWord(String word) {
            EVdict.deleteWord(word);
        }
    }

    public static class VEDictionary {
        public static Word getWord(String key) {
            return EVdict.getWord(key);
        }

        public static List<String> searchWord(String query) {
            return VEdict.searchWord(query);
        }

        public static void addWord(String word, String meaning) {
            VEdict.addWord(word, meaning);
        }

        public static void deleteWord(String word) {
            VEdict.deleteWord(word);
        }
    }

    public static class Favorite {
        public static void addWord(String word) {
            fav.addWord(word);
        }

        public static void removeWord(String word) {
            fav.removeWord(word);
        }

        public static List<String> getAll() {
            return fav.getAll();
        }
    }
}
