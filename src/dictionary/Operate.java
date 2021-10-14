package dictionary;

import dictionary.file.Type;
import dictionary.manager.DictionaryMgr;
import dictionary.manager.word.Word;
import dictionary.manager.TranslateMgr;
import dictionary.manager.TextToSpeechMgr;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Operate {
    private static DictionaryMgr EVdict;
    private static DictionaryMgr VEdict;
    private static DictionaryMgr EVfav;
    private static DictionaryMgr VEfav;
    private static TranslateMgr trans;

    public static void initOperation() {
        EVdict = new DictionaryMgr(Type.EV);
        VEdict = new DictionaryMgr(Type.VE);
        EVfav = new DictionaryMgr(Type.EVFav);
        VEfav = new DictionaryMgr(Type.VEFav);
        trans = new TranslateMgr();
    }

    public static void updateData() throws IOException {
        EVdict.updateData();
        VEdict.updateData();
        EVfav.updateData();
        VEfav.updateData();
    }

    public static class TextToSpeech {
        public static void Speak(String text) {
            TextToSpeechMgr.Speak(text);
        }
    }

    public static class Translate {
        public static String translate(String langFrom, String langTo, String text) throws IOException {
            return trans.translate(langFrom, langTo, text);
        }
    }

    public static class Dictionary {
        public static Word getWord(String type, String key) {
            if (type == Type.EV) {
                return EVdict.getWord(key);
            } else {
                return VEdict.getWord(key);
            }
        }

        public static List<String> searchWord(String type, String query) {
            if (type == Type.EV) {
                return EVdict.searchWord(query);
            } else {
                return VEdict.searchWord(query);
            }
        }

        public static void addWord(String type, String target, String explain) {
            if (type == Type.EV) {
                EVdict.addWord(target, explain);
            } else {
                VEdict.addWord(target, explain);
            }
        }

        public static void editWord(String type, String target, String explain) {
            if (type == Type.EV) {
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

        public static void deleteWord(String type, String word) {
            if (type == Type.EV) {
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

        public static Map<String, Word> getData(String type) {
            if (type == Type.EV) {
                return EVdict.getData();
            } else {
                return VEdict.getData();
            }
        }
    }

    public static class Favorite {
        public static Word getWord(String type, String key) {
            if (type == Type.EVFav) {
                return EVfav.getWord(key);
            } else {
                return VEfav.getWord(key);
            }
        }

        public static void addWord(String type, String target, String explain) {
            if (type == Type.EVFav) {
                EVfav.addWord(target, explain);
            } else {
                VEfav.addWord(target, explain);
            }
        }

        public static void deleteWord(String type, String word) {
            if (type == Type.EVFav) {
                EVfav.deleteWord(word);
            } else {
                VEfav.deleteWord(word);
            }
        }

        public static Map<String, Word> getData(String type) {
            if (type == Type.EVFav) {
                return EVfav.getData();
            } else {
                return VEfav.getData();
            }
        }
    }
}
