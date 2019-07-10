package com.rikkei.tra_02_t0104_fragmentlifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

class MainActivity : AppCompatActivity() {

    companion object {
        fun replaceFragment(activity: FragmentActivity, fragment: Fragment) {
            activity.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.frConstant, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) return
        replaceFragment(this, Fragment1.newInstance("", ""))


    }

}
