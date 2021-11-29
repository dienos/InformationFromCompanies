package jth.companies.main.viewmodel.model

import com.google.gson.annotations.SerializedName

data class Themes(
    @SerializedName("color")
    val color: String,

    @SerializedName("cover_image")
    val coverImage: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String
)