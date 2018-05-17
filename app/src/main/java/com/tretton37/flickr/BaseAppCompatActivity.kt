package com.tretton37.flickr

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseAppCompatActivity : AppCompatActivity() {

    protected val subscriptions = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.unSubscribeIfNotNull()
    }

}
