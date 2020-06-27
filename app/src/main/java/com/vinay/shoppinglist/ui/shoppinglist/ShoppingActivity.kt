package com.vinay.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinay.shoppinglist.R
import com.vinay.shoppinglist.data.db.entities.ShoppingItem
import com.vinay.shoppinglist.utils.ui.adapters.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory  by instance<ShoppingViewModelFactory>()
    lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        initializeUi()
    }

    private fun initializeUi() {
        viewModel = ViewModelProvider(this,factory)
            .get(ShoppingViewModel::class.java)
        val  adapter = ShoppingItemAdapter(listOf(), viewModel)
        shoppingRV.layoutManager = LinearLayoutManager(this)
        shoppingRV.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}
