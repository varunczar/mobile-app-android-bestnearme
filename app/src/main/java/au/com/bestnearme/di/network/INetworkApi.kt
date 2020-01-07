package au.com.bestnearme.di.network

import au.com.bestnearme.data.categories.Category
import au.com.bestnearme.data.place.Place
import au.com.bestnearme.data.placedetails.PlaceDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface INetworkApi {


    @GET(Endpoints.places+"{latitude}/{longitude}/1500/{category}")
    fun getPlaces(
        @Path("latitude") latitude : Double,
        @Path("longitude") longitude : Double,
        @Path("category") category : String): Single<List<Place>>

    @GET(Endpoints.placedetails+"{placeid}")
    fun getPlaceDetailsForId(@Path("placeid") placeId : String): Single<PlaceDetails>

    @GET(Endpoints.categories)
    fun getCategories(): Single<List<Category>>
}