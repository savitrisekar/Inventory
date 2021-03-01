package com.sekar.inventory.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sekar.inventory.R
import com.sekar.inventory.UpdateInventoryActivity
import com.sekar.inventory.model.ItemsItem

class ItemAdapter(private val context: Context) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var item: List<ItemsItem> = listOf()
    private var listener: OnItemClickListener? = null

    fun setData(itemList: List<ItemsItem>) {
        item = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_inventory, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemList = item[position]

        holder.name.text = itemList.name
        holder.price.text = itemList.price

        holder.itemView.setOnClickListener {
//            listener!!.OnItemClick(item[position])
            var intent = Intent (context,UpdateInventoryActivity::class.java)
            intent.putExtra("id",item.get(position).id)
            intent.putExtra("name",item.get(position).name)
            intent.putExtra("price",item.get(position).price)
            intent.putExtra("supplier",item.get(position).supplier)
            intent.putExtra("qty",item.get(position).qty)
            intent.putExtra("date",item.get(position).date)
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val price = itemView.findViewById<TextView>(R.id.tv_price)
    }

    fun getDataAt(position: Int): String? {
        return item[position].id
    }

    interface OnItemClickListener {
        abstract fun OnItemClick(dataItem: ItemsItem)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}