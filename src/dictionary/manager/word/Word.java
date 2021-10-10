package dictionary.manager.word;

public class Word {
    private String word_target;
    private String word_explain;

    /** 
     * Empty constructor.
     */
    public Word() {
        word_target = "";
        word_explain = "";
    }

    /**
     * Constructor with 2 parameters.
     * @param word_target word target.
     * @param word_explain word explain.
     */
    public Word(String word_target, String word_explain){
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    /**
     * word_explain getter.
     * @return word_explain.
     */
    public String getWord_explain() {
        return word_explain;
    }

    /**
     * word_target getter.
     * @return word_target.
     */
    public String getWord_target() {
        return word_target;
    }

    /**
     * word_explain setter.
     * @param word_explain word_explain.
     */
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    /**
     * word_target setter.
     * @param word_target word_target.
     */
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

}
