package com.albara.bazarstoreroom.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albara.bazarstoreroom.data.models.Product
import com.albara.bazarstoreroom.databinding.ProductItemBinding

class ProductsAdapter(private val products : List<Product>)
    : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {


    inner class ProductViewHolder (private val viewDataBinding: ProductItemBinding)
        : RecyclerView.ViewHolder(viewDataBinding.root)  {
            fun bind( product: Product) {
                viewDataBinding.apply {
                    productName.text = product.productName
                    val amountForWeekday =" ${product.amountForWeekday
                            ?.toString() ?: "??"} ${product.unit}"
                    amountToOrderWeekday.append(amountForWeekday)

                    val amountForWeekend = " ${product.amountForWeekend
                        ?.toString() ?: "??"} ${product.unit}"
                    amountToOrderWeekend.append(amountForWeekend)

                    val amountToOrder = " ${product.amountToOrder
                        ?.toString() ?: "??"} ${product.unit}"
                    orderAmount.append(amountToOrder)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

}