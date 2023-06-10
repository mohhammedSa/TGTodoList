package com.example.tgtodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tgtodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<TaskData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        list = ArrayList()

        recyclerViewAdapter()

        binding.addTask.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            val view: View = LayoutInflater.from(this).inflate(R.layout.add_alert_dialog, null)
            alert.setView(view)
            val alertDialog = alert.create()
            alertDialog.show()

            val taskET: EditText = view.findViewById(R.id.taskET)
            val addBtn: Button = view.findViewById(R.id.addTaskToList)
            addBtn.setOnClickListener {
                addTask(alertDialog, taskET)
            }
        }
    }

    private fun addTask(alert: AlertDialog, taskEt: EditText) {
        val taskText = taskEt.text.toString()
        val addTask = TaskData(0, taskText)
        list.add(0,addTask)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = MyAdapter(this, R.layout.task_card, list)
        alert.dismiss()
    }
    private fun recyclerViewAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = MyAdapter(this, R.layout.task_card, list)
    }
}