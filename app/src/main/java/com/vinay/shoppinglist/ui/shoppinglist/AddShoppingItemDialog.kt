package com.vinay.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.vinay.shoppinglist.R
import com.vinay.shoppinglist.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(context: Context, var listener: AddDialogListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)
        addTV.setOnClickListener {
            val name = nameTIET.text.toString()
            val amount = amountTIET.text.toString()
            if(name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item= ShoppingItem(name, amount.toInt())
            listener.onAddButtonClicked(item)
            dismiss()
        }
        cancelTV.setOnClickListener {
            cancel()
        }
    }
}
