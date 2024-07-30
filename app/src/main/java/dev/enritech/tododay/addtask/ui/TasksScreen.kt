package dev.enritech.tododay.addtask.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    val isShowingDialog: Boolean by tasksViewModel.isShowingDialog.observeAsState(false)

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { tasksViewModel.openDialog() }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add task")
        }
    }) { padding ->
        AddTaskDialog(
            isShowing = isShowingDialog,
            onDismiss = { tasksViewModel.onDialogClose() }) {
            tasksViewModel.onTaskAdded(it)
        }
        TaskList(tasksViewModel = tasksViewModel)
    }
}

@Composable
fun TaskList(tasksViewModel: TasksViewModel) {
    LazyColumn {

    }
}

@Composable
fun TaskItem() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Ejemplo", modifier = Modifier.weight(1f))
        Checkbox(checked = true, onCheckedChange = { })
    }
}

@Composable
@Preview
fun TaskItemPreview() {
    TaskItem()
}

@Composable
fun AddTaskDialog(
    isShowing: Boolean,
    onDismiss: () -> Unit,
    onTaskAdded: (String) -> Unit
) {
    var task by remember {
        mutableStateOf("")
    }

    fun cleanState() {
        task = ""
    }

    if (isShowing) {
        Dialog(onDismissRequest = {
            onDismiss()
            cleanState()
        }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Añade tu tarea",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(value = task, onValueChange = { onTaskAdded(it) })
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        TextButton(onClick = {
                            onDismiss()
                            cleanState()
                        }) {
                            Text(text = "Cancelar")
                        }
                        TextButton(onClick = { onTaskAdded(task) }) {
                            Text(text = "Añadir tarea")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TasksWrapperPreview() {
    AddTaskDialog(false, {}) {}
}