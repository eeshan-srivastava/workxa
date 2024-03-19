package com.example.myapplication.presentation.workOrder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.WorkOrderItemContent
import com.example.myapplication.R

class WorkOrderListAdapter(
    private val dataSet: MutableList<WorkOrderItemContent> = mutableListOf(),
    private var listener: WorkOrderItemAdapterListener
): RecyclerView.Adapter<WorkOrderListAdapter.WorkOrderListItemViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WorkOrderListItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_work_order, viewGroup, false)
        return WorkOrderListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: WorkOrderListItemViewHolder, position: Int) {
        holder.tvTitle.text = dataSet[position].title
        holder.clRoot.setOnClickListener {
            listener.onWorkOrderItemClick(dataSet[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<WorkOrderItemContent>) {
        dataSet.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    interface WorkOrderItemAdapterListener {
        fun onWorkOrderItemClick(workOrderItemContent: WorkOrderItemContent)
    }

    inner class WorkOrderListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val clRoot: ConstraintLayout = itemView.findViewById(R.id.clRoot)
    }

}
