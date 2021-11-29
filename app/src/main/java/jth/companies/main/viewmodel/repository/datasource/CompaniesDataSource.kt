package jth.companies.main.viewmodel.repository.datasource

import jth.companies.main.viewmodel.model.CompaniesResponse
import jth.companies.main.viewmodel.MainViewModel
import jth.companies.main.viewmodel.model.CompanyItem
import androidx.paging.PageKeyedDataSource

class CompaniesDataSource(private val viewModel: MainViewModel) : PageKeyedDataSource<Int, CompanyItem>() {
    private var key: Int = 0

    interface CompaniesResultListener {
        fun onResult(data: CompaniesResponse)
    }

    companion object {
        const val PAGE_SIZE = 14
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, CompanyItem>) {
        viewModel.addCompaniesDisposable(object : CompaniesResultListener {
            override fun onResult(data: CompaniesResponse) {
                callback.onResult(data.items, null, key)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CompanyItem>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CompanyItem>) {

    }
}