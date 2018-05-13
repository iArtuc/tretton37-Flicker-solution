package com.tretton37.flickr;

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tretton37.flickr.model.PhotoItem
import kotlinx.android.synthetic.main.custom_photo_item.view.*
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class PhotoAdapter(private val items: List<PhotoItem>, private val itemClick: ItemClick) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var networkState: NetworkState? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.custom_photo_item -> (holder as PhotosViewHolder).bindView(items[position])
            R.layout.custom_loading_item -> (holder as NetworkStateViewHolder).bindTo(networkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.custom_photo_item -> PhotosViewHolder(parent.inflate(R.layout.custom_photo_item))
            R.layout.custom_loading_item -> NetworkStateViewHolder.create(parent)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }


    override fun getItemCount(): Int {
        return items.count() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.custom_loading_item
        } else {
            R.layout.custom_photo_item
        }
    }

    inner class PhotosViewHolder(private val v: View) :
            RecyclerView.ViewHolder(v) {

        fun bindView(item: PhotoItem) {

            Glide.with(itemView.context)
                    .load(item.urlO)
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                    .into(itemView.image)

            itemView.owner.text = item.ownername
            itemView.date.text = item.datetaken?.convertStringToDate()?.formatDate()
            itemView.photoTags.text = item.tags?.addHasToTags()

            itemView.rootView.setOnClickListener {
                itemClick.clickedItem(item)
            }
        }
    }

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    fun Date.formatDate(): String {
        val format: DateFormat = SimpleDateFormat("MMM d, YYYY", Locale.ENGLISH)
        try {
            return format.format(this)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

    fun String.convertStringToDate(): Date? {

        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        try {
            return format.parse(this)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    fun String.addHasToTags(): String {
        val separatedTags = this.split(" ")
        return separatedTags.joinToString(" ") { "#$it" }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        if (items.isNotEmpty()) {
            val previousState = this.networkState
            val hadExtraRow = hasExtraRow()
            this.networkState = newNetworkState
            val hasExtraRow = hasExtraRow()
            if (hadExtraRow != hasExtraRow) {
                if (hadExtraRow) {
                    notifyItemRemoved(itemCount)
                } else {
                    notifyItemInserted(itemCount)
                }
            } else if (hasExtraRow && previousState !== newNetworkState) {
                notifyItemChanged(itemCount - 1)
            }
        }
    }
}

