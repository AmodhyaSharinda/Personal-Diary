package com.example.personaldiary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiaryAdapter(private var diaries: List<Diary>) : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {

    inner class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDateTime)
        val noteTextView: TextView = itemView.findViewById(R.id.textViewContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.diary_view, parent, false)
        return DiaryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val currentDiary = diaries[position]
        holder.titleTextView.text = currentDiary.title
        holder.dateTextView.text = currentDiary.date.toString() // Format the date as needed
        holder.noteTextView.text = currentDiary.note
    }

    override fun getItemCount(): Int {
        return diaries.size
    }

    fun refreshData(newdiary : List<Diary>){
        diaries = newdiary
        notifyDataSetChanged()

    }
}