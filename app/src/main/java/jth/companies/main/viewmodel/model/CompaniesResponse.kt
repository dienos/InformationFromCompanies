package jth.companies.main.viewmodel.model

import com.google.gson.annotations.SerializedName

data class CompaniesResponse(
    @SerializedName("minimum_interviews")
    val minimumInterviews: Int = 0,

    @SerializedName("total_page")
    val totalPage: Int = 0,

    @SerializedName("minimum_reviews")
    val minimumReviews: Int = 0,

    @SerializedName("total_count")
    val totalCount: Int = 0,

    @SerializedName("items")
    val items: ArrayList<CompanyItem> = arrayListOf())

