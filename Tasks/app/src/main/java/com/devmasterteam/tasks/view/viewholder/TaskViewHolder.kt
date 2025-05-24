package com.devmasterteam.tasks.view.viewholder

import android.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.databinding.ItemTaskListBinding
import com.devmasterteam.tasks.service.listener.TaskListener
import com.devmasterteam.tasks.service.model.TaskModel
import java.text.SimpleDateFormat
import java.util.Locale

class TaskViewHolder(private val itemBinding: ItemTaskListBinding, val listener: TaskListener) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    /**
     * Atribui valores aos elementos de interface e também eventos
     */
    fun bindData(task: TaskModel) {

        itemBinding.textDescription.text = task.description
        val date  = inputDateFormat.parse(task.dueDate)
        itemBinding.textDueDate.text = outputDateFormat.format(date!!)
        itemBinding.textPriority.text = task.priorityDescription

        setComplete(task.complete)

        setListerners(task)
    }

    private fun setComplete(complete: Boolean) {
        if (complete) {
            itemBinding.imageTask.setImageResource(R.drawable.ic_done)
        } else {
            itemBinding.imageTask.setImageResource(R.drawable.ic_todo)
        }
    }

    private fun setListerners(task: TaskModel){
        itemBinding.textDescription.setOnClickListener {
            listener.onListClick(task.id)
        }

        itemBinding.imageTask.setOnClickListener {
            if ( task.complete ){
                listener.onUndoClick(task.id)
            } else {
                listener.onCompleteClick(task.id)
            }
        }

        itemBinding.textDescription.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.title_task_removal)
                .setMessage(R.string.label_remove_task)
                .setPositiveButton(R.string.button_yes) { _, _ ->
                    listener.onDeleteClick(task.id)
                }
                .setNeutralButton(R.string.button_cancel, null)
                .show()
            true
        }
    }
}