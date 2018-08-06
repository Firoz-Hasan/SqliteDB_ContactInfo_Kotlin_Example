package com.example.firozhasan.kotlin_sqlitedb_example.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.firozhasan.kotlin_sqlitedb_example.R
import kotlinx.android.synthetic.main.personal_details.view.*

class DetailsAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    internal var nameTV: TextView? = null
    internal var NumberTV: TextView? = null
    internal var emailTV: TextView? = null

    init {
        nameTV = itemView.findViewById(R.id.name_TV) as TextView
        NumberTV = itemView.findViewById(R.id.number_TV) as TextView
        emailTV = itemView.findViewById(R.id.email_TV) as TextView
    }
}