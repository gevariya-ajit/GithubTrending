package com.github.repo.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.github.repo.baseActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.app_bar_layout.*

abstract class BaseFragment : Fragment() {

    val disposable = CompositeDisposable()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        baseActivity()?.setSupportActionBar(toolbar)
    }

    override fun onDetach() {
        disposable.clear()
        super.onDetach()
    }

    open fun onBackPressed(): Boolean = false

    open fun getFragmentTag(): String = javaClass.simpleName

    open fun setToolbarTitle(title: String) {
        toolbar.title = title
    }

}
