package com.sekar.inventory

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sekar.inventory.adapter.ItemAdapter
import com.sekar.inventory.model.ItemCreate
import com.sekar.inventory.model.ItemsItem
import com.sekar.inventory.repo.ItemViewModel
import kotlinx.android.synthetic.main.activity_input_inventory.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ItemViewModel
    lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        setupData()

        viewModel.getItem()
    }

    private fun setupData() {

        fab_add_item.setOnClickListener {
            val intent = Intent(this, InputInventoryActivity::class.java)
            startActivityForResult(intent, 1)
        }

        itemAdapter = ItemAdapter(this)
        rv_item.layoutManager = LinearLayoutManager(this)
        rv_item.adapter = itemAdapter

        viewModel.showSuccess.observe(this, Observer { item ->

            item?.let {
                itemAdapter.setData(it)
                itemAdapter.notifyDataSetChanged()
                progressbar.visibility = View.GONE
            }
        })

        viewModel.showFailure.observe(this, Observer { isFailed ->

            isFailed?.let {
                Toast.makeText(this, "Oops! something went wrong", Toast.LENGTH_SHORT).show()
            }
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteItem(
                    itemAdapter.getDataAt(viewHolder.adapterPosition).toString()
                )
                Toast.makeText(this@MainActivity, "Data deleted", Toast.LENGTH_LONG).show()
            }
        }).attachToRecyclerView(rv_item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            var name = data.getStringExtra("EXTRA_NAME")
            var price = data.getStringExtra("EXTRA_PRICE")
            var supplier = data.getStringExtra("EXTRA_SUPPLIER")
            var qty = data.getStringExtra("EXTRA_QTY")
            var date = data.getStringExtra("EXTRA_DATE")
            name = name!!.substring(0, 1).toUpperCase() + name.substring(1)
            val itemCreate = ItemCreate("", name, price, supplier, qty, date)
            viewModel.createItem(itemCreate)
            Toast.makeText(this, "Contact Saved!", Toast.LENGTH_SHORT).show()
        }
    }
}