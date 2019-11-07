package au.com.bestnearme.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceHeight: Int,val isHorizontal: Boolean) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {

            if(!isHorizontal) {
                if (parent.getChildAdapterPosition(view) != parent.getAdapter()?.getItemCount()!! - 1) {
                    bottom = spaceHeight
                }
            }
            else {
                if (parent.getChildAdapterPosition(view) != parent.getAdapter()?.getItemCount()!! - 1) {
                    right = spaceHeight
                }
            }

        }
    }
}