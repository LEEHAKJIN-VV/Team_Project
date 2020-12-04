package org.techtown.team_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class AddMedicalInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medical_information)
        onEditTextLister()
    }

    private fun onEditTextLister(){
        val edtvMedicalName: EditText = findViewById(R.id.edtv_add_medical_name)
        val edtvMedicalNumber: EditText = findViewById(R.id.edtv_add_medical_number)
        val edtvMedicalBirth: EditText = findViewById(R.id.edtv_add_medical_birth)
        val edtvMedicalheight: EditText = findViewById(R.id.edtv_add_medical_height)
        val edtvMedicalBloodType: EditText = findViewById(R.id.edtv_add_medical_blood_type)

        edtvMedicalBloodType.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){ // 여기서 넘어온 텍스트값 처리
                Log.d("MedicalAdd", "완료")
                Log.d("MedicalAdd tv", "${textView.text}")  // blood Type의 tv가 TextView

                Log.d("MedicalAdd tv", "${edtvMedicalName.text}")   // name의 텍스트
                finish() // 액티비티 종료
                return@setOnEditorActionListener true
            }
            Log.d("MedicalAdd", "실패")
            false
        }
    }
}