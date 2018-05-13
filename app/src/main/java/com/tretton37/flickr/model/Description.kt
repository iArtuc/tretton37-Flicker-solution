package com.tretton37.flickr.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Description(

        @field:SerializedName("_content")
        val content: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Description> {
        override fun createFromParcel(parcel: Parcel): Description {
            return Description(parcel)
        }

        override fun newArray(size: Int): Array<Description?> {
            return arrayOfNulls(size)
        }
    }
}