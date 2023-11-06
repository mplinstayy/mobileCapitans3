package com.example.capitanspt3.recycler

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.capitanspt3.R
import com.example.capitanspt3.Task
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class MyAdapter(private val data : ArrayList<Task>) : RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycle_view_item,
            parent,false
        )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val database = Firebase.database.reference
        val currentitem = data[position]

        holder.title.text = currentitem.NameTask
        holder.description.text = currentitem.Description
        holder.date.text = currentitem.DateTime.toString()

        if (currentitem.Status == 1){
            holder.button.setBackgroundColor(Color.parseColor("#FF86DDFF"))
            holder.button.text = "✓ Выполнено"
            holder.button.setTextColor(Color.parseColor("#FF000000"))

        }
        else{
            holder.button.setBackgroundColor(Color.parseColor("#FF00A9EC"))
            holder.button.text = "Отметить как выполненное"
            holder.button.setTextColor(Color.parseColor("#FFFFFFFF"))
        }

        val dateformat = SimpleDateFormat("dd.MM.yyy")

        val date = SimpleDateFormat("dd.MM.yyy").parse(currentitem.DateTime)
        val curDate = dateformat.format(Date())
        val currentDate = SimpleDateFormat("dd.MM.yyy").parse(curDate)
        if ( currentDate > date){

            holder.title.setTextColor(Color.parseColor("#FFFF0000"))
            holder.date.setTextColor(Color.parseColor("#FFFF0000"))
            holder.deadline.setTextColor(Color.parseColor("#FFFF0000"))
        }
        else{
            holder.title.setTextColor(Color.parseColor("#FF000000"))
            holder.date.setTextColor(Color.parseColor("#FF000000"))
            holder.deadline.setTextColor(Color.parseColor("#FF000000"))

        }



        holder.button.setOnClickListener {
            if (currentitem.Status == 0) {
                currentitem.Status = 1
                holder.button.setBackgroundColor(Color.parseColor("#FF86DDFF"))
                holder.button.text = "✓ Выполнено"
                holder.button.setTextColor(Color.parseColor("#FF000000"))
                database.child("User").child(uri).child("Tasks").child(currentitem.TaskID.toString())
                    .child("Status").setValue(1)

                val dateformat = SimpleDateFormat("dd.MM.yyy")
                currentitem.TaskDone = dateformat.format(Date())
                Log.d("TIME", currentitem.TaskDone.toString())
                database.child("User").child(uri).child("Tasks").child(currentitem.TaskID.toString())
                    .child("TaskDone").setValue(currentitem.TaskDone)

            }
            else{
                currentitem.Status = 0
                holder.button.setBackgroundColor(Color.parseColor("#FF00A9EC"))
                holder.button.text = "Отметить как выполненную"
                holder.button.setTextColor(Color.parseColor("#FF000000"))
                database.child("User").child(uri).child("Tasks").child(currentitem.TaskID.toString())
                    .child("Status").setValue(0)
            }
            Log.d("CLICKED", currentitem.NameTask.toString())
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