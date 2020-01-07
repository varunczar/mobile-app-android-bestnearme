package au.com.bestnearme.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.R
import au.com.bestnearme.data.placedetails.PhotosItem
import au.com.bestnearme.views.utils.loadImage
import kotlinx.android.synthetic.main.layout_photos_item.view.*

class PhotosAdapter(val mPhotos: List<PhotosItem>, val context: Context) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_photos_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mPhotos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.image_view_photo.loadImage(mPhotos.get(position).photoReference)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}