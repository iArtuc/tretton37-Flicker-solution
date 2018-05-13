package com.tretton37.flickr

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tretton37.flickr.model.PhotoItem
import kotlinx.android.synthetic.main.activity_photo_detail.*

class PhotoDetailActivity : AppCompatActivity() {

    companion object {
        const val PHOTO_ITEM = "PHOTO_ITEM"

        fun newIntent(context: Context, photoItem: PhotoItem): Intent {
            val intent = Intent(context, PhotoDetailActivity::class.java)
            intent.putExtra(PHOTO_ITEM, photoItem)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        val photoItem = intent.extras.getParcelable<PhotoItem>(PHOTO_ITEM)

        Glide.with(this)
                .load(photoItem.urlO)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(photoView)
    }


}



