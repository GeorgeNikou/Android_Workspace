package ca.herzing.lab3;

import java.util.Comparator;

public class TaskBean {

    String taskTitle;
    String taskDescription;
    String date;
    int priority;
    boolean done;

    /*public TaskBean(String taskTitle, String taskDescription, int priority){
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.priority = priority;
    }
*/
    public TaskBean(String taskTitle, String taskDescription, String date, int priority, boolean done) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.date = date;
        this.done = done;
        this.priority = priority;
    }

    public TaskBean(String taskTitle, String taskDescription, String date, int priority) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.date = date;
        this.done = false;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * The comparator for TaskBean Priority
     */
    public static Comparator<TaskBean> taskPriorityComparator = new Comparator<TaskBean>() {

        public int compare(TaskBean s1, TaskBean s2) {
            int tPriority1 = s1.getPriority();
            switch (tPriority1) {
                case R.drawable.squaregreen:
                    tPriority1 = 2;
                    break;
                case R.drawable.squareyellow:
                    tPriority1 = 1;
                    break;
                case R.drawable.squarered:
                    tPriority1 = 0;
                    break;
            }
            int tPriority2 = s2.getPriority();
            switch (tPriority2) {
                case R.drawable.squaregreen:
                    tPriority2 = 2;
                    break;
                case R.drawable.squareyellow:
                    tPriority2 = 1;
                    break;
                case R.drawable.squarered:
                    tPriority2 = 0;
                    break;
            }

            //ascending order
            return tPriority1 - tPriority2;
        }
    };


    /**
     * The The comparator for TaskBean Complete
     */
    public static Comparator<TaskBean> taskCompleteComparator = new Comparator<TaskBean>() {

        public int compare(TaskBean s1, TaskBean s2) {
            boolean taskComplete1 = s1.isDone();
            boolean taskComplete2 = s2.isDone();

            //ascending order
            return Boolean.compare(taskComplete2, taskComplete1);
        }
    };
}
