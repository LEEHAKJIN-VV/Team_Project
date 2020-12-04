package org.techtown.team_project

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    var pageList= arrayListOf<Fragment>()
    private val nameList=arrayOf("홈", "의료정보", "보호자")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabPages: ViewPager2 = findViewById(R.id.tab_pages)
        val tabs:TabLayout = findViewById(R.id.tabs)
        //tabs.setSelectedTabIndicatorColor(Color.parseColor("#FF4500"))
        makeFragment()
        makeViewPage(tabPages, tabs)
    }
    private fun makeFragment(){
        val page1 = MainFragment()
        val page2 = Test1()
        val page3 = Test3()
        pageList.add(page1)
        pageList.add(page2)
        pageList.add(page3)
    }
    private fun makeViewPage(tabPages:ViewPager2, tabs:TabLayout){
        val adapter=PageAdapter(this)
        adapter.setFragment(pageList)
        tabPages.adapter=adapter
        TabLayoutMediator(tabs, tabPages){ tab, position ->
            tab.text=nameList[position]
        }.attach()
    }
}