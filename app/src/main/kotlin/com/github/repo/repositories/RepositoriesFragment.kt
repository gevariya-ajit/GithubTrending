package com.github.repo.repositories

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.github.repo.R
import com.github.repo.base.BaseFragment
import com.github.repo.makeErrorSnackBar
import com.github.repo.network.GitRepository
import com.github.repo.repositories.epoxy.RepositoryController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_repositories.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RepositoriesFragment : BaseFragment() {

    private val viewModel by sharedViewModel<RepositoriesViewModel>()

    private lateinit var controller: RepositoryController

    override fun getLayout(): Int = R.layout.fragment_repositories

    override fun getToolbarTitle(): Int = R.string.repositories

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initController()
        loadRepositories()

        swipeRefresh.setOnRefreshListener {
            loadRepositories()
        }
    }

    private fun initController() {
        controller = RepositoryController {
            Timber.d("Repo clicked: ${it.fullName}")
        }
        with(recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = controller.adapter
        }
    }

    private fun loadRepositories() {
        if (!swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = true
        viewModel
                .getRepositories(GitRepository.Order.ASC, GitRepository.Language.KOTLIN, 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            swipeRefresh.isRefreshing = false
                            controller.setRepositories(it)
                        },
                        {
                            swipeRefresh.isRefreshing = false
                            it.printStackTrace()
                            makeErrorSnackBar(container, it)
                        }
                )
                .addTo(disposable)
    }
}
