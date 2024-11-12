import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
a )  Дан список Programmer(String name, String city, List<Task>  tasks).
Каждый программист  имеет список задач
Task (int Number, String description, String status, int daysInProcessing) .
Сформировать лист из всех задач.
b)
Сформировать лист из всех задач, которые относятся к программистам из Берлина, не завершены
(т.е. имеют статус, отличный от «done») и висят в обработке более 5 дней.

c) Сформировать Map<Boolean, Task> , где ключу true будут соответствовать задачи находящиеся в обработке
(поле daysInProcessing)  менее заданного количества дней, false - остальные.

d)Сформировать Map<Integer, Task> , где ключ сколько дней задача в обработке, значение - список задач.
*/
public class Main {
    public static void main(String[] args) {
        List<Programmer> programmers = List.of(
                new Programmer("Максим ", "Берлін", List.of(
                        new Task(1, "Виправити помилку у функції входу", "in process", 2),
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
        List<String> allTaskWithNames = programmers.stream()
                .flatMap(p->p.getTasks().stream()
                        .map(t->p.getName()+"-"+t.getDescription()))
                .collect(Collectors.toList());
        System.out.println("Задачі програмістів:\n");
        allTaskWithNames.forEach(System.out::println);

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
        System.out.println("Задачі з Берліна що не завершені та в обробці більше 5 днів:\n ");
        taskByProgrammer.forEach((name, tasks) -> {
            System.out.println("прогаміст: " + name);
            tasks.forEach(t -> System.out.println(" - " + t.getDescription()));
        });

        //c)
        int daysTreshold = 5;
        Map<Boolean, List<Task>> tasksByProcessingTime = programmers.stream()
                .flatMap(p->p.getTasks().stream())
                .collect(Collectors.partitioningBy(t->t.getDaysInProcessing()<daysTreshold));
        System.out.println("--------------------------------------------------------------");
        System.out.println("\nЗадачі, розподілені за кількістю днів в обробці:\n ");
        tasksByProcessingTime.forEach((isBelowTreshold, tasks)->
                tasks.forEach(t->
                        System.out.println((isBelowTreshold ? "<" + daysTreshold+ "day: ": ">== "+daysTreshold+"days)")+
                t.getDescription() + "(" + t.getDaysInProcessing() + " days)")));

        //d
        Map<Integer, List<Task>> taskByDaysInProcessing= programmers.stream()
                .flatMap(p->p.getTasks().stream())
                .collect(Collectors.groupingBy(Task::getDaysInProcessing));
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\nЗадачі, згруповані за кількістю днів в обробці:\n "+taskByDaysInProcessing);



    }
}