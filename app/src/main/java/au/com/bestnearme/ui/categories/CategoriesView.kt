package au.com.bestnearme.ui.categories

import au.com.bestnearme.data.PostData
import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.data.place.Place
import au.com.bestnearme.ui.IView

interface CategoriesView: IView {

    fun showCategories(categories: List<Category>)
}