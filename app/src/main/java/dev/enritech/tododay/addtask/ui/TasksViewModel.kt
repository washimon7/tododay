package dev.enritech.tododay.addtask.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TasksViewModel @Inject constructor() : ViewModel() {
    private val _isShowingDialog = MutableLiveData<Boolean>()
    val isShowingDialog: LiveData<Boolean> = _isShowingDialog

    fun onDialogClose() {
        _isShowingDialog.value = false
    }

    fun openDialog() {
        _isShowingDialog.value = true
    }

    fun onTaskAdded(taskValue: String) {
        Log.i("[TODO]", "Task: $taskValue")
    }
}