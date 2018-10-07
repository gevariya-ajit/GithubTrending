package com.github.repo.repositories.epoxy

import com.airbnb.epoxy.EpoxyController
import com.github.repo.model.Repository

class RepositoryController(
        private val clickListener: ((repo: Repository) -> (Unit))? = null
) : EpoxyController() {

    private var repos: List<Repository>? = null
    private var isLoaderEnable = true

    fun setRepositories(repos: List<Repository>) {
        this.repos = repos
        requestModelBuild()
    }

    fun setLoaderEnable(value: Boolean) {
        isLoaderEnable = value
        requestModelBuild()
    }

    override fun buildModels() {
        repos?.forEach {
            RepositoryEpoxyModel(it)
                    .id(it.id)
                    .addTo(this)

        }
        repos?.let {
            if (isLoaderEnable) {
                ProgressEpoxyModel()
                        .id("progress")
                        .addTo(this)
            }
        }
    }

    fun clear() {
        this.repos = null
        requestModelBuild()
    }

}
