package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val hepler = ToDoDB(this)
        val intent = intent
        var id = intent.getIntExtra("id", 0)
        binding.edtTieuDe.setText(intent.getStringExtra("tieude"))
        binding.edtNoiDung.setText(intent.getStringExtra("noidung"))
        binding.btnAdd.setOnClickListener {
            var tieude= binding.edtTieuDe.text.toString().trim()
            var noidung = binding.edtNoiDung.text.toString().trim()
            if(id == 0 ){
                hepler.addDB(tieude, noidung)
            }else{
                hepler.updateDB(id, tieude, noidung)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}