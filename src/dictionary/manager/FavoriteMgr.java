package dictionary.manager;

import java.util.*;

import dictionary.file.FileHandler;

public class FavoriteMgr {
    private FileHandler reader = new FileHandler();
    private List<String> data = reader.getFavorite();

    /**
     * add a word to favorite.
     * @param word word.
     */
    public void addWord(String word) {
        reader.addFavorite(word);
    }

    /**
     * remove a word 
     * @param word
     */
    public void removeWord(String word) {
        reader.removeFavorite(word);
    }

    /**
     * get all favorite words.
     * @return list of strings
     */
    public List<String> getAll() {
        return data;
    }

    /** 
     * print all, for testing purpose.
     */
    @Deprecated
    public void printAll() {
        for (String fav : data) {
            System.out.println(fav);
        }
    }
}
