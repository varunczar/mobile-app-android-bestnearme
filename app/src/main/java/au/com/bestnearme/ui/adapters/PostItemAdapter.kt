package au.com.bestnearme.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.R
import au.com.bestnearme.data.PostData

import kotlinx.android.synthetic.main.layout_place_row.view.*

class PostItemAdapter(val postItems: List<PostData>, val context: Context) : RecyclerView.Adapter<PostItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_place_row, parent, false))

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.itemView.text_view_place_name.text = postItems.get(position).title
        holder?.itemView.text_view_place_address.text = postItems.get(position).body
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}