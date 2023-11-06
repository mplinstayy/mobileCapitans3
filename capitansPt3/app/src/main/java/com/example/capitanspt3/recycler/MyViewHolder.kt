package com.example.capitanspt3.recycler

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capitanspt3.R

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title : TextView = itemView.findViewById(R.id.textViewTitle)
    val description : TextView = itemView.findViewById(R.id.textViewDescription)
    val date : TextView = itemView.findViewById(R.id.textViewDateTime)
    val deadline : TextView = itemView.findViewById(R.id.textViewDeadline)
    val button : Button = itemView.findViewById(R.id.ButtonCheckAsDone)
}