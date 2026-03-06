/**
 * Represents a Deadline task, which has a description and a due date.
 * Inherits from the Task class.
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public Deadline (String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * Format: [D][statusIcon] description (by: dueDate)
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
