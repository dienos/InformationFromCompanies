package jth.companies.main.viewmodel.model

import com.google.gson.annotations.SerializedName

data class CompanyItem(
    @SerializedName("cell_type")
    val cellType: String,

    @SerializedName("count")
    val horizontalCount: Int?,

    @SerializedName("themes")
    val horizontalThemes: ArrayList<Themes>?,

    @SerializedName("ranking")
    val ranking: Int?,

    @SerializedName("interview_difficulty")
    val interviewDifficulty: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("salary_avg")
    val salaryAvg: String?,

    @SerializedName("web_site")
    val webSite: String?,

    @SerializedName("logo_path")
    val logoPath: String?,

    @SerializedName("interview_question")
    val interviewQuestion: String?,

    @SerializedName("company_id")
    val companyId: String?,

    @SerializedName("has_job_posting")
    val hasJobPosting: String?,

    @SerializedName("rate_total_avg")
    val rateTotalAvg: String?,

    @SerializedName("industry_id")
    val industryId: String?,

    @SerializedName("review_summary")
    val reviewSummary: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("industry_name")
    val industryName: String?,

    @SerializedName("simple_desc")
    val simpleDesc: String?,

    @SerializedName("cons")
    val cons: String?,

    @SerializedName("days_ago")
    val daysAgo: String?,

    @SerializedName("pros")
    val pros: String?,

    @SerializedName("occupation_name")
    val occupationName: String?,

    @SerializedName("date")
    val date: String?
)
