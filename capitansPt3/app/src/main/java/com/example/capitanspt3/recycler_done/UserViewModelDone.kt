package com.example.capitanspt3.recycler_done

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capitanspt3.Task

class UserViewModelDone: ViewModel() {
    private val repository : UserRepositoryDone

    private val _allDoneTasks = MutableLiveData<List<Task>>()
    val allUsers : LiveData<List<Task>>
        get() = _allDoneTasks

    init {
        repository = UserRepositoryDone().getInstance()
        repository.loadTasks(_allDoneTasks)

    }
}