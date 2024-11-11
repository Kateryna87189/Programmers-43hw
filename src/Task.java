import java.util.Objects;

/*
int Number, String description, String status, int daysInProcessing
 */
public class Task {
    private int number;
    private String description;
    private String status;
    private int daysInProcessing;

    public Task(int number, String description, String status, int daysInProcessing) {
        this.number = number;
        this.description = description;
        this.status = status;
        this.daysInProcessing = daysInProcessing;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getDaysInProcessing() {
        return daysInProcessing;
    }

    @Override
    public String toString() {
        return String.format("(%d) %s [%s] %d\n", number,description,status,daysInProcessing);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        return number == task.number && daysInProcessing == task.daysInProcessing && Objects.equals(description, task.description) && Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(status);
        result = 31 * result + daysInProcessing;
        return result;
    }
}
