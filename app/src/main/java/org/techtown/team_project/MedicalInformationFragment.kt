package org.techtown.team_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText

class MedicalInformationFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addMedicalInfoBtn: Button = view.findViewById(R.id.btn_add_change_medical_info)

        addMedicalInfoBtn.setOnClickListener {
            val intent = Intent(requireContext(), AddMedicalInformation::class.java)
            startActivity(intent)
        }
        //onEditTextListener(view)
    }

//    private fun onEditTextListener(view: View) {
//        val edtvMedicalName: EditText = view.findViewById(R.id.edtv_add_medical_name)
//        val edtvMedicalNumber: EditText = view.findViewById(R.id.edtv_add_medical_number)
//        val edtvMedicalBirth: EditText = view.findViewById(R.id.edtv_add_medical_birth)
//        val edtvMedicalheight: EditText = view.findViewById(R.id.edtv_add_medical_height)
//        val edtvMedicalBloodType: EditText = view.findViewById(R.id.edtv_add_medical_blood_type)
//
//        edtvMedicalBloodType.setOnEditorActionListener { textView, i, keyEvent ->
//            if(i == EditorInfo.IME_ACTION_DONE){
//                Log.d("MedicalAdd", "완료")
//                true
//            }
//            false
//        }
//    }
}