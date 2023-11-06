package com.example.capitanspt3


data class Task(val TaskID: String? = null, val NameTask: String? = null, val Description: String? = null, val DateTime: String? = null,
                var Status: Int? = null, val ExecutorTask: String? = null, var TaskDone: String? = null)
