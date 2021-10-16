package dictionary.history;

import java.util.List;

import dictionary.file.Type;
import dictionary.manager.word.Word;

public class History {
    private static Stack ev_history = new Stack();
    private static Stack ve_history = new Stack();

    public static void addHistory(String type, Word word) {
        if (type.equals(Type.EV)) {
            ev_history.push(word);
        } else {
            ve_history.push(word);
        }
    }

    public static void removeHistory(String type, Word word) {
        if (type.equals(Type.EV)) {
            ev_history.stack.removeIf(filter -> (filter == word));
            System.out.println(ev_history.toStringList());
        } else {
            ve_history.stack.removeIf(filter -> (filter == word));
            System.out.println(ve_history.toStringList());
        }
    }

    public static void clearHistory(String type) {
        if (type.equals(Type.EV)) {
            ev_history.clear();
        } else {
            ve_history.clear();
        }
    }

    public static List<String> getAllHistory(String type) {
        if (type.equals(Type.EV)) {
            return ev_history.toStringList();
        } else {
            return ve_history.toStringList();
        }
    }

    public static Word getHistoryAt(String type, int index) {
        if (type.equals(Type.EV)) {
            return ev_history.stack.get(index);
        } else {
            return ve_history.stack.get(index);
        }
    }
}