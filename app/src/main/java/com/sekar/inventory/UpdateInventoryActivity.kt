package com.sekar.inventory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sekar.inventory.adapter.ItemAdapter
import com.sekar.inventory.model.ItemCreate
import com.sekar.inventory.repo.ItemViewModel
import kotlinx.android.synthetic.main.activity_input_inventory.*
import kotlinx.android.synthetic.main.activity_input_inventory.edt_qty
import kotlinx.android.synthetic.main.activity_input_inventory.ll_save
import kotlinx.android.synthetic.main.activity_update_inventory.*

class UpdateInventoryActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemViewModel
    private lateinit var itemAdapter: ItemAdapter

    var id: String? = null
    var nama: String? = null
    var price: String? = null
    var supplier: String? = null
    var qty: String? = null
    var date: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_inventory)

        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        id = intent.getStringExtra("id")
        nama = intent.getStringExtra("name")
        price = intent.getStringExtra("price")
        supplier = intent.getStringExtra("supplier")
        qty = intent.getStringExtra("qty")
        date = intent.getStringExtra("date")

        tv_nama.setText(nama)
        tv_price.setText(price)
        tv_supplier.setText(supplier)
        edt_qty_update.setText(qty)
        tv_date_update.setText(date)

        ll_save_update.setOnClickListener {
            val item = ItemCreate(
                tv_nama.text.toString(), tv_price.text.toString(), tv_supplier.text.toString(),
                edt_qty_update.text.toString(), tv_date_update.text.toString()
            )

            viewModel.updateItem(item, id.toString())
            finish()
        }
    }
}