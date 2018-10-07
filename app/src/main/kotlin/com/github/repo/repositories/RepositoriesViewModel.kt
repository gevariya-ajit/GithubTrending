package com.github.repo.repositories

import android.arch.lifecycle.ViewModel
import com.github.repo.model.Repository
import com.github.repo.network.GitRepository
import io.reactivex.Observable

class RepositoriesViewModel(
        private val gitRepository: GitRepository
) : ViewModel(){


    fun getRepositories(
            order: GitRepository.Order,
            language: GitRepository.Language,
            pageSize: Int,
            page: Int
    ): Observable<List<Repository>> {

        return gitRepository.getRepositories(order, language, pageSize, page)

    }
}
