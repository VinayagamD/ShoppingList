package com.vinay.shoppinglist.utils.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinay.shoppinglist.R
import com.vinay.shoppinglist.data.db.entities.ShoppingItem
import com.vinay.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(var items: List<ShoppingItem>,
private val viewModel: ShoppingViewModel): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        holder.itemView.nameTV.text = curShoppingItem.name
        holder.itemView.amountTV.text = "${curShoppingItem.amount}"
        holder.itemView.deleteIV.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }
        holder.itemView.addIV.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }
        holder.itemView.minusIV.setOnClickListener {
            if(curShoppingItem.amount > 0) {
                curShoppingItem.amount --
                viewModel.upsert(curShoppingItem)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}