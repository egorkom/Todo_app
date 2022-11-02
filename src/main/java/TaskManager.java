import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {

    //12.02.2023 12:00
    //12.02.2021 12:00

//    1. 12.2.2023 12:00
//            1
//            1
//    _______________________________
//2. 12.2.2023 03:00
//        2
//        2
//    _______________________________
//3. 11.2.2023 05:00
//        3
//        3
//    _______________________________

    private List<Task> taskList = new ArrayList<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy hh:mm");
    private Scanner scanner;

    public void addTask() throws ParseException {
        scanner = new Scanner(System.in);
        System.out.println("Введите название запланированной задачи");
        String title = inputTitle(scanner.nextLine());
        System.out.println("Введите описание задачи");
        String description = scanner.nextLine();
        System.out.println("Введите дату и время dd.mm.yyyy hh:mm");
        Date date = inputDate(scanner.nextLine());

        taskList.add(new Task(title
                , description
                , date));

    }

    private Date inputDate(String inputString) {

        Date localDate = new Date();
        Date inputDate = null;

        try {
            inputDate = sdf.parse(inputString);

            if (inputDate.getTime() < localDate.getTime()) {
                System.out.println("Это все в прошлом, подумай о будущем");
                inputDate(scanner.nextLine());
            } else {
                return inputDate;
            }
        } catch (ParseException e) {
            System.out.println("Формат даты не верный, повтори ввод");
            inputDate(scanner.nextLine());
        }
        return inputDate;
    }

    private String inputTitle(String inputString) {
        if (inputString.isEmpty()) {
            System.out.println("Заголовок не может быть пустым, повторите ввод");
            inputTitle(scanner.nextLine());
        }
        return inputString;
    }


    public void removeTask() {
        if (taskList.isEmpty()||taskList.equals(null)){
            System.out.println("Список еще не создан или в нем нет задач для удаления");
        } else {
            System.out.println("Введите номер задачи для удаления");
            scanner = new Scanner(System.in);
            int taskNumber = scanner.nextInt();

            if (taskNumber-1 <= taskList.size()) {
                taskList.remove(taskNumber - 1);
            } else {
                System.out.println("В списке всего " + taskList.size() + " задачи " + " введите правильный номер!");
                removeTask();
            }
        }
    }

    public void showAllTasks()  {
        scanner = new Scanner(System.in);

        if (taskList.isEmpty()) {
            System.out.println("У вас нет запланированных задач, хотите запланировать?" + "\n" + "1 - да, 2 - нет");
            switch (scanner.nextInt()) {
                case 1:
                    try {
                        addTask();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
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
        if (!taskList.isEmpty()) {
            taskList.stream()
                    .sorted(Comparator.comparing(Task::getMillis).reversed())
                    .collect(Collectors.toList());
        } else {
            System.out.println("Сортировать нечего");
        }
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
                    removeTask();
                    break;
                case 4:
                    sortByDate();
                    break;
                case 5:
                    System.out.println("Программа завершена");
                    a = false;
                    break;
                default:
                    showMenu();
            }
        }
        scanner.close();
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
