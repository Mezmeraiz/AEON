package ru.example.aeon.features.payment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_payment.view.*
import ru.example.aeon.R
import ru.example.aeon.core.Utils
import ru.example.aeon.core.api.entities.Payment
import ru.example.aeon.core.extension.inflate
import javax.inject.Inject

class PaymentAdapter @Inject constructor() : RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    var payments = listOf<Payment>()

    fun setData(payments: List<Payment>) {
        this.payments = payments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_payment))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(payments[position])

    override fun getItemCount() = payments.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(itemData: Payment) {
            with(itemView){
                tvDesc.text = itemData.desc ?: "-"
                tvAmount.text = if (itemData.amount != null && itemData.currency != null)
                    "${itemData.amount} ${itemData.currency}" else "-"
                tvDate.text = if (itemData.created != null && itemData.created!! > 0)
                    Utils.getDate(itemData.created!! * 1000) else "-"
            }
        }

    }
}