package com.tretton37.flickr

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.custom_loading_item.view.*

class NetworkStateViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    fun bindTo(networkState: NetworkState?) {
        itemView.loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup): NetworkStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.custom_loading_item, parent, false)
            return NetworkStateViewHolder(view)
        }
    }
}