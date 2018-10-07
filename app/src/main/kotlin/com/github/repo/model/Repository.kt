package com.github.repo.model

import com.google.gson.annotations.SerializedName

class Repository {

    @SerializedName("id")
    var id: Long = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("full_name")
    var fullName: String? = null

    @SerializedName("owner")
    var owner: Owner? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("html_url")
    var url: String? = null

    @SerializedName("language")
    var language: String? = null

    @SerializedName("forks")
    var forks: Int? = null

    @SerializedName("open_issues_count")
    var openIssues: Int? = null

    @SerializedName("watchers")
    var watchers: Int? = null

    @SerializedName("default_branch")
    var defaultBranch: String? = null

}

data class RepositoryResponse(val items: List<Repository>)
