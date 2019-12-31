package ca.herzing.lab2;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
    //3:35 pm 2/20/2019
public class MainActivity extends AppCompatActivity {

    //variable declarations
    RadioButton male;
    RadioButton female;

    RadioButton male1;
    RadioButton female1;
    float finalHeight;
    float finalWeight;

    //main activity variables
    EditText txt_name;
    EditText txt_age;
    EditText txt_gender;
    EditText txt_category;
    EditText txt_risk;
    ImageView image_swap;

    EditText txtName;
    EditText txtAge;
    EditText txt_agePI;
    EditText txtCategory;
    EditText txtRisk;
    EditText txtGender;
    EditText txtFeet;
    EditText txtInches;
    EditText txtWeight;


    ImageView btnBmi;
    Button btnBMIResults;

    ImageView enter;
    ImageView reset;
    Dialog customDialog;
    EditText txt_namePI;

    String name;
    int age;
    int sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        txt_name = findViewById(R.id.txt_name);
        txt_age = findViewById(R.id.txt_age);
        txt_gender = findViewById(R.id.txt_gender);
        txt_category = findViewById(R.id.txt_category);
        txt_risk = findViewById(R.id.txt_risk);
        image_swap = findViewById(R.id.image_swap);



        txtName = findViewById(R.id.txt_name);
        //txt_agePI = findViewById(R.id.txt_agePI);

        //variable instantiations
        txtCategory = findViewById(R.id.txt_category);
        txtRisk = findViewById(R.id.txt_risk);
        txtFeet = findViewById(R.id.txt_feet);
        txtInches = findViewById(R.id.txt_inches);
        txtWeight = findViewById(R.id.txt_weight);

        //male1  = findViewById(R.id.rb_male);
       // female1 = findViewById(R.id.rb_female);
        //txtName = findViewById(R.id.txt_name);
        txtAge = findViewById(R.id.txt_age);
        txtGender = findViewById(R.id.txt_gender);

        btnBmi = findViewById(R.id.btn_bmi);
        btnBMIResults = findViewById(R.id.btn_BMIResults);

