package com.github.repo.repositories

import android.os.Bundle
import com.github.repo.R
import com.github.repo.base.BaseFragment

class RepositoriesFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.fragment_repositories

    override fun getToolbarTitle(): Int = R.string.repositories

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
