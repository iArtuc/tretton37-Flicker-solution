package com.tretton37.flickr.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PhotoItem(

        @field:SerializedName("server")
        val server: String? = null,

        @field:SerializedName("width_c")
        val widthC: String? = null,

        @field:SerializedName("isfriend")
        val isfriend: Int? = null,

        @field:SerializedName("datetakengranularity")
        val datetakengranularity: String? = null,

        @field:SerializedName("description")
        val description: Description? = null,

        @field:SerializedName("secret")
        val secret: String? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("url_z")
        val urlZ: String? = null,

        @field:SerializedName("height_l")
        val heightL: String? = null,

        @field:SerializedName("url_sq")
        val urlSq: String? = null,

        @field:SerializedName("height_n")
        val heightN: Int? = null,

        @field:SerializedName("height_o")
        val heightO: String? = null,

        @field:SerializedName("datetaken")
        val datetaken: String? = null,

        @field:SerializedName("height_c")
        val heightC: Int? = null,

        @field:SerializedName("ispublic")
        val ispublic: Int? = null,

        @field:SerializedName("farm")
        val farm: Int? = null,

        @field:SerializedName("width_l")
        val widthL: String? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("width_n")
        val widthN: String? = null,

        @field:SerializedName("width_o")
        val widthO: String? = null,

        @field:SerializedName("owner")
        val owner: String? = null,

        @field:SerializedName("width_sq")
        val widthSq: Int? = null,

        @field:SerializedName("ownername")
        val ownername: String? = null,

        @field:SerializedName("width_z")
        val widthZ: String? = null,

        @field:SerializedName("isfamily")
        val isfamily: Int? = null,

        @field:SerializedName("tags")
        val tags: String? = null,

        @field:SerializedName("height_sq")
        val heightSq: Int? = null,

        @field:SerializedName("url_c")
        val urlC: String? = null,

        @field:SerializedName("url_n")
        val urlN: String? = null,

        @field:SerializedName("url_o")
        val urlO: String? = null,

        @field:SerializedName("height_z")
        val heightZ: String? = null,

        @field:SerializedName("url_l")
        val urlL: String? = null,

        @field:SerializedName("datetakenunknown")
        val datetakenunknown: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readParcelable(Description::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(server)
        parcel.writeString(widthC)
        parcel.writeValue(isfriend)
        parcel.writeString(datetakengranularity)
        parcel.writeParcelable(description, flags)
        parcel.writeString(secret)
        parcel.writeString(title)
        parcel.writeString(urlZ)
        parcel.writeString(heightL)
        parcel.writeString(urlSq)
        parcel.writeValue(heightN)
        parcel.writeString(heightO)
        parcel.writeString(datetaken)
        parcel.writeValue(heightC)
        parcel.writeValue(ispublic)
        parcel.writeValue(farm)
        parcel.writeString(widthL)
        parcel.writeString(id)
        parcel.writeString(widthN)
        parcel.writeString(widthO)
        parcel.writeString(owner)
        parcel.writeValue(widthSq)
        parcel.writeString(ownername)
        parcel.writeString(widthZ)
        parcel.writeValue(isfamily)
        parcel.writeString(tags)
        parcel.writeValue(heightSq)
        parcel.writeString(urlC)
        parcel.writeString(urlN)
        parcel.writeString(urlO)
        parcel.writeString(heightZ)
        parcel.writeString(urlL)
        parcel.writeString(datetakenunknown)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhotoItem> {
        override fun createFromParcel(parcel: Parcel): PhotoItem {
            return PhotoItem(parcel)
        }

        override fun newArray(size: Int): Array<PhotoItem?> {
            return arrayOfNulls(size)
        }
    }
}