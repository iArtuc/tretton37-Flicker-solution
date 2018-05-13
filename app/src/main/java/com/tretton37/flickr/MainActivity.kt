package com.tretton37.flickr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.tretton37.flickr.model.PhotoItem
import com.tretton37.flickr.model.PhotosResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        const val LOG_TAG = "MainActivity"
    }

    private val photos = ArrayList<PhotoItem>()

    lateinit var photosAdapter: PhotoAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    private val PAGE_START = 1
    private var isRecyclerLoading = false
    private var currentPage = PAGE_START


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createRecyclerView()
        loadNextPage()
        addRecyclerPositionListener()

    }


    private fun createRecyclerView() {
        photosAdapter = PhotoAdapter(photos, object : ItemClick {
            override fun clickedItem(photoItem: PhotoItem) {
                startActivity(PhotoDetailActivity.newIntent(this@MainActivity, photoItem))
            }
        })
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        photoList.layoutManager = linearLayoutManager
        photoList.itemAnimator = DefaultItemAnimator()
        photoList.setHasFixedSize(false)
        photoList.adapter = photosAdapter
        photosAdapter.setNetworkState(NetworkState.LOADED)
    }

    private fun addRecyclerPositionListener() {

        photoList.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override var isLoading: Boolean
                get() = isRecyclerLoading
                set(value) {
                    isRecyclerLoading = value
                    if (value) {
                        photosAdapter.setNetworkState(NetworkState.LOADING)
                    } else {
                        photosAdapter.setNetworkState(NetworkState.LOADED)
                    }
                }


            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1
                loadNextPage()
            }

        })

    }

    private fun loadNextPage() {
        (application as BaseApplication).restApi.getPictures(currentPage).enqueue(object : Callback<PhotosResponse> {
            override fun onFailure(call: Call<PhotosResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<PhotosResponse>?, response: Response<PhotosResponse>?) {
                val apiPhotos = response?.body()?.photos?.photo ?: emptyList()
                photos.addAll(apiPhotos)
                photosAdapter.notifyDataSetChanged()
                isRecyclerLoading = false
                photosAdapter.setNetworkState(NetworkState.LOADED)
            }
        })

    }

}
