package com.example.ignfootabbed

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
//Project uses some third party libraries
//Please make sure to pull the entire repo if you want to build the code
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tab_layout.addTab(tab_layout.newTab().setText("Articles"))
        tab_layout.addTab(tab_layout.newTab().setText("Videos"))

        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL)

        val adapter: MyPagerAdapter = MyPagerAdapter(supportFragmentManager)

        pager.adapter = adapter

        tab_layout.setupWithViewPager(pager)

    }
}

