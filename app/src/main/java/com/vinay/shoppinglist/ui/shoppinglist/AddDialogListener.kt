package com.vinay.shoppinglist.ui.shoppinglist

import com.vinay.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}