package ca.herzing.lab1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ColorSwitcher extends AppCompatActivity {


    RadioButton RB_red;
    RadioButton RB_blue;
    RadioButton RB_green;
    RadioGroup radioGroup;

    CheckBox CB_red;
    CheckBox CB_green;
    CheckBox CB_blue;

    ImageButton colorPal;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_switcher);


        RB_red = findViewById(R.id.RB_red);
        RB_blue = findViewById(R.id.RB_blue);
        RB_green = findViewById(R.id.RB_green);

        CB_red = findViewById(R.id.CB_red);
        CB_blue = findViewById(R.id.CB_blue);
        CB_green = findViewById(R.id.CB_green);

        colorPal = findViewById(R.id.colorPal);
        btnBack = findViewById(R.id.btn2_back);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {



                // checkedId is the RadioButton selected
                boolean red = CB_red.isChecked();
                boolean blue = CB_blue.isChecked();
                boolean green = CB_green.isChecked();

                if(checkedId == R.id.RB_blue){
                    if(red && !green){
                        colorPal.setBackgroundColor(Color.parseColor("#FF00FF"));
                    }else if(!red && green){
                        colorPal.setBackgroundColor(Color.parseColor("#00FFFF"));
                    }else if(red && green){
                        colorPal.setBackgroundColor(Color.parseColor("#000000"));
                    }else if(!red && !green){
                        colorPal.setBackgroundColor(Color.parseColor("#0000FF"));
                    }
                }else if(checkedId == R.id.RB_green){
                    if(red && !blue){
                        colorPal.setBackgroundColor(Color.parseColor("#FFFF00"));
                    }else if(!red && blue){
                        colorPal.setBackgroundColor(Color.parseColor("#00FFFF"));
                    }else if(red && blue){
                        colorPal.setBackgroundColor(Color.parseColor("#000000"));
                    }else if(!red && !blue){
                        colorPal.setBackgroundColor(Color.parseColor("#008000"));
                    }
                }else if(checkedId == R.id.RB_red){
                    if(green && !blue){
                        colorPal.setBackgroundColor(Color.parseColor("#FFFF00"));
                    }else if(!green && blue){
                        colorPal.setBackgroundColor(Color.parseColor("#FF00FF"));
                    }else if(green && blue){
                        colorPal.setBackgroundColor(Color.parseColor("#000000"));
                    }else if(!green && !blue){
                        colorPal.setBackgroundColor(Color.parseColor("#FF0000"));
                    }
                }else{
                    if(!red && !green && !blue){
                        colorPal.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }else if(!red && !green && blue){
                        colorPal.setBackgroundColor(Color.parseColor("#0000FF"));
                    }else if(!red && green && !blue){
                        colorPal.setBackgroundColor(Color.parseColor("#008000"));
                    }else if(red && !green && !blue){
                        colorPal.setBackgroundColor(Color.parseColor("#FF0000"));
                    }else if(!red && green && blue){
                        colorPal.setBackgroundColor(Color.parseColor("#00FFFF"));
                    }else if(red && !green && blue){
                        colorPal.setBackgroundColor(Color.parseColor("#FF00FF"));
                    }else if(red && green && !blue){
                        colorPal.setBackgroundColor(Color.parseColor("#FFFF00"));
                    }else if(red && green && blue){
                        colorPal.setBackgroundColor(Color.parseColor("#000000"));
                    }
                }
            }
        });

        CB_red.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
               boolean red = CB_red.isChecked();
               boolean blue = CB_blue.isChecked();
               boolean green = CB_green.isChecked();

               boolean red_RB = RB_red.isChecked();
               boolean blue_RB = RB_blue.isChecked();
               boolean green_RB = RB_green.isChecked();

               if(!(red || red_RB) && !(blue || blue_RB) && !(green || green_RB)){
                   colorPal.setBackgroundColor(Color.parseColor("#FFFFFF"));
               }else if(!(red || red_RB) && !(blue || blue_RB) && (green || green_RB)){
                   colorPal.setBackgroundColor(Color.parseColor("#008000"));
               }else if(!(red || red_RB) && (blue || blue_RB) && !(green || green_RB)){
                   colorPal.setBackgroundColor(Color.parseColor("#0000FF"));
               }else if((red || red_RB) && !(blue || blue_RB) && !(green || green_RB)){
                   colorPal.setBackgroundColor(Color.parseColor("#FF0000"));
               }else if(!(red || red_RB) && (blue || blue_RB) && (green || green_RB)){
                   colorPal.setBackgroundColor(Color.parseColor("#00FFFF"));
               }else if((red || red_RB) && !(blue || blue_RB) && (green || green_RB)){
                   colorPal.setBackgroundColor(Color.parseColor("#FFFF00"));
               }else if((red || red_RB) && (blue || blue_RB) && !(green || green_RB)){
                   colorPal.setBackgroundColor(Color.parseColor("#FF00FF"));
               }else if((red || red_RB) && (blue || blue_RB) && (green || green_RB)){
                   colorPal.setBackgroundColor(Color.parseColor("#000000"));
               }
           }
        });

        CB_green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                boolean red = CB_red.isChecked();
                boolean blue = CB_blue.isChecked();
                boolean green = CB_green.isChecked();

                boolean red_RB = RB_red.isChecked();
                boolean blue_RB = RB_blue.isChecked();
                boolean green_RB = RB_green.isChecked();

                if(!(red || red_RB) && !(blue || blue_RB) && !(green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }else if(!(red || red_RB) && !(blue || blue_RB) && (green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#008000"));
                }else if(!(red || red_RB) && (blue || blue_RB) && !(green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#0000FF"));
                }else if((red || red_RB) && !(blue || blue_RB) && !(green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#FF0000"));
                }else if(!(red || red_RB) && (blue || blue_RB) && (green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#00FFFF"));
                }else if((red || red_RB) && !(blue || blue_RB) && (green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#FFFF00"));
                }else if((red || red_RB) && (blue || blue_RB) && !(green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#FF00FF"));
                }else if((red || red_RB) && (blue || blue_RB) && (green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#000000"));
                }
            }
        });

        CB_blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                boolean red = CB_red.isChecked();
                boolean blue = CB_blue.isChecked();
                boolean green = CB_green.isChecked();

                boolean red_RB = RB_red.isChecked();
                boolean blue_RB = RB_blue.isChecked();
                boolean green_RB = RB_green.isChecked();

                if(!(red || red_RB) && !(blue || blue_RB) && !(green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }else if(!(red || red_RB) && !(blue || blue_RB) && (green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#008000"));
                }else if(!(red || red_RB) && (blue || blue_RB) && !(green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#0000FF"));
                }else if((red || red_RB) && !(blue || blue_RB) && !(green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#FF0000"));
                }else if(!(red || red_RB) && (blue || blue_RB) && (green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#00FFFF"));
                }else if((red || red_RB) && !(blue || blue_RB) && (green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#FFFF00"));
                }else if((red || red_RB) && (blue || blue_RB) && !(green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#FF00FF"));
                }else if((red || red_RB) && (blue || blue_RB) && (green || green_RB)){
                    colorPal.setBackgroundColor(Color.parseColor("#000000"));
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            back();
            }
        });


    }

    public void back(){
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }


}
