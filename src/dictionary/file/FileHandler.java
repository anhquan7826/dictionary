package dictionary.file;

import dictionary.manager.word.Word;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class FileHandler {
    // file paths
    private static final String EV_PATH = "res/E_V.txt";
    private static final String VE_PATH = "res/V_E.txt";
    private static final String FAV_PATH = "res/fav.txt";

    // character used for splitting word target and meaning.
    private static final String SPLITTING_CHARACTERS = "<html>";

    // store data using Treemap
    private Map<String, Word> data = new TreeMap<String, Word>();

    /**
     * reading dictionary data file.
     *
     * @param filePath file path.
     */
    public void readData(String filePath) {
        // put these line in try-catch block as BufferredReader dont throw error if the file is not exist.
        try {
            FileReader fis = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fis);
            String line;
            // read till end.
            while ((line = br.readLine()) != null) {
                // spliting each line into 2 part separated by html block.
                String[] parts = line.split(SPLITTING_CHARACTERS);
                String word = parts[0];
                String word_explain = SPLITTING_CHARACTERS + parts[1];
                Word wordObj = new Word(word, word_explain);
                data.put(word, wordObj);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * writing data to file.
     * @param path file path
     * @throws IOException
     */
    public void writeData(String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        BufferedWriter buffer = new BufferedWriter(writer);
        for (var i : data.entrySet()) {
            buffer.write(i.getValue().getWord_target() + i.getValue().getWord_explain() + "\n");
        }
        buffer.close();
    }

    /**
     * read data from E-V dictionary file.
     *
     * @return a Treemap of E-V data.
     */
    public Map<String, Word> getDataEV() {
        readData(EV_PATH);
        return data;
    }

    /**
     * write current data to E-V dictionary file
     * @throws IOException
     */
    public void writeDataEV() throws IOException {
        writeData(EV_PATH);
    }

    /**
     * read data from V-E dictionary file.
     *
     * @return a Treemap of V-E data.
     */
    public Map<String, Word> getDataVE() {
        readData(VE_PATH);
        return data;
    }

    /**
     * write current data to V-E dictionary file
     * @throws IOException
     */
    public void writeDataVE() throws IOException {
        writeData(VE_PATH);
    }

    /**
     * read data from favorite file
     * @return a Treemap of favorite
     */
    public Map<String, Word> getDataFav() {
        readData(FAV_PATH);
        return data;
    }

    /**
     * write data to favorite file
     * @throws IOException
     */
    public void writeDataFav() throws IOException {
        writeData(FAV_PATH);
    }
}
