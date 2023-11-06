package com.example.capitanspt3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capitanspt3.recycler.MyAdapter
import com.example.capitanspt3.recycler.UserViewModel
import com.example.capitanspt3.recycler_done.MyAdapterDone
import com.example.capitanspt3.recycler_done.UserViewModelDone
import com.google.firebase.database.DatabaseReference

class DoneTaskActivity : AppCompatActivity() {

    private lateinit var currentButton: Button

    private lateinit var tasks: ArrayList<Task>

    private lateinit var viewModel : UserViewModelDone
    private lateinit var userRecyclerView: RecyclerView
    lateinit var adapter: MyAdapterDone


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_done_task)

        tasks = arrayListOf<Task>()

        userRecyclerView = findViewById(R.id.recyclerDoneTasks)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        adapter = MyAdapterDone(tasks)
        userRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[UserViewModelDone::class.java]

        viewModel.allUsers.observe(this) {
            adapter.updateUserList(it)
            Log.d("ALL_TASKS_DONE", it.toString())
        }








        currentButton = findViewById(R.id.imageButtonCurrent2)
        currentButton.setOnClickListener {
            val intent = Intent(this, CurrentTaskActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }
}