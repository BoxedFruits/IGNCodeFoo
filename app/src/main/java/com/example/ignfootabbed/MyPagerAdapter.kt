package com.example.ignfootabbed

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter


class MyPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
    var mNumOfTabs: Int = 0

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return ArticleFragment()
            1 -> return VideoFragment()

            else -> return null
        }
    }

    override fun getCount(): Int {
        return 2
    }
    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Articles"
            1 -> "Videos"
            else -> {
                return "Huh"
            }
        }

    }
}
