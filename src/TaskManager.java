import java.util.*;

// Task class implementing Comparable to prioritize tasks
class Task implements Comparable<Task> {
    private String taskName;
    private int priority;
    private int duration;

    public Task(String taskName, int priority, int duration) {
        this.taskName = taskName;
        this.priority = priority;
        this.duration = duration;
    }

    public String getTaskName() {
        return taskName;
    }
    public int getPriority() {
        return priority;
    }
    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority); // Higher priority first
        }
        return Integer.compare(this.duration, other.duration); // Shorter duration first
    }

    @Override
    public String toString() {
        return "[Priority " + priority + "] " + taskName + " (Duration: " + duration + " mins)";
    }
}

// TaskScheduler class to manage tasks
class TaskScheduler {
    private PriorityQueue<Task> taskQueue = new PriorityQueue<>();
    private Queue<Task> pendingQueue = new LinkedList<>();

    public void addTask(Task task) {
        taskQueue.add(task);
    }

    public void processTask() {
        if (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            System.out.println("Processing Task: " + task);
        } else if (!pendingQueue.isEmpty()) {
            Task task = pendingQueue.poll();
            System.out.println("Processing Task: " + task);
        } else {
            System.out.println("No tasks available to process.");
        }
    }

    public void moveToPending(String taskName) {
        Iterator<Task> iterator = taskQueue.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getTaskName().equals(taskName)) {
                iterator.remove();
                pendingQueue.add(task);
                System.out.println("Delaying Task: " + taskName);
                return;
            }
        }
    }

    public void displayScheduledTasks() {
        System.out.println("Scheduled Tasks (sorted by priority):");
        if (taskQueue.isEmpty()) {
            System.out.println("(No priority tasks)");
        } else {
            int i = 1;
            for (Task task : taskQueue) {
                System.out.println(i + ". " + task);
                i++;
            }
        }
    }

    public void displayPendingTasks() {
        System.out.println("Pending Tasks (FIFO Order):");
        if (pendingQueue.isEmpty()) {
            System.out.println("(No pending tasks)");
        } else {
            int i = 1;
            for (Task task : pendingQueue) {
                System.out.println(i + ". " + task.getTaskName() + " (Priority: " + task.getPriority() + ", Duration: " + task.getDuration() + " mins)");
                i++;
            }
        }
    }
}

// Generic Methods
class Utility {
    public static <T> List<T> mergeListsAlternating(List<T> list1, List<T> list2) {
        List<T> mergedList = new ArrayList<>();
        int size = Math.max(list1.size(), list2.size());
        for (int i = 0; i < size; i++) {
            if (i < list1.size()) mergedList.add(list1.get(i));
            if (i < list2.size()) mergedList.add(list2.get(i));
        }
        return mergedList;
    }

    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

// Main class
public class TaskManager {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        // Adding tasks
        scheduler.addTask(new Task("Code Review", 3, 20));
        scheduler.addTask(new Task("System Update", 5, 45));
        scheduler.addTask(new Task("Database Backup", 2, 30));
        scheduler.addTask(new Task("Deploy New Feature", 5, 50));
        scheduler.addTask(new Task("Bug Fixing", 4, 25));

        System.out.println("\nTasks Added:");
        scheduler.displayScheduledTasks();

        // Process and delay tasks
        scheduler.processTask();
        scheduler.moveToPending("Code Review");
        scheduler.displayScheduledTasks();
        scheduler.displayPendingTasks();

        scheduler.moveToPending("Database Backup");
        scheduler.displayScheduledTasks();
        scheduler.displayPendingTasks();

        scheduler.processTask();
        scheduler.displayScheduledTasks();
        scheduler.displayPendingTasks();

        scheduler.processTask();
        scheduler.displayScheduledTasks();
        scheduler.displayPendingTasks();

        scheduler.processTask();
        scheduler.displayScheduledTasks();
        scheduler.displayPendingTasks();

        scheduler.processTask();
        scheduler.displayScheduledTasks();
        scheduler.displayPendingTasks();
    }
}
