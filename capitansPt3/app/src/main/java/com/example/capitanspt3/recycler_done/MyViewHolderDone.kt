package com.example.capitanspt3.recycler_done

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capitanspt3.R

class MyViewHolderDone(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title : TextView = itemView.findViewById(R.id.textViewTitle)
    val description : TextView = itemView.findViewById(R.id.textViewDescription)
    val date : TextView = itemView.findViewById(R.id.textViewDateTime)
    val dateDone : TextView = itemView.findViewById(R.id.textViewDate)
    val textDone : TextView = itemView.findViewById(R.id.textViewDone)
}