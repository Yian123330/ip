/**
 * Represents an Event task, which has a description, start time, and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     * Format: [E][statusIcon] description (from: startTime to: endTime)
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
