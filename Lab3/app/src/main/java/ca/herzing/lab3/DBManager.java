package ca.herzing.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    //Database version, creates a new DB when changes
    private static final int DB_VERSION = 1;

    //Database Structure
    private static final String DB_NAME = "tasksManager";
    private static final String TABLE_NAME = "tasks";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_DESC = "description";
    private static final String COL_DATE = "date";
    private static final String COL_PRIORITY = "priority";
    private static final String COL_DONE = "done";

    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Method to create a database
     *
     * @param db
     */
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "";
        CREATE_TABLE += "CREATE TABLE " + TABLE_NAME + " ( ";
        CREATE_TABLE += COL_ID + " INTEGER PRIMARY KEY, ";
        CREATE_TABLE += COL_NAME + " TEXT, ";
        CREATE_TABLE += COL_DESC + " TEXT, ";
        CREATE_TABLE += COL_DATE + " TEXT, ";
        CREATE_TABLE += COL_PRIORITY + " INTEGER, ";
        CREATE_TABLE += COL_DONE + " TEXT ";
        CREATE_TABLE += " )";

        db.execSQL(CREATE_TABLE);
    }

    /**
     * Method to recreate a database
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    /**
     * Function to insert a contact into our database
     *
     * @param taskBean
     */
    public void addTask(TaskBean taskBean) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, taskBean.getTaskTitle());
        values.put(COL_DESC, taskBean.getTaskDescription());
        values.put(COL_DATE, taskBean.getDate());
        values.put(COL_PRIORITY, taskBean.getPriority());
        values.put(COL_DONE, String.valueOf(taskBean.isDone()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Function to get a single contact
     *
     * @param id
     * @return
     */
    public TaskBean getSingleTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        TaskBean taskBean = null;

        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{COL_NAME, COL_DESC, COL_DATE, COL_PRIORITY, COL_DONE},
                COL_ID + "=?",
                new String[]{id + ""},
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            taskBean = new TaskBean(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    Boolean.valueOf(cursor.getString(5))
            );
        }
        db.close();
        return taskBean;
    }

    /**
     * Function to get all the records from database
     *
     * @return
     */
    public List<TaskBean> getAllTasks() {
        List<TaskBean> tasks = new ArrayList<TaskBean>();
        TaskBean taskBean;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                taskBean = new TaskBean(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        Boolean.valueOf(cursor.getString(5))
                );
                tasks.add(taskBean);
            } while (cursor.moveToNext());
        }

        db.close();
        return tasks;
    }

    /**
     * Function to update a record in db
     *
     * @param taskBean
     */
    public void updateTask(int index, TaskBean taskBean) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, taskBean.getTaskTitle());
        values.put(COL_DESC, taskBean.getTaskDescription());
        values.put(COL_DATE, taskBean.getDate());
        values.put(COL_PRIORITY, taskBean.getPriority());
        values.put(COL_DONE, String.valueOf(taskBean.isDone()));

        db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(index)});
        db.close();
    }

    /**
     * Function to delete a record from database
     *
     * @param id
     */
    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + "= ?", new String[]{id + ""});
        db.close();
    }

    public void purge() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }

    /**
     * Function to count the total of contacts I have in database
     *
     * @return
     */
    public int getTotalTasks() {
        int total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        total = cursor.getCount();
        cursor.close();
        db.close();

        return total;
    }
}
