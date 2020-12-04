package org.techtown.team_project

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ProtectorFragment : Fragment() {
    companion object{
        var btnIndex:Int = -1
    }
    val btnArray = arrayListOf<Button>()
    val protectorArray = arrayListOf<TextView>()
    val protectorNumArray = arrayListOf<TextView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_protector, container, false)
        setProtectors(view)
        return view
    }
    private fun setProtectors(view:View){
        protectorArray.add(view.findViewById(R.id.tv_pro1_name))
        protectorNumArray.add(view.findViewById(R.id.tv_pro1_number))
        protectorArray.add(view.findViewById(R.id.tv_pro2_name))
        protectorNumArray.add(view.findViewById(R.id.tv_pro2_number))
        protectorArray.add(view.findViewById(R.id.tv_pro3_name))
        protectorNumArray.add(view.findViewById(R.id.tv_pro3_number))
        protectorArray.add(view.findViewById(R.id.tv_pro4_name))
        protectorNumArray.add(view.findViewById(R.id.tv_pro4_number))
        protectorArray.add(view.findViewById(R.id.tv_pro5_name))
        protectorNumArray.add(view.findViewById(R.id.tv_pro5_number))
        for(i in 0..4) {
            protectorArray[i].text = SavedInfo.prefs.getString("protector name$i", "")
            if(protectorArray[i].text == "".toString()) protectorArray[i].text = "보호자 추가"
            protectorNumArray[i].text = SavedInfo.prefs.getString("protector number$i","")
            if(protectorNumArray[i].text == "".toString()) protectorNumArray[i].text = "연락처 추가"
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnArray.add(view.findViewById(R.id.btn_change_pro1))
        btnArray.add(view.findViewById(R.id.btn_change_pro2))
        btnArray.add(view.findViewById(R.id.btn_change_pro3))
        btnArray.add(view.findViewById(R.id.btn_change_pro4))
        btnArray.add(view.findViewById(R.id.btn_change_pro5))
        for(btn in btnArray)
            setButtons(btn)
    }
    private fun setButtons(btn: Button){
        btn.setOnClickListener {
            val intent = Intent(requireContext(), ProtectorAdd::class.java)
            startActivityForResult(intent,101)
            btnIndex = btnArray.indexOf(btn)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var name=data!!.getStringExtra("protector name")
        var number= data.getStringExtra("protector number")
        var names = listOf<String>()
        var numbers= listOf<String>()
        if(name!!.contains('\n')) {
            names = name.toString().split("\n")
            name = names[0]
        }
        if(number!!.contains('\n')) {
            numbers = number.toString().split("\n")
            number = numbers[0]
        }
        SavedInfo.prefs.setString("protector name$btnIndex",name.toString())
        SavedInfo.prefs.setString("protector number$btnIndex",number.toString())
        protectorArray[btnIndex].text = name.toString()
        protectorNumArray[btnIndex].text=number.toString()
    }
}