import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> taskList = new ArrayList<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy hh:mm");
    private Scanner scanner;

    public void addTask() throws ParseException {
        scanner = new Scanner(System.in);
        String inputTitle;
        String inputDescription;
        String inputDate;

        System.out.println("Введите название запланированной задачи");
        inputTitle = scanner.nextLine();
        System.out.println("Введите описание");
        inputDescription = scanner.nextLine();
        System.out.println("Введите дату и время dd.M.yy hh:mm");
        inputDate = scanner.nextLine();

        taskList.add(new Task(inputTitle, inputDescription, sdf.parse(inputDate)));
    }


    public void removeTask(int number) {
        if (number-1 <= taskList.size()) {
            taskList.remove(number-1);
        } else {
            System.out.println("В списке всего " + taskList.size() + " задачи " + " введите правильный номер!");
        }
    }

    public void showAllTasks()  {
        if (taskList==null) {
            System.out.println("У вас нет запланированных задач, хотите запланировать?" + "\n" + "1 - да, 2 - нет");
            switch (scanner.nextInt()) {
                case 1:
                    try {
                        addTask();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    showMenu();
                    break;
            }
        } else {
            int taskCounter = 1;
            System.out.println("В настоящий момент у вас " + taskList.size() + " запланированных задач" + "\n");

                for (Task task : taskList) {
                    Date date = task.getDate();
                    String dateToString  = sdf.format(date);

                    System.out.println(taskCounter + "." + " " + dateToString + "\n" + task.getTitle() + "\n" + task.getDescription() + "\n" + "_______________________________");
                    taskCounter++;
                }
            }
        }

    public void sortByDate() {
        taskList = taskList.stream()
                .sorted(Comparator.comparingLong(Task::getMillis).reversed())
                .collect(Collectors.toList());
    }

    public void start() {
        scanner = new Scanner(System.in);
        boolean a = true;
        while (a) {
            showMenu();

            switch (scanner.nextInt()) {
                case 1:
                    showAllTasks();
                    break;
                case 2:
                    try {
                        addTask();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    System.out.println("Введите номер задачи для удаления");
                    removeTask(scanner.nextInt());
                    break;
                case 4:
                    sortByDate();
                    showAllTasks();
                    break;
                case 5:
                    System.out.println("Программа завершена");
                    a = false;
                    break;
                default:
                    showMenu();
            }
        }
    }
    public void showMenu(){
        String menu = "\n"+"1 - отобразить список всех задач" + "\n" +
                "2 - добавить задачу" + "\n" +
                "3 - удалить задачу" + "\n" +
                "4 - отсортировать задачи по времени" + "\n" +
                "5 - завершить программу";
        System.out.println(menu);

    }
}
