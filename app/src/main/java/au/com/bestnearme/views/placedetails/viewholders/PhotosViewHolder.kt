package au.com.bestnearme.views.placedetails.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.data.placedetails.PhotosItem
import au.com.bestnearme.views.adapters.PhotosAdapter
import kotlinx.android.synthetic.main.layout_photos_row.view.*

class PhotosViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    val mRecyclerViewPhotos : RecyclerView? = itemView.recycler_view_photos


    fun bind(photos : List<PhotosItem>) {
        mRecyclerViewPhotos?.context?.let {
            mRecyclerViewPhotos.apply {
                layoutManager = LinearLayoutManager(it,RecyclerView.HORIZONTAL, false)
                adapter = PhotosAdapter(photos,it)
            }
        }
    }
}