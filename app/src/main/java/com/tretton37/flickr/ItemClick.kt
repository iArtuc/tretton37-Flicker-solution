package com.tretton37.flickr

import com.tretton37.flickr.model.PhotoItem

interface ItemClick {

    fun clickedItem(photoItem: PhotoItem)
}