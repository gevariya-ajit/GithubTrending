package com.github.repo

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import com.github.repo.base.BaseActivity
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Fragment.baseActivity(): BaseActivity? {
    if (activity is BaseActivity) {
        return activity as BaseActivity
    }
    return null
}

fun Fragment.makeErrorSnackBar(view: View, e: Throwable?, length: Int = Snackbar.LENGTH_LONG) {
    val text = if (e is UnknownHostException || e is SocketTimeoutException) {
        getString(R.string.error_no_connection)
    } else {
        e?.localizedMessage
    }
    Snackbar.make(view, "Error! $text", length).show()

}
