package com.udemy_3.daycheckbeta2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.udemy_3.daycheckbeta2.ROOM.TaskViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    private TaskViewModel taskViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);//we get the recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//second of all we set the Layout Manager of the recyclerView
        recyclerView.setHasFixedSize(true);//and it the recyclerView weill not change size we fixe it

        final TaskAdapter adapter = new TaskAdapter();// now we call an adapter
        recyclerView.setAdapter(adapter);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab_add_task);
        fab.setOnClickListener(this);



        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.submitList(tasks);
            }


        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                taskViewModel.delete(adapter.getTaskAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Task delete", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            public void onItemClick(Task task) {
                Intent intent = new Intent(MainActivity.this, Add_Edit_Task.class);
                intent.putExtra(Add_Edit_Task.EXTRA_ID, task.getId());
                intent.putExtra(Add_Edit_Task.EXTRA_TASK_NAME, task.getNameTask());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
                Log.d("MainActivity", "onItemClick: "+ task.getId());


            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add_task:
                Intent intent = new Intent(MainActivity.this, Add_Edit_Task.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(Add_Edit_Task.EXTRA_TASK_NAME);
            String time = data.getStringExtra(Add_Edit_Task.EXTRA_TASK_TIME);
            String COLOR = data.getStringExtra(Add_Edit_Task.EXTRA_COLOR);

            Task task = new Task();
            task.setNameTask(name);
            task.setTimeTask(time);
            task.setColor(COLOR);

            taskViewModel.insert(task);
            Log.d("COLOR", "onActivityResult: "+ COLOR);

            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {

            int id = data.getIntExtra(Add_Edit_Task.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Task can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String nameUp = data.getStringExtra(Add_Edit_Task.EXTRA_TASK_NAME);
            String time = data.getStringExtra(Add_Edit_Task.EXTRA_TASK_TIME);
            String COLOR = data.getStringExtra((Add_Edit_Task.EXTRA_COLOR));

            Task task = new Task();
            task.setNameTask(nameUp);
            task.setTimeTask(time);
            task.setColor(COLOR);

            task.setId(id);
            taskViewModel.update(task);


        } else {
            Toast.makeText(this, "Not saved", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_task:
                taskViewModel.deleteAll();
                Toast.makeText(this, "All task delete", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

