package org.techtown.team_project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){ //View Pager 2
    lateinit var fragmentItems:List<Fragment>
    fun getItem(position: Int): Fragment {
        return when(position){
            0 -> fragmentItems[0]
            1-> fragmentItems[1]
            else -> fragmentItems[2]
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int):Fragment{
        return when(position){
            0 -> fragmentItems[0]
            1-> fragmentItems[1]
            else-> fragmentItems[2]
        }
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
    fun setFragment(items:List<Fragment>){
        fragmentItems=items
    }
}