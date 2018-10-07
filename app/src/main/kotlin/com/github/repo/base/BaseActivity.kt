package com.github.repo.base

import android.support.v7.app.AppCompatActivity
import com.github.repo.R

open class BaseActivity : AppCompatActivity() {

    fun addFragment(fragment: BaseFragment) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragment, fragment.getFragmentTag())
                .addToBackStack(fragment.getFragmentTag())
                .commitAllowingStateLoss()
    }

}
