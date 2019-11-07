package au.com.bestnearme.di.network

import au.com.bestnearme.data.PostData
import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.data.place.Place
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface INetworkApi {

    @GET(Endpoints.posts)
    fun getAllPosts(): Observable<List<PostData>>

    @GET(Endpoints.places+"-33.748005/151.289443/1500/{category}")
    fun getPlaces(@Path("category") category : String): Observable<List<Place>>

    @GET(Endpoints.categories)
    fun getCategories(): Observable<List<Category>>
}