package ca.herzing.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class NewTask extends AppCompatActivity {

    private static final String TAG = "NewTask";
    ImageView btn_addNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);

        btn_addNT = findViewById(R.id.btn_addNT);



        btn_addNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txt_title = findViewById(R.id.txt_title);
                EditText txt_description = findViewById(R.id.txt_description);
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                int priority = -1;

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

                Log.d(TAG, "onClick: save");
                //MainActivity.addToList(txt_title.getText().toString(), txt_description.getText().toString(), priority);
                Bundle taskData = new Bundle();
                taskData.putString("title", txt_title.getText().toString());
                taskData.putString("description", txt_description.getText().toString());
                taskData.putInt("priority", priority);
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                main.putExtra("taskData", taskData);
                startActivity(main);
            }
        });

    }

}
