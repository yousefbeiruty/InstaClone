package com.it.yousefl.instaclone

import android.app.Application
import android.os.Build.VERSION.SDK_INT
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

class InstaCloneApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Coil.setImageLoader(ImageLoader.Builder(this@InstaCloneApp).componentRegistry {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder(this@InstaCloneApp))
            } else {
                add(GifDecoder())
            }
        }.build())
    }
}