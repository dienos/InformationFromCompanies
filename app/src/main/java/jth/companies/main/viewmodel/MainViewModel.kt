package jth.companies.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import jth.companies.base.BaseViewModel
import jth.companies.main.viewmodel.repository.MainRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jth.companies.https.RetrofitBuilder
import jth.companies.main.viewmodel.model.CompaniesResponse
import jth.companies.main.viewmodel.model.CompanyItem
import jth.companies.main.viewmodel.repository.datasource.CompaniesDataSource
import jth.companies.main.viewmodel.repository.datasource.CompaniesDataSource.Companion.PAGE_SIZE
import jth.companies.main.viewmodel.repository.datasource.CompanyDataSourceFactory

class MainViewModel : BaseViewModel() {
    var companyLiveDataSource: LiveData<PageKeyedDataSource<Int, CompanyItem>>? = null

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val repo by lazy {
        MainRepository()
    }

    fun addCompaniesDisposable(listener : CompaniesDataSource.CompaniesResultListener) {
        compositeDisposable.add(
            repo.getCompaniesEndPoint()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val response : CompaniesResponse = RetrofitBuilder.deserializer.fromJson(
                        result.string(),
                        TypeToken.getParameterized(CompaniesResponse::class.java).type
                    )



                    listener.onResult(response)
                }, { error ->

                    }
                )
        )
    }

    fun getCompanyPagedList(): LiveData<PagedList<CompanyItem>> {
        val pagedListConfig: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(1)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .build()

        val itemDataSourceFactory = CompanyDataSourceFactory(this)
        companyLiveDataSource = itemDataSourceFactory.getCompanyLiveDataSource()
        return LivePagedListBuilder(itemDataSourceFactory, pagedListConfig).build()
    }
}