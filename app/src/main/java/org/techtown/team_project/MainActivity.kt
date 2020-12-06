package org.techtown.team_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.telephony.SmsManager
import android.util.Log
import android.view.KeyEvent
import java.io.IOException
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    var pageList = arrayListOf<Fragment>()
    private val nameList = arrayOf("홈", "의료정보", "보호자")

    private lateinit var mGeocoder: Geocoder
    private var address: List<Address>? = null
    private var locationManager: LocationManager? = null
    private var shortPress = false  // 버튼 길게 누름 감지

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabPages: ViewPager2 = findViewById(R.id.tab_pages)
        val tabs: TabLayout = findViewById(R.id.tabs)

        makeFragment()
        makeViewPage(tabPages, tabs)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        setAddress()
    }

    private fun makeFragment() {
        val page1 = MainFragment()
        val page2 = MedicalInformationFragment()
        val page3 = ProtectorFragment()
        pageList.add(page1)
        pageList.add(page2)
        pageList.add(page3)
    }

    private fun getMessageContent(){    // 보낼 메시지를 가져오는 메소드 (보호자 이름과 현재 주소를 가져와야함)
        val myLocation = address?.get(0)?.getAddressLine(0).toString()
    }

    private fun setAddress(){   // 주소를 가져오는 메소드
        val userLocation: Location? = getUserLocation()

        if(userLocation != null) {
            val currentLatitude = userLocation.latitude
            val currentLongitude = userLocation.longitude
            Log.d("MyCurrentLocation", "현재 내위치값 (위도 경도) (${currentLatitude}, ${currentLongitude})")

            mGeocoder = Geocoder(applicationContext, Locale.KOREAN)
            try {   // 위치를 가져옴
                address = mGeocoder.getFromLocation(
                    currentLatitude!!, currentLongitude!!, 1
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (address != null) {
                Log.d("내주소 한글", address!![0].getAddressLine(0))
            }
        }
    }

    private fun sendSMS(phoneNumber: String, message: String){      // 메시지를 보내느 코드
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun makeViewPage(tabPages: ViewPager2, tabs: TabLayout) {
        val adapter = PageAdapter(this)
        adapter.setFragment(pageList)
        tabPages.adapter = adapter
        TabLayoutMediator(tabs, tabPages) { tab, position ->
            tab.text = nameList[position]
        }.attach()
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation(): Location? {
        var currentLatLng: Location? = null
        val locationProvider = LocationManager.GPS_PROVIDER

        currentLatLng = locationManager?.getLastKnownLocation(locationProvider)
        return currentLatLng
    }

    fun startService(){     // Foreground 서비스 시작
        val serviceIntent: Intent = Intent(this, EmergencyService::class.java)
        serviceIntent.action = "START_FOREGROUND"
        startService(serviceIntent)
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            Log.d("Volumn", "길게누르는중")
            sendSMS("01071207426", "Test")
            shortPress = false
            return true
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            if(event?.action == KeyEvent.ACTION_DOWN){
                event.startTracking()
                if(event.repeatCount == 0){
                    shortPress = true
                }
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            if(shortPress){

            } else{
                //Don't handle longpress here, because the user will have to get his finger back up first
            }
            shortPress = false
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun onDestroy() {  // 앱이 종료됐을때 Foreground 서비스 시작
        super.onDestroy()
        startService()
    }
}

