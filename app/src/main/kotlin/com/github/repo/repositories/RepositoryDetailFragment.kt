package com.github.repo.repositories

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.repo.R
import com.github.repo.base.BaseFragment
import com.github.repo.databinding.FragmentRepositoryDetailBinding
import com.github.repo.model.Repository
import com.google.gson.Gson

class RepositoryDetailFragment : BaseFragment() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Private members

    private var repository: Repository? = null
    private lateinit var databinding: FragmentRepositoryDetailBinding

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Override methods

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        databinding = DataBindingUtil.inflate<FragmentRepositoryDetailBinding>(inflater,R.layout.fragment_repository_detail, container, false)
        return databinding.root
    }

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
        databinding.repo = repository
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
