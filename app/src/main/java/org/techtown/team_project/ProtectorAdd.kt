package org.techtown.team_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class ProtectorAdd : AppCompatActivity() {
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
                Log.d("MedicalAdd", "완료")
                Log.d("MedicalAdd tv", "${textView.text}")  // blood Type의 tv가 TextView
                finish() // 액티비티 종료
                return@setOnEditorActionListener true
            }
            Log.d("MedicalAdd", "실패")
            false
        }
    }
}