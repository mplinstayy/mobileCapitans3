package com.example.capitanspt3

import android.content.ContentValues.TAG
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
import com.example.capitanspt3.recycler.uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CurrentTaskActivity : AppCompatActivity() {

    private lateinit var doneButton: Button
    private lateinit var database: DatabaseReference
    private lateinit var tasks: ArrayList<Task>

    private lateinit var viewModel : UserViewModel
    private lateinit var userRecyclerView: RecyclerView
    lateinit var adapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_task)

        tasks = arrayListOf<Task>()

        userRecyclerView = findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        adapter = MyAdapter(tasks)
        userRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.allUsers.observe(this) {
            adapter.updateUserList(it)
            Log.d("ALL_USERS", it.toString())
        }


        doneButton = findViewById(R.id.imageButtonDone)
        doneButton.setOnClickListener {
            val intent = Intent(this, DoneTaskActivity::class.java)
            startActivity(intent)
            this.finish()
        }





//        database = FirebaseDatabase.getInstance().getReference("User/2z03SvNcpCMfhy8uVwi4JtSpwEy1/Tasks")
//        database.addValueEventListener(object: ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot){
//                val value = snapshot.getValue()
//                Log.d("TEST", "TEST: " + value)
//            }
//
//            override fun onCancelled(error: DatabaseError){
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//        })

    }
}