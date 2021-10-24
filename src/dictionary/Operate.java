package dictionary;

import dictionary.file.Type;
import dictionary.manager.DictionaryMgr;
import dictionary.manager.word.Word;
import dictionary.manager.TranslateMgr;
import dictionary.manager.TextToSpeechMgr;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * only using one static class for all function. no need to instantialize any class instances.
 */

public class Operate {
    private static DictionaryMgr EVdict;
    private static DictionaryMgr VEdict;
    private static DictionaryMgr EVfav;
    private static DictionaryMgr VEfav;
//  private static TranslateMgr trans = new TranslateMgr();

    /**
     * load all dictionaries and favoirites before starting.
     */
    public static void initOperation() {
        EVdict = new DictionaryMgr(Type.EV);
        VEdict = new DictionaryMgr(Type.VE);
        EVfav = new DictionaryMgr(Type.EVFav);
        VEfav = new DictionaryMgr(Type.VEFav);
    }

    /**
     * write all data stored in stack to files.
     * @throws IOException
     */
    public static void updateData() throws IOException {
        EVdict.updateData();
        VEdict.updateData();
        EVfav.updateData();
        VEfav.updateData();
    }

    public static class TextToSpeech {
        /**
         * text to speech.
         * @param text text to convert.
         */
        public static void Speak(String text) {
            TextToSpeechMgr.Speak(text);
        }
    }

    public static class Translate {
        /**
         * translate a string from a language to another.
         * @param langFrom input language.
         * @param langTo output language.
         * @param text text to translate.
         * @return translated text.
         * @throws IOException
         */
        public static String translate(String langFrom, String langTo, String text) throws IOException {
            return TranslateMgr.translate(langFrom, langTo , text);
        }
    }

    public static class Dictionary {
        /**
         * get Word from dictionary.
         * @param type dictionary type.
         * @param key keyword.
         * @return Word object
         */
        public static Word getWord(String type, String key) {
            if (type.equals(Type.EV)) {
                return EVdict.getWord(key);
            } else {
                return VEdict.getWord(key);
            }
        }

        /**
         * search a word from dictionary data.
         * @param type dictionary type.
         * @param query search query.
         * @return list of results.
         */
        public static List<String> searchWord(String type, String query) {
            if (type.equals(Type.EV)) {
                return EVdict.searchWord(query);
            } else {
                return VEdict.searchWord(query);
            }
        }

        /**
         * add word to dictionary.
         * @param type dictionary type.
         * @param target word target.
         * @param explain word explain.
         */
        public static void addWord(String type, String target, String explain) {
            if (type.equals(Type.EV)) {
                EVdict.addWord(target, explain);
            } else {
                VEdict.addWord(target, explain);
            }
        }

        /**
         * edit a word.
         * @param type dictionary type.
         * @param target word target.
         * @param explain word explain.
         */
        public static void editWord(String type, String target, String explain) {
            if (type.equals(Type.EV)) {
                EVdict.editWord(target, explain);
                if (EVfav.getData().containsKey(target)) {
                    EVfav.editWord(target, explain);
                }
            } else {
                VEdict.editWord(target, explain);
                if (VEfav.getData().containsKey(target)) {
                    VEfav.editWord(target, explain);
                }
            }
        }

        /**
         * delete a word.
         * @param type dictionary type.
         * @param word word to delete.
         */
        public static void deleteWord(String type, String word) {
            if (type.equals(Type.EV)) {
                EVdict.deleteWord(word);
                if (EVfav.getData().containsKey(word)) {
                    EVfav.deleteWord(word);
                }
            } else {
                VEdict.deleteWord(word);
                if (EVfav.getData().containsKey(word)) {
                    EVfav.deleteWord(word);
                }
            }
        }

        /**
         * get dictionary Map data.
         * @param type dictionary type.
         * @return dictionary Map.
         */
        public static Map<String, Word> getData(String type) {
            if (type.equals(Type.EV)) {
                return EVdict.getData();
            } else {
                return VEdict.getData();
            }
        }
    }

    public static class Favorite {
        /**
         * get word from favorite.
         * @param type dictionary type.
         * @param key keyword.
         * @return Word object.
         */
        public static Word getWord(String type, String key) {
            if (type.equals(Type.EVFav)) {
                return EVfav.getWord(key);
            } else {
                return VEfav.getWord(key);
            }
        }

        /**
         * add word to favorite.
         */
        public static void addWord(String type, String target, String explain) {
            if (type.equals(Type.EVFav)) {
                EVfav.addWord(target, explain);
            } else {
                VEfav.addWord(target, explain);
            }
        }

        /**
         * delete word from favorite.
         * @param type dictionary type.
         * @param word word to delete.
         */
        public static void deleteWord(String type, String word) {
            if (type.equals(Type.EVFav)) {
                EVfav.deleteWord(word);
            } else {
                VEfav.deleteWord(word);
            }
        }

        /**
         * get favorite Map data.
         * @param type dictionary type.
         * @return favorite Map.
         */
        public static Map<String, Word> getData(String type) {
            if (type.equals(Type.EVFav)) {
                return EVfav.getData();
            } else {
                return VEfav.getData();
            }
        }
    }
}
