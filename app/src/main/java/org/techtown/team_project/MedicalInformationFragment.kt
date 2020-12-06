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
import android.widget.TextView

class MedicalInformationFragment : Fragment() {
    val medicalInfo = arrayListOf<TextView>()
    val patientInfo = arrayOf("","","","","","","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_medical_information, container, false)
        setMedicalInfo(view)
        return view
    }

    private fun setMedicalInfo(view:View){
        medicalInfo.add(view.findViewById(R.id.textView11))
        medicalInfo.add(view.findViewById(R.id.textView12))
        medicalInfo.add(view.findViewById(R.id.textView13))
        medicalInfo.add(view.findViewById(R.id.textView14))
        medicalInfo.add(view.findViewById(R.id.textView15))
        medicalInfo.add(view.findViewById(R.id.textView16))
        medicalInfo.add(view.findViewById(R.id.textView17))
        medicalInfo[0].text = SavedInfo.prefs.getString("patient name","")
        medicalInfo[1].text = SavedInfo.prefs.getString("patient birth","")
        medicalInfo[2].text = SavedInfo.prefs.getString("patient height","")
        medicalInfo[3].text = SavedInfo.prefs.getString("patient weight","")
        medicalInfo[4].text = SavedInfo.prefs.getString("patient blood type","")
        medicalInfo[5].text = SavedInfo.prefs.getString("patient disease","")
        medicalInfo[6].text = SavedInfo.prefs.getString("patient medicine","")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addMedicalInfoBtn: Button = view.findViewById(R.id.btn_add_change_medical_info)

        addMedicalInfoBtn.setOnClickListener {
            val intent = Intent(requireContext(), AddMedicalInformation::class.java)
            startActivityForResult(intent,202)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data==null)
            return
        if(data.getStringExtra("patient name")!="")
            patientInfo[0]=data.getStringExtra("patient name")!!
        if(data.getStringExtra("patient birth")!="")
            patientInfo[1]=data.getStringExtra("patient birth")!!
        if(data.getStringExtra("patient height")!="")
            patientInfo[2]=data.getStringExtra("patient height")!!
        if(data.getStringExtra("patient weight")!="")
            patientInfo[3]=data.getStringExtra("patient weight")!!
        if(data.getStringExtra("patient blood type")!="")
            patientInfo[4]=data.getStringExtra("patient blood type")!!
        if(data.getStringExtra("patient disease")!="")
            patientInfo[5]=data.getStringExtra("patient disease")!!
        if(data.getStringExtra("patient medicine")!="")
            patientInfo[6]=data.getStringExtra("patient medicine")!!
        for (i in 0 .. 6)
            patientInfo[i] = onDeleteEnter(patientInfo[i])
        SavedInfo.prefs.setString("patient name",patientInfo[0])
        SavedInfo.prefs.setString("patient birth",patientInfo[1])
        SavedInfo.prefs.setString("patient height",patientInfo[2])
        SavedInfo.prefs.setString("patient weight",patientInfo[3])
        SavedInfo.prefs.setString("patient blood type",patientInfo[4])
        SavedInfo.prefs.setString("patient disease",patientInfo[5])
        SavedInfo.prefs.setString("patient medicine",patientInfo[6])
        medicalInfo[0].text = SavedInfo.prefs.getString("patient name","")
        medicalInfo[1].text = SavedInfo.prefs.getString("patient birth","")
        medicalInfo[2].text = SavedInfo.prefs.getString("patient height","")
        medicalInfo[3].text = SavedInfo.prefs.getString("patient weight","")
        medicalInfo[4].text = SavedInfo.prefs.getString("patient blood type","")
        medicalInfo[5].text = SavedInfo.prefs.getString("patient disease","")
        medicalInfo[6].text = SavedInfo.prefs.getString("patient medicine","")
    }
    private fun onDeleteEnter(str: String) :String{
        var strs = listOf<String>()
        var outStr:String = str
        if(str.contains('\n')){
            strs = str.toString().split("\n")
            outStr = strs[0]
        }
        return outStr
    }
}