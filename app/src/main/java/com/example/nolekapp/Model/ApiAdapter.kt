package com.example.nolekapp.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nolekapp.Database.Data.LeakTestData
import com.example.nolekapp.R
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.mongodb.kbson.ObjectId


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
class ObjectIdTypeAdapter : TypeAdapter<ObjectId>() {
    override fun write(out: JsonWriter, value: ObjectId) {
        out.value(value.toHexString())
    }

    override fun read(`in`: JsonReader): ObjectId {
        return ObjectId(`in`.nextString())
    }
}