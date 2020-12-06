package org.techtown.team_project

import android.content.Intent
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
        val edtvMedicalBirth: EditText = findViewById(R.id.edtv_add_medical_birth)
        val edtvMedicalHeight: EditText = findViewById(R.id.edtv_add_medical_height)
        val edtvMedicalWeight: EditText = findViewById(R.id.edtv_add_medical_weight)
        val edtvMedicalBloodType: EditText = findViewById(R.id.edtv_add_medical_blood_type)
        val edtvMedicalDiease: EditText = findViewById(R.id.edtv_add_medical_disease)
        val edtvMedicalMedicine: EditText = findViewById(R.id.edtv_add_medical_medicine)

        edtvMedicalMedicine.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){ // 여기서 넘어온 텍스트값 처리
                val intent = Intent()
                intent.putExtra("patient name",edtvMedicalName.text.toString())
                intent.putExtra("patient birth",edtvMedicalBirth.text.toString())
                intent.putExtra("patient height",edtvMedicalHeight.text.toString())
                intent.putExtra("patient weight",edtvMedicalWeight.text.toString())
                intent.putExtra("patient blood type",edtvMedicalBloodType.text.toString())
                intent.putExtra("patient disease",edtvMedicalDiease.text.toString())
                intent.putExtra("patient medicine",edtvMedicalMedicine.text.toString())
                setResult(202,intent)
                finish()
                return@setOnEditorActionListener true
            }
            false
        }
    }
}