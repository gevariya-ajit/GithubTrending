package com.github.repo

import android.support.v4.app.Fragment
import com.github.repo.base.BaseActivity

fun Fragment.baseActivity(): BaseActivity? {
    if (activity is BaseActivity) {
        return activity as BaseActivity
    }
    return null
}
