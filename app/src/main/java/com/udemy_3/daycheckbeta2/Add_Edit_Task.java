package com.udemy_3.daycheckbeta2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.time.LocalTime;

public class Add_Edit_Task extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_ID = " com.udemy_3.daycheckbeta2.EXTRA_ID";
    public static final String EXTRA_COLOR= " com.udemy_3.daycheckbeta2.EXTRA_COLOR";
    public static final String EXTRA_TASK_NAME= " com.udemy_3.daycheckbeta2.EXTRA_TASK_NAME";
    public static final String EXTRA_TASK_TIME= "com.udemy_3.daycheckbeta2.EXTRA_TASK_TIME";
    private EditText editTaskname;
    public String COLOR="";

    private Button save_button;
    private ImageButton imageButtonRed;
    private ImageButton imageButtonYellow;
    private ImageButton imageButtonGreen;

    TaskAdapter taskAdapter_Color;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__task);
        editTaskname = findViewById(R.id.name_task);
        save_button = findViewById(R.id.save_Task);

        imageButtonRed = findViewById(R.id.redbtn);
        imageButtonGreen = findViewById(R.id.greenbtn);
        imageButtonYellow = findViewById(R.id.yellowbtn);

        imageButtonRed.setOnClickListener(this);
        imageButtonGreen.setOnClickListener(this);
        imageButtonYellow.setOnClickListener(this);

        save_button.setOnClickListener(this);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTaskname.setText(intent.getStringExtra(EXTRA_TASK_NAME));


        }else{
            setTitle("Add Note");
        }

    }






    private void save_Task() {
        String task_name = editTaskname.getText().toString().trim();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if(task_name.isEmpty()){
                Toast.makeText(Add_Edit_Task.this, "insert the name", Toast.LENGTH_LONG).show();
                return;
            }
        }
        LocalTime time = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            time = LocalTime.now();
        }
        String timeString =time.toString();

        Intent data = new Intent();
        data.putExtra(EXTRA_TASK_NAME, task_name);
        data.putExtra(EXTRA_TASK_TIME, timeString);

        if(COLOR==""){
            COLOR ="#8BA8C2";
        }
        data.putExtra(EXTRA_COLOR, COLOR);




        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID,id);
       }
        setResult(RESULT_OK, data);
        finish();
        
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.save_Task:
                save_Task();
                break;
                case R.id.greenbtn:
                COLOR = "#15FA2F";

                break;
            case R.id.redbtn:
                COLOR ="#FA3D15";
                break;
            case R.id.yellowbtn:
                COLOR ="#F6FA15";
            default:
                break;
        }
    }
}