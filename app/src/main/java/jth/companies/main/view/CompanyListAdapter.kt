package jth.companies.main.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import jth.companies.BR
import jth.companies.R
import jth.companies.base.CellType
import jth.companies.databinding.*
import jth.companies.main.viewmodel.model.CompanyItem

private val DIFF_CALLBACK: DiffUtil.ItemCallback<CompanyItem> =
    object : DiffUtil.ItemCallback<CompanyItem>() {
        override fun areItemsTheSame(oldItem: CompanyItem, newItem: CompanyItem): Boolean {
            return oldItem.companyId == newItem.companyId
        }

        override fun areContentsTheSame(oldItem: CompanyItem, newItem: CompanyItem): Boolean {
            return oldItem == newItem
        }
    }

class CompanyListAdapter :
    PagedListAdapter<CompanyItem, CompanyListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                when(viewType) {
                    CellType.CELL_TYPE_COMPANY.viewType -> {
                        R.layout.item_company
                    }

                    CellType.CELL_TYPE_REVIEW.viewType -> {
                        R.layout.item_review
                    }

                    else -> {
                        R.layout.item_default
                    }
                },
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)?.cellType) {
            CellType.CELL_TYPE_COMPANY.type -> {
                CellType.CELL_TYPE_COMPANY.viewType
            }

            CellType.CELL_TYPE_REVIEW.type -> {
                CellType.CELL_TYPE_REVIEW.viewType
            }

            CellType.CELL_TYPE_HORIZONTAL_THEME.type -> {
                CellType.CELL_TYPE_HORIZONTAL_THEME.viewType
            }

            else -> -1
        }
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: CompanyItem?) {
            val binding : Any = DataBindingUtil.bind(view)!!

            item?.let {
                when(binding) {
                    is ItemCompanyBinding ->{
                        binding.setVariable(BR.item, it)
                    }

                    is ItemReviewBinding ->{
                        binding.setVariable(BR.item, it)
                    }

                    is ItemHorizontalThemeBinding ->{
                        binding.setVariable(BR.item, it)
                    }
                    else -> {
                        if(binding is ItemDefaultBinding) {
                            binding.setVariable(BR.item, it)
                        } else {
                            Log.e("CompanyListAdapter", "error")
                        }
                    }
                }

            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}