package com.github.repo.repositories.epoxy

import android.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.github.repo.R
import com.github.repo.databinding.RepositoryItemBinding
import com.github.repo.model.Repository

class RepositoryEpoxyModel(
        private val repository: Repository,
        private val clickListener: ((repo: Repository) -> (Unit))? = null
) : DataBindingEpoxyModel() {

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        if (binding is RepositoryItemBinding) {
            binding.repo = repository
            binding.root.setOnClickListener { clickListener?.invoke(repository) }
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.repository_item
    }

}
