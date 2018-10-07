package com.github.repo.base

import android.support.v7.app.AppCompatActivity
import com.github.repo.R

abstract class BaseActivity : AppCompatActivity() {

    fun addFragment(fragment: BaseFragment) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragment, fragment.getFragmentTag())
                .addToBackStack(fragment.getFragmentTag())
                .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1)
            finish()
        else
            super.onBackPressed()
    }
}
