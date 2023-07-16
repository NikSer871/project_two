import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Manager {
    static HashMap<Integer, Task> dataTasks = new HashMap<>();
    static HashMap<Integer, Epic> dataEpics = new HashMap<>();
    static HashMap<Integer, Subtask> dataSubTasks = new HashMap<>();
    static int idTasks = 0;
    static int idEpics = 0;
    static int idSubTasks = 0;

    public static void giveListOfTasks(int a) {
        System.out.println("|  id          name    ");
        switch (a) {
            case 1:
                for (Map.Entry<Integer, Task> str : dataTasks.entrySet()) {
                    System.out.print("| ");
                    System.out.print(str.getKey() + " " + str.getValue().name);
                    System.out.println(" |");
                }
                break;
            case 2:
                for (Map.Entry<Integer, Epic> str : dataEpics.entrySet()) {
                    System.out.print("| ");
                    System.out.print(str.getKey() + " " + str.getValue().name);
                    System.out.println(" |");
                }
                break;
            case 3:
                for (Map.Entry<Integer, Subtask> str : dataSubTasks.entrySet()) {
                    System.out.print("| ");
                    System.out.print(str.getKey() + " " + str.getValue().name);
                    System.out.println(" |");
                }
                break;
        }
    }

    public static void deleteTasks(int a) {
        switch (a) {
            case 1:
                dataTasks.clear();
            case 2:
                dataSubTasks.clear();
                dataEpics.clear();
            case 3:
                dataSubTasks.clear();
                dataEpics.clear();

        }
    }

    public static String getTask(int a, int id) {
        String b = null;
        switch (a) {
            case 1 -> {
                if (!dataTasks.containsKey(id)) {
                    System.out.println("Нет задачи с таким идентивикатором!!!");
                    return null;
                }
                b = dataTasks.get(id).name;
            }
            case 2 -> {
                if (!dataEpics.containsKey(id)) {
                    System.out.println("Нет задачи с таким идентивикатором!!!");
                    return null;
                }
                b = dataEpics.get(id).name;
            }
            case 3 -> {
                if (!dataSubTasks.containsKey(id)) {
                    System.out.println("Нет задачи с таким идентивикатором!!!");
                    return null;
                }
                b = dataSubTasks.get(id).name;
            }
        }
        return b;
    }

    public static void createTask(Task a) {
        a.status = "NEW";
        a.id = idTasks;
        dataTasks.put(idTasks++, a);
    }

    public static void createEpic(Epic a) {
        a.status = "NEW";
        a.id = idEpics;
        dataEpics.put(idEpics++, a);
    }

    public static void createSubTask(Subtask a, int id) {
        a.status = "NEW";
        a.id = idSubTasks;
        dataEpics.get(id).subtasks.add(a);
        a.epic = dataEpics.get(id);
        dataSubTasks.put(idSubTasks++, a);
        System.out.println(a.epic.subtasks.size());

    }

    public static void updateTask(Task a) {
        a.action = "DONE";
        dataTasks.put(a.id, a);
    }

    public static void updateEpic(Epic a) {
        if (a.subtasks.size() == 0) {
            a.status = "DONE";
            dataEpics.put(a.id, a);
            System.out.println(dataEpics.get(a.id).status);
        } else {
            a.status = "IN PROGRESS";
            dataEpics.put(a.id, a);
        }

    }

    public static void updateSubTask(Subtask a) {
        a.action = "DONE";
        dataSubTasks.put(a.id, a);
        a.epic.subtasks.remove(a);
    }

    public static void deleteTask(int a, int id) {
        switch (a) {
            case 1 -> {
                if (!dataTasks.containsKey(id)) {
                    System.out.println("Нет задачи с таким идентивикатором!!!");
                    break;
                }
                dataTasks.remove(id);
            }
            case 2 -> {
                if (!dataEpics.containsKey(id)) {
                    System.out.println("Нет задачи с таким идентивикатором!!!");
                    break;
                }
                for (Subtask s: dataEpics.get(id).subtasks
                     ) {
                    dataSubTasks.remove(s.id);
                }
                dataEpics.get(id).subtasks.clear();
                dataEpics.remove(id);
            }
            case 3 -> {
                if (!dataSubTasks.containsKey(id)) {
                    System.out.println("Нет задачи с таким идентивикатором!!!");
                    break;
                }
                dataSubTasks.get(id).epic.subtasks.remove(dataSubTasks.get(id));
                dataSubTasks.remove(id);
            }
        }
    }
    public static void text(){
        System.out.println("Please, pick one of the options");
        System.out.println("1 - GIVE A LIST WITH ALL OF THE TASKS");
        System.out.println("2 - DELETE ALL TYPE OF TASKS");
        System.out.println("3 - GIVE TASK WITH USING ID");
        System.out.println("4 - CREATION ONE TYPE OF TASK");
        System.out.println("5 - UPDATE");
        System.out.println("6 - DELETE TASK USING ID");
        System.out.println("7 - GIVE A LIST ALL OF THE SUBTASK CERTAIN EPIC");
    }
    public static void giveListSubTasks(Epic epic) {
        for (int i = 0; i < epic.subtasks.size(); i++) {
            System.out.println(epic.subtasks.get(i).name);
            System.out.println("+++++++++++++++++++");
        }
    }

    public static void menu() {
        String text;
        int a;
        int id;
        Scanner v = new Scanner(System.in);
        System.out.println("HELLO!!!");
        System.out.println("If you want to stay in menu, write \"y\" ");
        text = v.nextLine();
        while (text.equals("y")) {
            System.out.println("IF YOU WANT TO LEAVE WRITE 0");
            System.out.println("---------------------------------------");
            System.out.println("---------------------------------------");
            System.out.println("---------------------------------------");
            if(v.nextLine().equals("0")){
                System.exit(0);
            }
            text();
            switch (v.nextLine()) {
                case "1" -> {
                    System.out.println("Pick 1 - TASKS OR 2 - EPiCS OR 3 - SUBTASKS");
                    a = v.nextInt();
                    v.nextLine();
                    giveListOfTasks(a);
                }
                case "2" -> {
                    System.out.println("Pick 1 - TASKS OR 2 - EPiCS OR 3 - SUBTASKS");
                    a = v.nextInt();
                    v.nextLine();
                    System.out.println();
                    deleteTasks(a);
                }
                case "3" -> {
                    System.out.println("Pick 1 - TASKS OR 2 - EPiCS OR 3 - SUBTASKS");
                    a = v.nextInt();
                    v.nextLine();
                    giveListOfTasks(a);
                    System.out.println("PICK ID");
                    a = v.nextInt();
                    v.nextLine();
                    id = v.nextInt();
                    v.nextLine();
                    System.out.println(getTask(a, id));
                }
                case "4" -> {
                    String name;
                    String description;
                    String action;
                    System.out.println("Pick 1 - TASKS OR 2 - EPiCS OR 3 - SUBTASKS");
                    a = v.nextInt();
                    v.nextLine();
                    System.out.println("name :");
                    name = v.nextLine();
                    System.out.println("description: ");
                    description = v.nextLine();
                    System.out.println("action: ");
                    action = v.nextLine();
                    switch (a) {
                        case 1 -> createTask(new Task(name, description, action));
                        case 2 -> createEpic(new Epic(name, description, action));
                        case 3 -> {
                            System.out.println("PICK EPIC");
                            giveListOfTasks(2);
                            id = v.nextInt();
                            v.nextLine();
                            createSubTask(new Subtask(name, description, action), id);
                        }
                    }
                }
                case "5" -> {
                    System.out.println("Pick 1 - TASKS OR 2 - EPiCS OR 3 - SUBTASKS");
                    a = v.nextInt();
                    v.nextLine();
                    giveListOfTasks(a);
                    System.out.println("PICK ID");
                    id = v.nextInt();
                    v.nextLine();
                    switch (a) {
                        case 1 -> updateTask(dataTasks.get(id));
                        case 2 -> updateEpic(dataEpics.get(id));
                        case 3 -> {
                            updateSubTask(dataSubTasks.get(id));
                            updateEpic(dataSubTasks.get(id).epic);
                        }
                    }
                }
                case "6" -> {
                    System.out.println("Pick 1 - TASKS OR 2 - EPiCS OR 3 - SUBTASKS");
                    a = v.nextInt();
                    v.nextLine();
                    giveListOfTasks(a);
                    System.out.println("PICK ID");
                    id = v.nextInt();
                    v.nextLine();
                    deleteTask(a, id);
                }
                case "7" -> {
                    System.out.println("PICK EPIC");
                    giveListOfTasks(2);
                    id = v.nextInt();
                    v.nextLine();
                    giveListSubTasks(dataEpics.get(id));
                }
            }

        }
        System.exit(0);

    }

    public static void main(String[] args) {
        menu();
    }
}
