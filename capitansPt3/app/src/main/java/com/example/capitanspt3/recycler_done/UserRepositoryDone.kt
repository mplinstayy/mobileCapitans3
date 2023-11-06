package com.example.capitanspt3.recycler_done

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.capitanspt3.Task
import com.example.capitanspt3.recycler.uri
import com.google.firebase.database.*


class UserRepositoryDone {

    val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("User")
            .child(uri)
            .child("Tasks")


    @Volatile
    private var INSTANCE: UserRepositoryDone? = null

    fun getInstance(): UserRepositoryDone {
        return INSTANCE ?: synchronized(this) {

            val instance = UserRepositoryDone()
            INSTANCE = instance
            instance
        }
    }

    fun loadTasks(taskList: MutableLiveData<List<Task>>) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("SNAP", snapshot.toString())
                Log.d("URI", uri)
                try {

                    val _taskLists: List<Task> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Task::class.java)!!
                    }

                    var list: List<Task> = emptyList()
                    _taskLists.forEach{
                        if (it.Status == 2){
                            list = list + it
                        }
                    }

                    snapshot.children.forEach {
                        if (it.child("Status").value.toString() == "2" ){
                            Log.d("SNAP_ITEM_DONE", it.value.toString())
                            taskList.value = list
                        }
                    }


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