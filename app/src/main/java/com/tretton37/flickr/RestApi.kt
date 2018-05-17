package com.tretton37.flickr

import com.tretton37.flickr.model.PhotosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("rest/")
    fun getPictures(@Query("page") page: Int = 1,
                    @Query("method") method: String = "flickr.photos.search",
                    @Query("api_key") apiKey: String = BaseApplication.Key,
                    @Query("tags") tags: String = "Tretton37",
                    @Query("format") format: String = "json",
                    @Query("per_page") perPage: String = "10",
                    @Query("media") media: String = "photos",
                    @Query("nojsoncallback") jsonCallBack: String = "1",
                    @Query("extras") extras: String = "description,tags,owner_name,date_taken," +
                                                      "url_sq,url_n,url_z,url_c,url_l,url_o"):
            Single<PhotosResponse>
}