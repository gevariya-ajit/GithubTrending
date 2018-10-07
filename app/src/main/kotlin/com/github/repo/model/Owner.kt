package com.github.repo.model

import com.google.gson.annotations.SerializedName

class Owner{

    @SerializedName("id")
    var id: Long = 0

    @SerializedName("login")
    var name: String? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("type")
    var type: String? = null

}
