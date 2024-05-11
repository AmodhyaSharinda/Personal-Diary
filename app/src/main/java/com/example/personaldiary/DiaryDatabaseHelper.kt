package com.example.personaldiary

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.Calendar

class DiaryDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DatbaseName, null, DatabaseVersion){

    companion object{

        private const val DatbaseName = "Diary.db"
        private const val DatabaseVersion = 1
        private const val TableName = "DiaryNotes"
        private const val Colomid = "id"
        private const val ColomnTitle = "Title"
        private const val ColomnDate = "Date"
        private const val ColomnNotes = "Notes"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TableName ($Colomid INTEGER PRIMARY KEY, $ColomnTitle TEXT, $ColomnDate TEXT, $ColomnNotes TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TableName")
        onCreate(db)
    }

    fun addDiary(diary: Diary): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ColomnTitle, diary.title)
        values.put(ColomnDate, diary.date.toString()) // Store date as milliseconds
        values.put(ColomnNotes, diary.note)
        return db.insert(TableName, null, values)
    }

    fun getAllDiaries(): List<Diary> {
        val diaries = mutableListOf<Diary>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TableName", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(Colomid))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(ColomnTitle))
                val date = cursor.getString(cursor.getColumnIndexOrThrow(ColomnDate))
                val note = cursor.getString(cursor.getColumnIndexOrThrow(ColomnNotes))
                val diary = Diary(id, title, note, date)
//                val diary = Diary(id, title, note, calendar)
                diaries.add(diary)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return diaries
    }
}