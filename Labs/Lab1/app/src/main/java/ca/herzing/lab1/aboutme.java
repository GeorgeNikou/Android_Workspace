package ca.herzing.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class aboutme extends AppCompatActivity {

    Button btn_theSecret;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);

        //action

        btn_theSecret = findViewById(R.id.btn_secret);
        btn_theSecret.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "I totally sniff butts on the regular!",
                        Toast.LENGTH_LONG).show();

            }
        });

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                back();
            }
        });


    }

    public void back(){
        Intent intent1 = new Intent(this, DashBoard.class);
        startActivity(intent1);
    }




}
