package com.example.tgtodolist

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyAdapter(
    private val context: Context,
    private val layoutRes: Int,
    private val list: ArrayList<TaskData>
) :
    RecyclerView.Adapter<MyAdapter.MyViewHold>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHold {
        val view: View = LayoutInflater.from(context).inflate(layoutRes, null)
        return MyViewHold(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHold(itemView: View) : ViewHolder(itemView) {
        val checkbox: CheckBox = itemView.findViewById(R.id.checkbox)
        val taskText: TextView = itemView.findViewById(R.id.taskText)
        val editBtn: ImageButton = itemView.findViewById(R.id.editBtn)
        val deleteBtn: ImageButton = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onBindViewHolder(holder: MyViewHold, position: Int) {
        val items = list[position]
        holder.taskText.text = items.taskText

        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.taskText.setTextColor(Color.GREEN)
            } else {
                holder.taskText.setTextColor(Color.BLACK)
            }
        }
        holder.deleteBtn.setOnClickListener {
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, list.size)
        }
        holder.editBtn.setOnClickListener {
            val alert = AlertDialog.Builder(context)
            val view: View = LayoutInflater.from(context).inflate(R.layout.edit_alert, null)
            alert.setView(view)
            val editAlert = alert.create()
            editAlert.show()

            val editTaskEt: EditText = view.findViewById(R.id.editTaskEt)
            val addEditedBtn: Button = view.findViewById(R.id.addEditedTask)
            val oldText = items.taskText
            val editableOldText = Editable.Factory().newEditable(oldText)
            editTaskEt.text = editableOldText
            addEditedBtn.setOnClickListener {
                val newText = editTaskEt.text.toString()
                holder.taskText.text = newText
                editAlert.dismiss()
            }
        }
    }
}