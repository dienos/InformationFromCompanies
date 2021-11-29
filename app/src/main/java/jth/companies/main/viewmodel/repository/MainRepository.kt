package jth.companies.main.viewmodel.repository

import jth.companies.https.RetrofitBuilder
import io.reactivex.Observable
import okhttp3.ResponseBody

class MainRepository {
    fun getCompaniesEndPoint() : Observable<ResponseBody> {
        return RetrofitBuilder.instance.getCompanies()
    }
}