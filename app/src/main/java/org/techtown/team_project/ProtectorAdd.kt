package org.techtown.team_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class ProtectorAdd() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_protector_add)
        setEditTextListener()
    }
    private fun setEditTextListener(){
        val edtvProName: EditText = findViewById(R.id.edtv_pro_name)
        val edtvProNumber: EditText = findViewById(R.id.edtv_pro_number)

        edtvProNumber.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){ // 여기서 넘어온 텍스트값 처리
                val intent = Intent()
                intent.putExtra("protector name",edtvProName.text.toString())
                intent.putExtra("protector number",edtvProNumber.text.toString())
                setResult(101,intent)
                finish() // 액티비티 종료
                return@setOnEditorActionListener true
            }
            false
        }
    }
}