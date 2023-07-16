import java.util.ArrayList;
import java.util.Arrays;

public class Epic extends Task {
    ArrayList<Subtask> subtasks;

    Epic(String name, String description, String action) {
        super(name, description, action);
        subtasks = new ArrayList<>();
    }
}
