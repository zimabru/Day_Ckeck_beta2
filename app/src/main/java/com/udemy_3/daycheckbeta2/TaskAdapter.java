package com.udemy_3.daycheckbeta2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.udemy_3.daycheckbeta2.ROOM.Tables.Task;


public class TaskAdapter extends ListAdapter<Task, TaskAdapter.TaskHolder> implements View.OnClickListener {
    private OnItemClickListener listener;
    private Context context;

    public TaskAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Task> DIFF_CALLBACK = new DiffUtil.ItemCallback<Task>() {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getNameTask().equals(newItem.getNameTask()) &&
                    oldItem.getTimeTask().equals(newItem.getTimeTask());
        }
    };


    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskHolder holder, int position) {
       Task currentTask = getItem(position);//le truc parfait
        holder.taskName.setText(currentTask.getNameTask());
        holder.relativeLayoutForChange.setBackgroundColor(Color.parseColor(currentTask.getColor()));
        if(currentTask.isCheckedTask()){
            holder.checkBox.setChecked(true);
        }

}




    public Task getTaskAt(int position) {
        return getItem(position);
    }

    @Override
    public void onClick(View view) {

    }


    class TaskHolder extends RecyclerView.ViewHolder {
        public TextView taskName;
        public CheckBox checkBox;
        public RelativeLayout relativeLayoutForChange;


        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_from_row);
            checkBox = itemView.findViewById(R.id.checkbox);
            relativeLayoutForChange = itemView.findViewById(R.id.relative_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }

                }
            });



            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   int position = getAdapterPosition();
                   if (listener != null && position != RecyclerView.NO_POSITION) {
                       if(checkBox.isChecked()){
                           listener.onChecked(getItem(position), true);

                       }else{
                           listener.onChecked(getItem(position), false);
                       }

                    }

                }
            });

        }



    }

    public interface OnItemClickListener {
        void onItemClick(Task task);
        void onChecked(Task task, boolean ischecked);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;


    }
}
