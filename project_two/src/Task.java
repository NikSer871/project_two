public class Task {
    String name;
    String description;

    String status;
    String action;

    Epic epic;
    int id;
    Task(String name, String description, String action) {
        this.name = name;
        this.description = description;
        this.action = action;
    }
}


