package ca.herzing.lab3;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Environment;
import android.renderscript.RenderScript;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ListView listView;

    //////////txt_title

    static CustomListAdapter adapter;
    ImageView btn_newTask;
    ImageView add;
    ImageView cancel;
    EditText txt_title;
    EditText txt_description;
    RadioGroup radioGroup;

    ImageView imageView2;
    TextView textView;
    ImageView btn_deleteBPc;
    ImageView btn_cancelBPc;

    DBManager dbManager;

    private static ArrayList<TaskBean> contactsArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(getApplicationContext());

        add = findViewById(R.id.btn_addNT);
        cancel = findViewById(R.id.btn_cancelNT);
        txt_title = findViewById(R.id.txt_title);
        txt_description = findViewById(R.id.txt_description);
        btn_newTask = (ImageView) findViewById(R.id.btn_newTask);
        // radioGroup = findViewById(R.id.radioGroup);
        listView = findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskBean taskBean = contactsArr.get(position);
                ((CheckBox) view.findViewById(R.id.task_done)).setChecked(!taskBean.isDone());
                taskBean.setDone(!taskBean.isDone());
                contactsArr.set(position, taskBean);
                adapter.notifyDataSetChanged();
            }
        });

        //listener for deletion of task within list/////////////////////////////////////////////////
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {


                checkDialog(adapter.getItem(i));


                return false;
            }
        });


        contactsArr = new ArrayList<>();

        adapter = new CustomListAdapter(this, contactsArr);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    protected void onPause() {
        super.onPause();
        dbManager.purge();

        for (int i = 0; i < contactsArr.size(); i++) {
            TaskBean task = contactsArr.get(i);

            dbManager.addTask(task);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.clear();
        adapter.notifyDataSetChanged();

        for (TaskBean task : dbManager.getAllTasks()) {

            contactsArr.add(task);
            adapter.notifyDataSetChanged();
        }
        adapter.setSortBy(0);
    }

    /*public void newActivity(){
        Intent intent = new Intent(this, NewTask.class);
        startActivity(intent);
    }*/

    //function to add new tasks to list/////////////////////////////////////////////////////////////
    public static void addToList(String name, String description, int priority) {
        contactsArr.add(new TaskBean(name, description, new SimpleDateFormat("dd-MM-yyyy").format(new Date()), priority));
        adapter.notifyDataSetChanged();
        Log.d(TAG, "addToList: added.");
    }


    //custom dialog pop up//////////////////////////////////////////////////////////////////////////
    public void CustomDialog(View view) {

        final Dialog cDialog = new Dialog(this);
        cDialog.setContentView(R.layout.new_task);

        final EditText txt_title = cDialog.findViewById(R.id.txt_title);
        final EditText txt_description = cDialog.findViewById(R.id.txt_description);
        ImageView add = cDialog.findViewById(R.id.btn_addNT);
        ImageView cancel = cDialog.findViewById(R.id.btn_cancelNT);
        ImageView btn_newTask = cDialog.findViewById(R.id.btn_newTask);
        final RadioGroup radioGroup = cDialog.findViewById(R.id.radioGroup);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int priority = -1;
                if (txt_title.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Task field is empty", Toast.LENGTH_SHORT).show();
                } else if (txt_description.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Description field is empty", Toast.LENGTH_SHORT).show();


                } else {
                    switch (radioGroup.getCheckedRadioButtonId()) {
                        case R.id.rb_high:
                            priority = 0;
                            break;
                        case R.id.rb_medium:
                            priority = 1;
                            break;
                        case R.id.rb_low:
                            priority = 2;
                            break;
                    }

                    dbManager.addTask(new TaskBean(txt_title.getText().toString(), txt_description.getText().toString(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), priority));
                    addToList(txt_title.getText().toString(), txt_description.getText().toString(), priority);
                    Toast.makeText(
                            getApplicationContext(),
                            "Added",
                            Toast.LENGTH_SHORT).show();

                    cDialog.dismiss();

                }


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        getApplicationContext(),
                        "Canceled",
                        Toast.LENGTH_SHORT).show();

                cDialog.dismiss();

            }
        });


        cDialog.show();
    }


    //function to delete after long click listener//////////////////////////////////////////////////
    public void checkDialog(final TaskBean task) {
        final Dialog cDialog = new Dialog(this);
        cDialog.setContentView(R.layout.buy_pc);

        ImageView imageView2 = cDialog.findViewById(R.id.image_colorBPc);
        TextView textView = cDialog.findViewById(R.id.textView);
        ImageView btn_deleteBPc = cDialog.findViewById(R.id.btn_deleteBPc);
        ImageView btn_cancelBPc = cDialog.findViewById(R.id.btn_cancelBPc);

        textView.setText(task.getTaskDescription());
        Drawable priorityDrawable = null;
        switch (task.getPriority()) {
            case 0:
                priorityDrawable = getDrawable(R.drawable.squarered);
                break;
            case 1:
                priorityDrawable = getDrawable(R.drawable.squareyellow);
                break;
            case 2:
                priorityDrawable = getDrawable(R.drawable.squaregreen);
                break;
        }
        imageView2.setImageDrawable(priorityDrawable);

        Toast.makeText(this, "this is a title", Toast.LENGTH_SHORT).show();
        btn_deleteBPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactsArr.remove(task);
                dbManager.deleteTask(contactsArr.indexOf(task));
                adapter.notifyDataSetChanged();
                Toast.makeText(
                        getApplicationContext(),
                        "DELETED!",
                        Toast.LENGTH_SHORT).show();

                cDialog.dismiss();

            }
        });


        btn_cancelBPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //contactsArr.remove(task);
               // dbManager.deleteTask(contactsArr.indexOf(task));
                //adapter.notifyDataSetChanged();
                Toast.makeText(
                        getApplicationContext(),
                        "CANCELED!",
                        Toast.LENGTH_SHORT).show();

                cDialog.dismiss();

            }
        });


        cDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sort_completed:
                adapter.setSortBy(1);
                adapter.notifyDataSetChanged();
                break;
            case R.id.action_sort_priority:
                adapter.setSortBy(0);
                adapter.notifyDataSetChanged();
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}
