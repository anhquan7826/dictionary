package dictionary.file;

import dictionary.manager.word.Word;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class FileHandler {
    // file paths
    private static final String EV_PATH = "res/E_V.txt";
    private static final String VE_PATH = "res/V_E.txt";
    private static final String EVFAV_PATH = "res/fav_ev.txt";
    private static final String VEFAV_PATH = "res/fav_ve.txt";

    // character used for splitting word target and meaning.
    private static final String SPLITTING_CHARACTERS = "<html>";

    // store data using Treemap
    private Map<String, Word> data = new TreeMap<String, Word>();

    /**
     * reading dictionary data file.
     *
     * @param filePath file path.
     */
    private void readFile(String filePath) {
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
    private void writeFile(String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        BufferedWriter buffer = new BufferedWriter(writer);
        for (var i : data.entrySet()) {
            buffer.write(i.getValue().getWord_target() + i.getValue().getWord_explain() + "\n");
        }
        buffer.close();
    }

    /**
     * get data from {@code Type} file.
     *
     * @return a Treemap of E-V data.
     */
    public Map<String, Word> getData(String type) {
        switch (type) {
            case Type.EV: {
                readFile(EV_PATH);
                break;
            }
            case Type.VE: {
                readFile(VE_PATH);
                break;
            }
            case Type.EVFav: {
                readFile(EVFAV_PATH);
                break;
            }
            case Type.VEFav: {
                readFile(VEFAV_PATH);
                break;
            }
        }
        return data;
    }

    /**
     * update current data to {@Code Type} file
     * @throws IOException
     */
    public void updateData(String type) throws IOException {
        switch (type) {
            case Type.EV: {
                writeFile(EV_PATH);
                break;
            }
            case Type.VE: {
                writeFile(VE_PATH);
                break;
            }
            case Type.EVFav: {
                writeFile(EVFAV_PATH);
                break;
            }
            case Type.VEFav: {
                writeFile(VEFAV_PATH);
                break;
            }
        }
    }
}
