package com.sekar.inventory

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sekar.inventory.model.ItemsItem
import com.sekar.inventory.repo.ItemViewModel
import kotlinx.android.synthetic.main.activity_input_inventory.*
import java.text.SimpleDateFormat
import java.util.*

class InputInventoryActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemViewModel

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_inventory)

        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        initView()
    }

    private fun initView() {

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        ll_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@InputInventoryActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

        ll_save.setOnClickListener {
            save()
        }
    }

    private fun save() {

        val result = ItemsItem()
        val id = result.id

        if (!edt_nama.text.isEmpty() || !edt_price.text.isEmpty() || !edt_supplier.text.isEmpty()
            || !edt_qty.text.isEmpty() || !tv_date.text.isEmpty()
        ) {

            val data = Intent()
            data.putExtra("EXTRA_ID", "${id}")
            data.putExtra("EXTRA_NAME", "${edt_nama.text}")
            data.putExtra("EXTRA_PRICE", "${edt_price.text}")
            data.putExtra("EXTRA_SUPPLIER", "${edt_supplier.text}")
            data.putExtra("EXTRA_QTY", "${edt_qty.text}")
            data.putExtra("EXTRA_DATE", "${tv_date.text}")
            setResult(Activity.RESULT_OK, data)
            finish()
        } else {
            Toast.makeText(this, "Provide Complete Data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        tv_date!!.text = sdf.format(cal.getTime())
    }
}