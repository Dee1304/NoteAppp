package com.example.todolist

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var lst: MutableList<ToDo> = mutableListOf()
    private var adapter = ToDoAdapter(lst, this)
    private var helper = ToDoDB(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Tạo con trỏ
        val rs = helper.readDB()

        binding.fab.setOnClickListener {
            val intent = Intent(this, ContentActivity::class.java)
            startActivity(intent)
        }
        // Duyệt DataBase thêm vào list
        if (rs.moveToLast()){
            do {
                var id = rs.getInt(0)
                var tieude = rs.getString(1)
                var noidung = rs.getString(2)
                lst.add(ToDo(id, tieude, noidung))
            }while (rs.moveToPrevious())
        }
        // Thiết lập layout đổ adapter vào rcv
        binding.rcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = adapter
        adapter.notifyDataSetChanged()

        binding.sv.setOnClickListener {

        }
    }
}

