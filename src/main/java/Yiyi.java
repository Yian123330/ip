/**
 * Yiyi chatbot application that helps users manage their tasks.
 * Supports Todo, Deadline, and Event tasks with features to add, delete,
 * mark/unmark, find, and persist tasks to a file.
 */
public class Yiyi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Yiyi chatbot instance with the specified file path for storage.
     * Initializes the UI, storage, and loads existing tasks from the file.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Yiyi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (YiyiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Yiyi chatbot.
     * Displays welcome message, reads user commands, executes them,
     * and continues until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (YiyiException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the Yiyi chatbot application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Yiyi("./data/yiyi.txt").run();
    }
}