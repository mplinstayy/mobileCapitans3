package com.example.capitanspt3.recycler_done

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capitanspt3.R
import com.example.capitanspt3.Task
import java.text.SimpleDateFormat

class MyAdapterDone(private val data : ArrayList<Task>) : RecyclerView.Adapter<MyViewHolderDone>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderDone {
        val  itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_view_task_done,
            parent,false
        )
        return MyViewHolderDone(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolderDone, position: Int) {
        val currentitem = data[position]

        holder.title.text = currentitem.NameTask
        holder.description.text = currentitem.Description
        holder.date.text = currentitem.DateTime.toString()
        holder.dateDone.text = currentitem.TaskDone

        val date = SimpleDateFormat("dd.MM.yyy").parse(currentitem.DateTime)
        val dateDone = SimpleDateFormat("dd.MM.yyy").parse(currentitem.TaskDone)
        if ( dateDone > date){
            holder.textDone.text = "Выполнено с задержкой"
            holder.textDone.setTextColor(Color.parseColor("#FFFF0000"))
        }
        else{
            holder.textDone.text = "Выполнено в срок"
            holder.textDone.setTextColor(Color.parseColor("#FF005613"))
        }

    }



    override fun getItemCount(): Int {
        return data.size
    }

    fun updateUserList(taskList : List<Task>){
        this.data.clear()
        this.data.addAll(taskList)
        notifyDataSetChanged()
    }
}