package org.techtown.team_project

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_PERMISSIONS = 1000
    private val PERM_FLAG = 99
    private val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //test

        // supportFragmentManager.beginTransaction().replace(R.id.contan, ProtectorFragment()).addToBackStack(null).commit()
         // supportFragmentManager.beginTransaction().replace(R.id.contan, MedicalInformationFragment()).addToBackStack(null).commit()

        onRequestPermission()
    }

    private fun isPermitted(): Boolean{     // 권한 체크
        for(perm in permissions){
            if((ContextCompat.checkSelfPermission(this, perm)!= PERMISSION_GRANTED)){
                return false
            }
        }
        return true
    }

    /*내 위치 권환을 요청하는 메서드*/
    private fun onRequestPermission() {
        if (ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    REQUEST_CODE_PERMISSIONS
            )
            return
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERM_FLAG -> {
                var check = true
                for (grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        check = false
                        break
                    }
                }
                if(check){
                    //startProcess()
                } else{
                    Toast.makeText(this, "권한을 승인하세요", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}