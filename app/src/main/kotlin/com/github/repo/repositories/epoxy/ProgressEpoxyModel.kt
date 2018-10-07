package com.github.repo.repositories.epoxy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyModelWithView
import com.github.repo.R

class ProgressEpoxyModel : EpoxyModelWithView<View>() {

    override fun buildView(parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        return inflater.inflate(R.layout.loader, parent, false)
    }

}
