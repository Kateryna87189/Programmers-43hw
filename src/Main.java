import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
a )  Дан список Programmer(String name, String city, List<Task>  tasks).
Каждый программист  имеет список задач
Task (int Number, String description, String status, int daysInProcessing) .
Сформировать лист из всех задач.

b)  Дан список Programmer(String name, String city, List<Task>  tasks).
Каждый программист  имеет список задач
Task (int Number, String description, String status, int daysInProcessing) .
Сформировать лист из всех задач, которые относятся к программистам из Берлина, не завершены
(т.е. имеют статус, отличный от «done») и висят в обработке более 5 дней.
 */
public class Main {
    public static void main(String[] args) {
        List<Programmer> programmers = List.of(
                new Programmer("Максим ", "Берлін", List.of(
                        new Task(1, "Виправити помилку у функції входу", "in process", 10),
                        new Task(2, "Розробити новий модуль", "done", 7),
                        new Task(3, "Написати документацію", "not started", 6)
                )),
                new Programmer("Оксана", "Берлін", List.of(
                        new Task(4, "Створити новий інтерфейс користувача", "not started", 15),
                        new Task(5, "Провести тестування системи", "in process", 3)

                )),
                new Programmer("Андрій", "Жмеринка", List.of(
                        new Task(6, "Оптимізувати базу данних", "in process", 12),
                        new Task(7, "Оновити сервіс", "done", 2)

                ))
        );
        //Сформировать лист из всех задач.
        List<Task> allTask = programmers.stream()
                .flatMap(p -> p.getTasks().stream())
                .collect(Collectors.toList());
        System.out.println("------------Усі задачі-------------------------");
        allTask.forEach(t -> System.out.println(t.getDescription()));

        //Сформировать лист из всех задач, которые относятся к программистам из Берлина, не завершены
        Map<String, List<Task>> taskByProgrammer = programmers.stream()
                .filter(p -> p.getCity().equalsIgnoreCase("Берлін"))
                .collect(Collectors.toMap(
                        Programmer::getName,
                        p -> p.getTasks().stream()
                                .filter(t -> !t.getStatus().equalsIgnoreCase("done"))
                                .filter(t -> t.getDaysInProcessing() > 5)
                                .collect(Collectors.toList())
                ));
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Задачі з Берліна що не завершені та в обробці більше 5 днів: ");
        taskByProgrammer.forEach((name, tasks) -> {
            System.out.println("прогаміст: " + name);
            tasks.forEach(t -> System.out.println(" - " + t.getDescription()));
        });


    }
}