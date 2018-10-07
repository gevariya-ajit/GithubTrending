package com.github.repo.repositories

import android.os.Bundle
import com.github.repo.R
import com.github.repo.base.BaseFragment
import com.github.repo.model.Repository
import com.google.gson.Gson

class RepositoryDetailFragment : BaseFragment() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Private members

    private var repository: Repository? = null

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Override methods

    override fun getLayout(): Int = R.layout.fragment_repository_detail

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let { bundle ->
            if (bundle.containsKey(KEY_REPOSITORY)) {
                val repoJson = bundle.getString(KEY_REPOSITORY)
                repository = Gson().fromJson(repoJson, Repository::class.java)
                repository?.name?.let { setToolbarTitle(it) }
                initData(repository)

            }
        }
    }

    override fun onResume() {
        super.onResume()
        repository?.name?.let { setToolbarTitle(it) }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Private methods

    private fun initData(repository: Repository?) {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Static

    companion object {
        const val KEY_REPOSITORY = "REPOSITORY"

        fun getInstance(repository: Repository): RepositoryDetailFragment {
            val fragment = RepositoryDetailFragment()
            val bundle = Bundle()
            val repo = Gson().toJson(repository)
            bundle.putString(KEY_REPOSITORY, repo)
            fragment.arguments = bundle
            return fragment
        }

    }
}
