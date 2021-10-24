package dictionary.manager;

import dictionary.file.FileHandler;
import dictionary.manager.word.Word;

import java.io.IOException;
import java.util.*;

public class DictionaryMgr {
    private FileHandler file = new FileHandler();
    private Map<String, Word> data;
    private String dictType;

    public DictionaryMgr(String type) {
        data = file.getData(type);
        dictType = type;
    }

    /**
     * search words using a query.
     * @param query search query.
     * @return a list of results.
     */
    public List<String> searchWord(String query) {
        List<String> listWord = new ArrayList<>();
        for (String a : data.keySet()) {
            if (a.toLowerCase().startsWith(query.toLowerCase())) {
                listWord.add(a);
            }
        }
        return listWord;
    }

    /**
     * Add word to dictionary.
     * @param target word target.
     * @param explain word explain.
     */
    public void addWord(String target, String explain) {
        // replace entry if already exist.
        if (data.containsKey(target)) {
            data.replace(target, new Word(target, explain));
        } else {
            data.put(target, new Word (target, explain));
        }
    }

    /**
     * get word.
     * @param key key.
     * @return value.
     */
    public Word getWord(String key) {
        return data.get(key);
    }

    /**
     * edit a word.
     * @param target word target.
     * @param explain word explain.
     */
    public void editWord(String target, String explain) {
        Word word = getWord(target);
        if (word == null) {
            return;
        }
        word.setWord_explain(explain);
    }

    /**
     * remove a word from dictionary.
     * @param word word.
     */
    public void deleteWord(String word) {
        data.remove(word);
    }

    /**
     * get map of dictionary.
     * @return a TreeMap.
     */
    public Map<String, Word> getData() {
        return data;
    }

    /** 
     * don't use this unless you need to.
     */
    @Deprecated
    public void printAll() {
        Set<String> allWord = data.keySet();
        System.out.print(Arrays.toString(allWord.toArray()));
    }

    /**
     * update dictionary data. should be used when quit.
     * @throws IOException
     */
    public void updateData() throws IOException {
        file.updateData(dictType);
    }
}