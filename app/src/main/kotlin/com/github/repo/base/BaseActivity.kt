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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            val size = supportFragmentManager.fragments.size
            if (size > 0) {
                val fragment = supportFragmentManager.fragments[size - 1]
                if (fragment is BaseFragment) {
                    if (fragment.onBackPressed()) return
                }
            }
        }
        super.onBackPressed()
    }
}
