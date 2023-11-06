package com.example.capitanspt3.recycler

import android.util.Log
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import com.example.capitanspt3.Task
import com.google.firebase.database.*

var uri: String = ""

class UserRepository {

     val databaseReference: DatabaseReference =
       FirebaseDatabase.getInstance().getReference("User")
           .child(uri)
           .child("Tasks")


    @Volatile
    private var INSTANCE: UserRepository? = null

    fun getInstance(): UserRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = UserRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadTasks(taskList: MutableLiveData<List<Task>>) {
        //Log.d("LOAD", uri.toString())
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("SNAP", snapshot.toString())
                try {
                    val _taskLists: List<Task> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Task::class.java)!!
                    }

                    var list: List<Task> = emptyList()
                    _taskLists.forEach{
                        if (it.Status != 2){
                            list = list + it
                        }
                    }

                    taskList.value = list

                    Log.d("LIST", taskList.value.toString())
                } catch (e: Exception) {
                    Log.e("ERROR", e.message.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}