package com.albara.bazarstoreroom.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.albara.bazarstoreroom.R
import com.albara.bazarstoreroom.data.models.SectionWithProducts
import com.albara.bazarstoreroom.databinding.SectionItemBinding

class SectionAdapter :  RecyclerView.Adapter<SectionAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val viewDataBinding : SectionItemBinding)
        : RecyclerView.ViewHolder(viewDataBinding.root) {
            fun bind(context : Context, sectionWithProducts: SectionWithProducts) {
                viewDataBinding.sectionName.text = sectionWithProducts.section?.sectionName ?:
                context.getString(R.string.noSectionPlaceholder)
                viewDataBinding.rvSection.apply {
                    adapter = ProductsAdapter(sectionWithProducts.products)
                }
            }
        }

    private val differCallback = object :
        DiffUtil.ItemCallback<SectionWithProducts>() {
        override fun areItemsTheSame(oldItem: SectionWithProducts, newItem: SectionWithProducts): Boolean {
            return oldItem.section == newItem.section
        }

        override fun areContentsTheSame(oldItem: SectionWithProducts, newItem: SectionWithProducts): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            SectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(holder.itemView.context, differ.currentList[position])
    }
}