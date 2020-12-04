package org.techtown.team_project

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import java.lang.Exception

class IntroActivity : AppCompatActivity() {
    private val REQ_PERMISSION_ALL = 1000;
    private val permissions = arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.SEND_SMS,
            Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        if(!hasPermissions()){  // 권한이 없을경우
            requestPermissions()    // 권한 요청
        }
        else{   // 있을경우
            startMainActivity() // 로그인 페이지에 연결
        }
    }

    private fun hasPermissions(): Boolean{  // 권한이 있는지 체크
        for (permission in permissions){
            if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                return false    // 없으면 false
            }
        }
        return true     // 있으면 true
    }
    private fun requestPermissions(){   //
        ActivityCompat.requestPermissions(this, permissions, REQ_PERMISSION_ALL)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        try {
            when(requestCode){
                REQ_PERMISSION_ALL -> {     // 응답이 온다면
                    var allPermissionEnabled = true
                    for (grandResult in grantResults){
                        if(grandResult!= PackageManager.PERMISSION_GRANTED){
                            allPermissionEnabled = false
                        }
                    }
                    if(allPermissionEnabled){   // 권한 체크 됐다면
                        startMainActivity()
                    }
                }
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun startMainActivity(){     // 메인 엑티비티 시작
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()    // MainActivity 를 호출시 IntroActivity 는 남아있으므로 IntroActivity 를 finish 해줘야함
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}