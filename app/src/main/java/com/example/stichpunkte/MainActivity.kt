package com.example.stichpunkte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    private val notices = mutableListOf<String>()
    private lateinit var isEnableCondition : () -> Boolean

    private lateinit var btnAddNote : Button
    private lateinit var txtNoteInput : EditText
    private lateinit var showNotices : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddNote = findViewById(R.id.btnAddNote)
        txtNoteInput = findViewById(R.id.txtNoteInput)
        showNotices = findViewById(R.id.showNotices)
        isEnableCondition = { txtNoteInput.text.isNotEmpty() && notices.size < 15 && txtNoteInput.text.length > 3 }

        btnAddNote.isEnabled = isEnableCondition()

        txtNoteInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btnAddNote.isEnabled = isEnableCondition()
            }
        })

        btnAddNote.setOnClickListener {
            notices.add(txtNoteInput.text.toString())
            txtNoteInput.text.clear()
            var output = ""
            notices.forEachIndexed { index, element ->
                    output += "${index +1}: $element\n"
            }
            showNotices.text = output
        }
    }

}
