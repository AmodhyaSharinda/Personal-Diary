package com.example.personaldiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import com.example.personaldiary.databinding.ActivityDiaryPagesBinding

class DiaryPages : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryPagesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityDiaryPagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Addbutton.setOnClickListener{
            val intent = Intent(this, AddDiaryPages::class.java)
           startActivity(intent)
        }
    }
}