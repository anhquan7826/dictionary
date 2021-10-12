package dictionary.manager;

import java.io.IOException;
import java.util.*;

import dictionary.file.FileHandler;
import dictionary.manager.word.Word;

public class FavoriteMgr {
    private FileHandler file = new FileHandler();
    private Map<String, Word> data = file.getDataFav();

    /**
     * add a word to favorite.
     * @param word word.
     */
    public void addWord(Word word) {
        data.put(word.getWord_target(), word);
    }

    /**
     * get word
     * @param key key
     * @return value
     */
    public Word getWord(String key) {
        return data.get(key);
    }

    /**
     * remove a word 
     * @param word
     */
    public void removeWord(String word) {
        data.remove(word);
    }

    /**
     * get all favorite words.
     * @return list of strings
     */
    public Map<String, Word> getDataFavorite() {
        return data;
    }

    /**
     * update favorite data. should be used when quit.
     * @throws IOException
     */
    public void updateData() throws IOException {
        file.writeDataFav();
    }
}
