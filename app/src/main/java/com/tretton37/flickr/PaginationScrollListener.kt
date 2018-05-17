package com.tretton37.flickr

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


abstract class PaginationScrollListener(internal var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    abstract var isLoading: Boolean

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
}