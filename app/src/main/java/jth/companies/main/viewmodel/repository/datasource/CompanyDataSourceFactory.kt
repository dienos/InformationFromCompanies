package jth.companies.main.viewmodel.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import jth.companies.main.viewmodel.MainViewModel
import jth.companies.main.viewmodel.model.CompanyItem

class CompanyDataSourceFactory(val viewModel : MainViewModel) : DataSource.Factory<Int, CompanyItem>() {
    private val companyLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, CompanyItem>> = MutableLiveData()

    fun getCompanyLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, CompanyItem>> {
        return companyLiveDataSource
    }

    override fun create(): DataSource<Int, CompanyItem> {
        val itemDataSource = CompaniesDataSource(viewModel)
        companyLiveDataSource.postValue(itemDataSource)

        return itemDataSource
    }
}