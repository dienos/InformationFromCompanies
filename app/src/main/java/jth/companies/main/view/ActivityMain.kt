package jth.companies.main.view

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import jth.companies.R
import jth.companies.base.BaseBindingActivity
import jth.companies.databinding.ActivityMainBinding
import jth.companies.main.viewmodel.MainViewModel

class ActivityMain : BaseBindingActivity<ActivityMainBinding>() {
    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initializeViewModel() {
        binding.viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initAdapter()
        binding.viewModel?.getCompanyPagedList()
    }

    override fun initializeEventObserver() {}

    private fun initAdapter() {
        val adapter = CompanyListAdapter()
        binding.companyList.layoutManager = LinearLayoutManager(this)

        binding.viewModel?.getCompanyPagedList()?.observe(this, { items ->
            adapter.submitList(items)
        })

        binding.companyList.adapter = adapter
    }
}