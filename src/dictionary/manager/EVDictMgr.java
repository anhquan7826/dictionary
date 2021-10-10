package dictionary.manager;

import java.util.*;

import dictionary.file.FileHandler;
import dictionary.manager.word.Word;

public class EVDictMgr {
    private FileHandler reader = new FileHandler();
    private Map<String, Word> data = reader.getDataEV();

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
     * don't use this unless you need to.
     */
    @Deprecated
    public void printAll() {
        Set<String> allWord = data.keySet();
        System.out.print(Arrays.toString(allWord.toArray()));
    }

    /**
     * add a word to the dictionary.
     * @param word word.
     * @param meaning meaning.
     */
    public void addWord (String word, String meaning) {
        meaning = "<html><i>" + word
                + "</i><br/><ul><li><font color='#cc0000'><b>"
                + word + "</b></font></li></ul></html>";
        // replace entry if already exist.
        if (data.containsKey(word)) {
            data.replace(word, new Word(word, meaning));
        } else {
            data.put(word, new Word (word, meaning));
        }
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
    @Deprecated
    public Map<String, Word> getDataDictionary(){
        return data;
    }

    /**
     * get word
     * @param key key
     * @return value
     */
    public Word getWord(String key) {
        return data.get(key);
    }
}