        enter = findViewById(R.id.enter);
        reset = findViewById(R.id.btn_reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });

    }



    public void openPI(View v){

        // creating a dialog
        customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.personal_info);
        customDialog.setTitle("BMI Program");

        // creating variables for texts, radio/buttons, image views
        EditText namePI = customDialog.findViewById(R.id.txt_namePI);
        EditText agePI = customDialog.findViewById(R.id.txt_agePI);
        male = customDialog.findViewById(R.id.rb_male);
        female = customDialog.findViewById(R.id.rb_female);
        ImageView enter = customDialog.findViewById(R.id.enter);
        ImageView cancel = customDialog.findViewById(R.id.cancel);
        txt_namePI = customDialog.findViewById(R.id.txt_namePI);



        // enter button (Save the users name, age and gender)
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setAgeAndSex();
                customDialog.dismiss();


            }
        });

        // cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"cancelled",Toast.LENGTH_SHORT).show();

            customDialog.dismiss();
            }
        });
        customDialog.show();




    }



    public void setAgeAndSex(){

        name = (((EditText)customDialog.findViewById(R.id.txt_namePI))).getText().toString();

        String ageCheck = ((EditText)customDialog.findViewById(R.id.txt_agePI)).getText().toString();


        male1 = findViewById(R.id.rb_male);
        female1 = findViewById(R.id.rb_female);

        System.out.println("Testing: " + ageCheck);
        if(ageCheck == null || ageCheck.equals("") || name == null || name.equals("") || (!male.isChecked() && !female.isChecked())){
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();

        }else{
            age = Integer.parseInt(ageCheck);

            male1 = customDialog.findViewById(R.id.rb_male);
            female1 = customDialog.findViewById(R.id.rb_female);

            txt_name.setText(name);
            txt_age.setText(age+"");

            if(male1.isChecked()){
                sex = 1;
                txt_gender.setText("Male");
            }else if(female1.isChecked()){
                sex = 0;
                txt_gender.setText("Female");


            }
        }




        //age = Integer.parseInt(((EditText)customDialog.findViewById(R.id.txt_agePI)).getText().toString());




        //System.out.println("TEST!!!!! :" + name + age + male1.isSelected() + female1.isSelected());


    }

    //function to calculate the BMI
    public float calculateBMI(float weight, float height){
        float tmp = 703.0f;
        float result =  (weight/(height*height)) * tmp;
        return result;
    }
    //setting a unique color based on persons body fat
    public void setColorBasedOnFat(float resultBMI){
        if(resultBMI <= 18.5){
            btnBMIResults.setBackgroundColor(Color.parseColor("#FFFF00"));
        }else if(resultBMI > 18.5 && resultBMI < 25.0){
            btnBMIResults.setBackgroundColor(Color.parseColor("#008000"));
        }else if(resultBMI >= 25){
            btnBMIResults.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }

    //function to calculate BODY FAT
    /*public void calculateBodyFat(){
        float tmp;

        tmp = calculateBMI(finalWeight,finalHeight);

        float bodyFat = (1.20f * tmp) + (0.23f * Float.parseFloat(txtAge.getText().toString())) - (10.8f * sex) - 54f;

        //body fat = (1.20 * BMI) + (0.23 * age) - (10.8 * gender) - 54
    }*/

    //calculate body fat action listener
    public void findBodyFat(View v){
        String weight =  txtWeight.getText().toString();
        String feet = txtFeet.getText().toString();
        String inch = txtInches.getText().toString();

        //CASTING both feet and inches into floating point #'s
        float tmp1 = Float.parseFloat(feet);
        float tmp2 = Float.parseFloat(inch);
        finalWeight = Float.parseFloat(weight);

        // adding feet and inches
        float totalFeet = tmp1 * 12f;

        //final height in inches
        finalHeight = totalFeet + tmp2;
        float resultBMI = calculateBMI(finalWeight,finalHeight);
        float resultBodyFat = (1.20f * resultBMI) + (0.23f * 17) - (10.8f * 1) - 5.4f; //change(Hardcoded)

        // float resultBodyFat = (1.20f * resultBMI) + (0.23f * Float.parseFloat(txtAge.getText().toString())) - (10.8f * sex) - 54f;

        btnBMIResults.setText("" + resultBodyFat);
    }


    //calculate BMI action listener
    public void findBmi(View v){

       String weight =  txtWeight.getText().toString();
       String feet = txtFeet.getText().toString();
       String inch = txtInches.getText().toString();

       //CASTING both feet and inches into floating point #'s
       float tmp1 = Float.parseFloat(feet);
       float tmp2 = Float.parseFloat(inch);

       float finalWeight = Float.parseFloat(weight);

       // adding feet and inches
       float totalFeet = tmp1 * 12f;

       //final height in inches
       float finalHeight = totalFeet + tmp2;

       float resultBMI = calculateBMI(finalWeight,finalHeight);

       btnBMIResults.setText(""+resultBMI);


           if (resultBMI <= 15) {
               txt_category.setText("very severely underweight");
               setColorBasedOnFat(resultBMI);
               if(male1.isChecked()){
                   System.out.println("male");
                   image_swap.setImageResource(R.drawable.mimg1);
               }else if(female1.isChecked()){
                   System.out.println("female");
                   image_swap.setImageResource(R.drawable.img1);
               }
//---------------------------------------------------------------------// 4:31PM  2/22/2019
           } else if (resultBMI > 15 && resultBMI < 16) {
               txt_category.setText("severely underweight");
               setColorBasedOnFat(resultBMI);

           } else if (resultBMI >= 16 && resultBMI < 18.5f) {
               txt_category.setText("underweight");
               setColorBasedOnFat(resultBMI);

           } else if (resultBMI >= 18.5 && resultBMI < 25) {
               txt_category.setText("normal (Healthy)");
               setColorBasedOnFat(resultBMI);

           } else if (resultBMI >= 25 && resultBMI < 30) {
               txt_category.setText("Overweight");
               setColorBasedOnFat(resultBMI);

           } else if (resultBMI >= 30 && resultBMI < 35) {
               txt_category.setText("Obese class I (moderately obese)");
               setColorBasedOnFat(resultBMI);

           } else if (resultBMI >= 35 && resultBMI < 40) {
               txt_category.setText("Obese class II (severely obese)");
               setColorBasedOnFat(resultBMI);

           } else if (resultBMI > 40){

               txt_category.setText("Obese class III (very severely obese)");

           }else{
               txt_category.setText("");
           }


           if (resultBMI >= 27.5) {
            txtRisk.setText("High risk of developing heart disease, high blood pressure, stroke, diabetes");

           } else if (resultBMI >= 23.0 && resultBMI <=27.4) {
               txtRisk.setText("Moderate risk of developing heart disease, high blood pressure, stroke, diabetes");

           } else if (resultBMI >= 18.5 && resultBMI <=22.9) {
               txtRisk.setText("Low risk (Healthy range)");

           } else if (resultBMI <= 18.4 && resultBMI > 0) {
               txtRisk.setText("Risk of developing problems such as nutrional deficiency and osteoporosis");

           }else{
               txtRisk.setText("");
           }


       //Toast.makeText(this,resultBMI + "", Toast.LENGTH_LONG).show();


    }

    public void resetFields(){

        txtFeet.setText("");
        txtInches.setText("");
        txtWeight.setText("");
        txtRisk.setText("");
        txtGender.setText("");
        txtCategory.setText("");
        txtAge.setText("");
        txtName.setText("");

        btnBMIResults.setText("");

    }




}
