package au.com.bestnearme.ui.posts

import au.com.bestnearme.data.PostData
import au.com.bestnearme.ui.IView

interface PostView: IView {

    fun showAllPosts(postList: List<PostData>)
}