/**
 * Represents a Todo task, which is a simple task without any date/time.
 * Inherits from the Task class.
 */
public class Todo extends Task{

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * Format: [T][statusIcon] description
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
