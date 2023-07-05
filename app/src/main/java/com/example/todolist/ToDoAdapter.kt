package com.example.todolist

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(private var lst:List<ToDo>, context: Context): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    inner class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_rcv, parent, false)
        return ToDoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.txtTD).setText(lst[position].tieude)
            findViewById<TextView>(R.id.txtND).setText(lst[position].noidung)
            findViewById<LinearLayout>(R.id.rcv_layout).setOnClickListener {
                val intent = Intent(context, ContentActivity::class.java)
                intent.putExtra("id", lst[position].id)
                intent.putExtra("tieude", findViewById<TextView>(R.id.txtTD).text.toString())
                intent.putExtra("noidung", findViewById<TextView>(R.id.txtND).text.toString())
                context.startActivity(intent)
            }
            findViewById<LinearLayout>(R.id.rcv_layout).setOnLongClickListener{
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Delete " + findViewById<TextView>(R.id.txtTD).text.toString() + " ?")
                builder.setMessage("Are you want to delete ?")
                builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    val helper = ToDoDB(context)
                    helper.delete(lst[position].id)
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                })
                builder.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

                })
                builder.create().show()
                return@setOnLongClickListener true

            }
        }
    }

}

