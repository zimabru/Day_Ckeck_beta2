package com.udemy_3.daycheckbeta2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    public static final String EXTRA_TASK_NAME= " com.udemy_3.daycheckbeta2.EXTRA_TASK_NAME";
    public static final String EXTRA_TASK_TIME= "com.udemy_3.daycheckbeta2.EXTRA_TASK_TIME";
    private EditText editTaskname;

    private Button save_button;
    private ImageButton imageButtonRed;
    private ImageButton imageButtonYellow;
    private ImageButton imageButtonGreen;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__task);
        editTaskname = findViewById(R.id.name_task);
        save_button = findViewById(R.id.save_Task);

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

        if(task_name.isEmpty()){
            Toast.makeText(Add_Edit_Task.this, "insert the name", Toast.LENGTH_LONG).show();
            return;
        }
        LocalTime time = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            time = LocalTime.now();
        }
        String timeString =time.toString();

        Intent data = new Intent();
        data.putExtra(EXTRA_TASK_NAME, task_name);
        data.putExtra(EXTRA_TASK_TIME, timeString);



        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_ID, -1);
        Log.d("bibi", "save_Task: "+ task_name+ " "+ timeString +" "+id );
        if(id != -1){
            data.putExtra(EXTRA_ID,id);
            Log.d("inside", "save_Task: "+ id);
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
            default:
                break;
        }
    }
}