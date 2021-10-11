package dictionary.file;

import dictionary.manager.word.Word;

import java.io.*;
import java.util.ArrayList;
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
     * read data from E-V dictionary file.
     *
     * @return a Treemap of E-V data.
     */
    public Map<String, Word> getDataEV() {
        readData(EV_PATH);
        return data;
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

    // this path below is for reading and writing to favorite file.

    /**
     * read stored favorite word from file.
     */
    public void readFavorite() {
        try {
            FileReader fis = new FileReader(FAV_PATH);
            BufferedReader br = new BufferedReader(fis);
            String line;
            while ((line = br.readLine()) != null) {
                data.put(line, null);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write a word to favorite list.
     *
     * @param favorite word to write into.
     */
    public void writeFavorite(String favorite) {
        favorite = favorite.toLowerCase();
        if (data.containsKey(favorite)) {
            return;
        }
        data.put(favorite, null);
        try {
            FileWriter writer = new FileWriter(FAV_PATH);
            writer.write(favorite + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get an arraylist of favorite word.
     *
     * @return arraylist of string.
     */
    public ArrayList<String> getFavorite() {
        readFavorite();
        return new ArrayList<>(data.keySet());
    }

    /**
     * add a word to favorite list.
     *
     * @param favorite word.
     */
    public void addFavorite(String favorite) {
        writeFavorite(favorite);
    }

    /**
     * remove a word from favorite.
     *
     * @param favorite word.
     */
    public void removeFavorite(String favorite) {
        if (data.containsKey(favorite)) {
            try {
                File inputFile = new File(FAV_PATH);
                File tempFile = new File("temp.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    // trim newline when comparing with lineToRemove
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.equals(favorite)) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                tempFile.renameTo(inputFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
