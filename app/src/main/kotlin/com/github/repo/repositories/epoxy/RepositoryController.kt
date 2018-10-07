package com.github.repo.repositories.epoxy

import com.airbnb.epoxy.EpoxyController
import com.github.repo.model.Repository

class RepositoryController(
        private val clickListener: ((repo: Repository) -> (Unit))? = null
) : EpoxyController() {

    private var repos = mutableListOf<Repository>()
    private var isLoaderEnable = true

    fun addRepositories(list: List<Repository>) {
        this.repos.addAll(list)
        this.repos = repos.asSequence().distinctBy { it.id }.toMutableList()
        requestModelBuild()
    }

    fun setLoaderEnable(value: Boolean) {
        isLoaderEnable = value
        requestModelBuild()
    }

    override fun buildModels() {
        repos.forEach {
            RepositoryEpoxyModel(it, clickListener)
                    .id(it.id)
                    .addTo(this)

        }
        if (isLoaderEnable && repos.size > 0) {
            ProgressEpoxyModel()
                    .id("progress")
                    .addTo(this)
        }
    }

}
