package ca.herzing.lab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomList extends AppCompatActivity {

    //ArrayAdapter adapter;

    /*String[] names = {
            "Abigail", "Alexander", "Alexis", "Alyssa", "Andrew", "Anna", "Anthony", "Ashley",
            "Austin", "Ava", "Benjamin", "Brandon", "Brianna", "Caleb", "Chloe", "Christian",
            "Christopher", "Daniel", "David", "Destiny", "Dylan", "Elijah", "Elizabeth", "Emma",
            "Ethan", "Gabriel", "Grace", "Hailey", "Hannah", "Isabella", "James", "Jasmine",
            "Jennifer", "Jessica", "John", "Jonathan", "Jose", "Joseph", "Joshua", "Julia",
            "Justin", "Kaitlyn", "Katherine", "Kayla", "Kevin", "Lauren", "Logan", "Madison",
            "Matthew", "Megan", "Mia", "Michael", "Morgan", "Mushu", "Natalie", "Nathan", "Nicholas",
            "Noah", "Olivia", "Rachel", "Robert", "Ryan", "Samantha", "Samuel", "Sarah",
            "Sophia", "Sydney", "Taylor", "Thomas", "Tyler", "Victoria", "William", "Zachary"
    };


   CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the array Adapter create an Array list of contacts
        ArrayList<TaskBean> contactsArr = new ArrayList<>();
        adapter = new CustomListAdapter(this,contactsArr);

       // adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, names);
       /* ListView listView = findViewById(R.id.names); // (ListView)
        listView.setAdapter(adapter);*/

        /*ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Toast.makeText(this,"hi there",Toast.LENGTH_LONG).show();

        //create a couple of contacts
        adapter.add(new TaskBean("title1", "description1","24-02-2019",10));
        adapter.add(new TaskBean("title2", "description2","24-02-2019",15));




*/


        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                contact c = adapter.getItem(i);
                Toast.makeText(getApplicationContext(),"hi there"+ i, Toast.LENGTH_LONG).show();
            }
        });*/

        /*listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                adapter.remove(adapter.getItem(i));
                adapter.notifyDataSetChanged();
                return false;
            }
        });*/

    //}
}
