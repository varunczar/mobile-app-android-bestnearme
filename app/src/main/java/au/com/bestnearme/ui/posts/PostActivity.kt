package au.com.bestnearme.ui.posts

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.bestnearme.R
import au.com.bestnearme.data.PostData
import au.com.bestnearme.ui.BaseActivity
import au.com.bestnearme.ui.adapters.PostItemAdapter
import kotlinx.android.synthetic.main.activity_post.*


class PostActivity : BaseActivity(), PostView {


   var postPresenter: PostPresenterImpl?=null


    override fun setLayout(): Int {

        return R.layout.activity_post;
    }

    override fun init(savedInstanceState: Bundle?) {
      //  postPresenter.getAllPosts()
        getPresenter()?.let {
            it.getAllPosts()
        }
    }

    fun getPresenter(): PostPresenterImpl?{
        postPresenter = PostPresenterImpl(this, application)
        return postPresenter
    }

    override fun onStartScreen() {
    }

    override fun stopScreen() {
        postPresenter?.let {
            postPresenter!!.unbindView()
            postPresenter = null
        }

    }

    override fun showAllPosts(postList: List<PostData>) {

        Log.d("Response", "" + postList)
        recycler_view_posts.layoutManager = LinearLayoutManager(this)
        recycler_view_posts.adapter =  PostItemAdapter(postList, this)
    }


}
