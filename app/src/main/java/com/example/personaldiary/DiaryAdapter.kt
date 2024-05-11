package com.example.personaldiary

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiaryAdapter(private var diaries: List<Diary>) : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {

    inner class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDateTime)
        val noteTextView: TextView = itemView.findViewById(R.id.textViewContent)
        val editButton: ImageView = itemView.findViewById(R.id.updateIcon)
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

        holder.editButton.setOnClickListener{
            val intent =Intent(holder.itemView.context, UpdateDiary::class.java).apply {
                putExtra("diary_id", currentDiary.id)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return diaries.size
    }

    fun refreshData(newdiary : List<Diary>){
        diaries = newdiary
        notifyDataSetChanged()

    }
}