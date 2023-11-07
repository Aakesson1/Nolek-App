package com.example.nolekapp.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nolekapp.Database.Data.LeakTestData
import com.example.nolekapp.R

class ApiAdapter(val context: Context, var userList: List<LeakTestData>): RecyclerView.Adapter<ApiAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var desription: TextView
        var reason: TextView
        var sniffingPoint: TextView
init {
    name = itemView.findViewById(R.id.tvName)
    desription = itemView.findViewById(R.id.tvDescription)
    reason = itemView.findViewById(R.id.tvReason)
    sniffingPoint = itemView.findViewById(R.id.tvSniffingPoint)
}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = userList[position].name
        holder.desription.text = userList[position].description
        holder.reason.text = userList[position].reason
        holder.sniffingPoint.text = userList[position].sniffingPoint

    }
}