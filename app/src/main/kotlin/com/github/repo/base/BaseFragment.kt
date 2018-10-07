package com.github.repo.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.repo.R
import com.github.repo.baseActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.app_bar_layout.*

abstract class BaseFragment : Fragment() {

    val disposable = CompositeDisposable()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        baseActivity()?.setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        toolbar.title = getString(getToolbarTitle())
    }

    override fun onDetach() {
        disposable.clear()
        super.onDetach()
    }

    open fun getFragmentTag(): String = javaClass.simpleName

    open fun getToolbarTitle(): Int = R.string.app_name

    abstract fun getLayout(): Int


}
