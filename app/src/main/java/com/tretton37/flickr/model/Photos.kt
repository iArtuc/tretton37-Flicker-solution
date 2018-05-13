package com.tretton37.flickr.model

import com.google.gson.annotations.SerializedName

data class Photos(

        @field:SerializedName("perpage")
        val perpage: Int? = null,

        @field:SerializedName("total")
        val total: String? = null,

        @field:SerializedName("pages")
        val pages: Int? = null,

        @field:SerializedName("photo")
        val photo: List<PhotoItem>,

        @field:SerializedName("page")
        val page: Int? = null
)