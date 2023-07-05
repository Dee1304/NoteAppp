package com.example.todolist

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ToDoDB(context: Context): SQLiteOpenHelper(context, "ToDo.db", null, 1) {
    private val TABLE_NAME = "ToDo"
    private val query = "select * from ${TABLE_NAME}"
    override fun onCreate(p0: SQLiteDatabase?) {
        //Tạo bảng
        p0?.execSQL("CREATE TABLE ${TABLE_NAME}(id Integer Primary key autoincrement, tieude text, noidung text )")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addDB(tieude: String,noidung: String){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("tieude", tieude)
        cv.put("noidung", noidung)
        db.insert(TABLE_NAME,null, cv)
    }

    fun readDB(): Cursor{
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if (db!=null){
            cursor = db.rawQuery(query,null)
        }
        return cursor!!
    }

    fun updateDB(row: Int, tieude: String, noidung: String){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("tieude", tieude)
        cv.put("noidung", noidung)
        db.update(TABLE_NAME, cv, "id=?", arrayOf(row.toString()))
    }

    fun delete(row: Int){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "id=?", arrayOf(row.toString()))
    }
}