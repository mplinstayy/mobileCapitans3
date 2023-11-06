package com.example.capitanspt3.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capitanspt3.Task

class UserViewModel: ViewModel() {

    private val repository : UserRepository

    private val _allTasks = MutableLiveData<List<Task>>()
    val allUsers : LiveData<List<Task>>
        get() = _allTasks

    init {
        repository = UserRepository().getInstance()
        repository.loadTasks(_allTasks)


    }
}