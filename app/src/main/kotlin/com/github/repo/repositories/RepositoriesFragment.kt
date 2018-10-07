package com.github.repo.repositories

import android.os.Bundle
import com.github.repo.R
import com.github.repo.base.BaseFragment
import com.github.repo.makeErrorSnackBar
import com.github.repo.network.GitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_repositories.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RepositoriesFragment : BaseFragment() {

    private val viewModel by sharedViewModel<RepositoriesViewModel>()

    override fun getLayout(): Int = R.layout.fragment_repositories

    override fun getToolbarTitle(): Int = R.string.repositories

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadRepositories()
    }

    private fun loadRepositories() {
        viewModel
                .getRepositories(GitRepository.Order.ASC, GitRepository.Language.KOTLIN, 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Timber.d("repositories: ${it.size}")
                        },
                        {
                            makeErrorSnackBar(container, it)
                        }
                )
                .addTo(disposable)
    }
}
