package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class text_editor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_editor)

        val undo_btn = findViewById<Button>(R.id.undo_text)

        val clr_btn = findViewById<Button>(R.id.clear_text)
        val text = findViewById<EditText>(R.id.textinput)

        var original = text.text



        clr_btn.setOnClickListener {
            original = text.text
            text.setText("")

        }

        undo_btn.setOnClickListener {
            text.setText(original)

        }



    }
}