package dictionary.history;

import java.util.ArrayList;
import java.util.List;

import dictionary.manager.word.Word;

class Stack {
    protected List<Word> stack;

    protected Stack() {
        stack = new ArrayList<Word>();
    }

    protected void push(Word value) {
        stack.add(0, value);
    }

    protected void clear() {
        stack.clear();
    }

    protected List<String> toStringList() {
        List<String> r = new ArrayList<>();
        for (Word i : stack) {
            r.add(i.getWord_target());
        }
        return r;
    }
}