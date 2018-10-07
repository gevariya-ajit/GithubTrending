package com.github.repo.repositories

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.repo.R
import com.github.repo.base.BaseFragment
import com.github.repo.baseActivity
import com.github.repo.makeErrorSnackBar
import com.github.repo.network.GitRepository
import com.github.repo.repositories.epoxy.RepositoryController
import com.github.repo.ui.EndlessScrollListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_repositories.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RepositoriesFragment : BaseFragment() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Inject

    private val viewModel by sharedViewModel<RepositoriesViewModel>()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Private members

    private lateinit var controller: RepositoryController

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Override methods

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initController()
        loadRepositories()

        swipeRefresh.setOnRefreshListener {
            loadRepositories()
        }
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle(getString(R.string.repositories))
    }

    override fun onBackPressed(): Boolean {
        baseActivity()?.finish()
        return true
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Private methods

    private fun initController() {
        controller = RepositoryController {
            val fragment = RepositoryDetailFragment.getInstance(it)
            baseActivity()?.addFragment(fragment)
        }

        val linearLayoutManager = LinearLayoutManager(context)
        with(recycler) {
            layoutManager = linearLayoutManager
            adapter = controller.adapter
        }

        recycler.addOnScrollListener(object : EndlessScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                loadRepositories(page + 1)
            }
        })

    }

    private fun loadRepositories(page: Int = 1) {
        if (!swipeRefresh.isRefreshing && page == 1) swipeRefresh.isRefreshing = true
        viewModel
                .getRepositories(GitRepository.Order.ASC, GitRepository.Language.KOTLIN, PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            swipeRefresh.isRefreshing = false
                            controller.addRepositories(it)
                            if (it.size < PAGE_SIZE) controller.setLoaderEnable(false)
                        },
                        {
                            swipeRefresh.isRefreshing = false
                            it.printStackTrace()
                            makeErrorSnackBar(container, it)
                        }
                )
                .addTo(disposable)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Static

    companion object {
        const val PAGE_SIZE = 25

    }
}
