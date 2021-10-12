package dictionary.manager;

import dictionary.file.FileHandler;
import dictionary.manager.word.Word;

import java.io.IOException;
import java.util.*;

public class VEDictMgr {
    private FileHandler file = new FileHandler();
    private Map<String, Word> data = file.getDataVE();

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
     * add a word to the dictionary.
     * @param word word.
     * @param meaning meaning.
     */
    public void addWord(String word, String meaning) {
        meaning = "<html><i>" + word
                + "</i><br/><ul><li><font color='#cc0000'><b>"
                + meaning + "</b></font></li></ul></html>";
        // replace entry if already exist.
        if (data.containsKey(word)) {
            data.replace(word, new Word(word, meaning));
        } else {
            data.put(word, new Word (word, meaning));
        }
    }

    /**
     * get word
     * @param key key
     * @return value
     */
    public Word getWord(String key) {
        return data.get(key);
    }

    public void editWord(String target, String explain) {
        Word word = getWord(target);
        word.setWord_explain("<html><i>" + target
        + "</i><br/><ul><li><font color='#cc0000'><b>"
        + explain + "</b></font></li></ul></html>");
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
    public Map<String, Word> getDataDictionary() {
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
        file.writeDataVE();
    }
}
