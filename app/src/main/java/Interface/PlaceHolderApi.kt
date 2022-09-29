package Interface

import Beans.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceHolderApi {

    @GET("posts")
    fun getListado():Call<List<Post>>

    @GET("posts/{id}")
    fun getPostId(@Path("id")id:Int):Call<Post>


}