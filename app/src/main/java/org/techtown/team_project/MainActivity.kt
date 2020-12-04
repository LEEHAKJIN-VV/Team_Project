package org.techtown.team_project


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED

class MainActivity : AppCompatActivity() {
    var pageList = arrayListOf<Fragment>()
    private val nameList = arrayOf("홈", "의료정보", "보호자")
    private val REQUEST_CODE_PERMISSIONS = 1000
    private val PERM_FLAG = 99
    private val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onRequestPermission()
        val tabPages: ViewPager2 = findViewById(R.id.tab_pages)
        val tabs: TabLayout = findViewById(R.id.tabs)
        //tabs.setSelectedTabIndicatorColor(Color.parseColor("#FF4500"))
        makeFragment()
        makeViewPage(tabPages, tabs)
    }

    private fun makeFragment() {
        val page1 = MainFragment()
        val page2 = MedicalInformationFragment()
        val page3 = ProtectorFragment()
        pageList.add(page1)
        pageList.add(page2)
        pageList.add(page3)
    }

    private fun makeViewPage(tabPages: ViewPager2, tabs: TabLayout) {
        val adapter = PageAdapter(this)
        adapter.setFragment(pageList)
        tabPages.adapter = adapter
        TabLayoutMediator(tabs, tabPages) { tab, position ->
            tab.text = nameList[position]
        }.attach()
    }
    private fun isPermitted(): Boolean {     // 권한 체크
        for (perm in permissions) {
            if ((ContextCompat.checkSelfPermission(this, perm) != PERMISSION_GRANTED)) {
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
        when (requestCode) {
            PERM_FLAG -> {
                var check = true
                for (grant in grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        check = false
                        break
                    }
                }
                if (check) {
                    //startProcess()
                } else {
                    Toast.makeText(this, "권한을 승인하세요", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

