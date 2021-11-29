package jth.companies.https

import jth.companies.https.RestApiUrl.COMPANIES
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitEndPoint {
    @GET(COMPANIES)
    @Headers("Content-Type: application/json")
    fun getCompanies(): Observable<ResponseBody>
}