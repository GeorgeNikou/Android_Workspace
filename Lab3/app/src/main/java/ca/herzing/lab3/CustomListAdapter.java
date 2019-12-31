package ca.herzing.lab3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CustomListAdapter extends ArrayAdapter<TaskBean> {
    int sortBy = -1;
    ArrayList<TaskBean> tasks;

    public CustomListAdapter(Context context, ArrayList<TaskBean> objects) {
        super(context, 0, objects);

        this.tasks = objects;
    }

    public View getView(int position, View convetedView, ViewGroup parent){

        ///                                                                    ///
        //if the item list doesn't exists, create it. Else use an existing one.//
        ///                                                                    ///
        if(convetedView == null)
            convetedView = LayoutInflater.from(getContext()).inflate((R.layout.contact_view), parent,false);


        //get each element from the list view
        TextView task_title  = convetedView.findViewById(R.id.task_title);
        TextView task_description = convetedView.findViewById(R.id.task_description);
        EditText task_date = convetedView.findViewById(R.id.task_date);
        ImageView task_priority = convetedView.findViewById(R.id.task_priority);
        CheckBox task_done = convetedView.findViewById(R.id.task_done);

        //get the current selected contact from the arraylist
        TaskBean task = getItem(position);

        //assigns values to elements in the view

        //RED	#FF0000	RGB(255, 0, 0)
        //YELLOW	#FFFF00	RGB(255, 255, 0)
        //GREEN	#008000	RGB(0, 128, 0)
//        String colorHexa = "#ffffff";
//        if(task.getPriority() == 1){
//            colorHexa = "#008000";
//        }else if(task.getPriority() == 2){
//            colorHexa = "#FFFF00";
//        }else if(task.getPriority() == 3){
//            colorHexa = "#FF0000";
//        }

        Drawable priorityDrawable = null;
        switch (task.getPriority()) {
            case 0:
                priorityDrawable = getContext().getDrawable(R.drawable.squarered);
                break;
            case 1:
                priorityDrawable = getContext().getDrawable(R.drawable.squareyellow);
                break;
            case 2:
                priorityDrawable = getContext().getDrawable(R.drawable.squaregreen);
                break;
        }

        task_priority.setImageDrawable(priorityDrawable);//color not changing, need help
        task_title.setText(task.getTaskTitle());
        task_description.setText(task.getTaskDescription());
        task_date.setText(task.getDate());
        task_done.setChecked(task.isDone());

        //returns the list
        return convetedView;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public void notifyDataSetChanged() {

        switch (sortBy) {
            case 0:
                Collections.sort(tasks, TaskBean.taskPriorityComparator);
                break;
            case 1:
                Collections.sort(tasks, TaskBean.taskCompleteComparator);
                break;
        }

        super.notifyDataSetChanged();
    }
}
