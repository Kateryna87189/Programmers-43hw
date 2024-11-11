import java.util.List;
import java.util.Objects;

/*
String name, String city, List<Task>  tasks
 */
public class Programmer {
    private String name;
    private String city;
    private List<Task> tasks;

    public Programmer(String name, String city, List<Task> tasks) {
        this.name = name;
        this.city = city;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s\n",name, city, tasks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Programmer that = (Programmer) o;
        return Objects.equals(name, that.name) && Objects.equals(city, that.city) && Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(city);
        result = 31 * result + Objects.hashCode(tasks);
        return result;
    }
}
