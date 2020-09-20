package jp.wasabeef.transformations.glide.gpu

/**
 * Copyright (C) 2020 Wasabeef
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import jp.wasabeef.transformations.glide.BitmapTransformation
import java.security.MessageDigest

abstract class GPUFilterTransformation(private val filter: GPUImageFilter) :
  BitmapTransformation() {

  val version: String = jp.wasabeef.transformations.glide.BuildConfig.Version

  protected val id: String
    get() = "${this::class.java.name}-${version}"

  override fun transform(
    context: Context,
    pool: BitmapPool,
    source: Bitmap,
    outWidth: Int,
    outHeight: Int
  ): Bitmap {
    val gpuImage = GPUImage(context)
    gpuImage.setImage(source)
    gpuImage.setFilter(filter)
    return gpuImage.bitmapWithFilterApplied
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> filter(): T = filter as T

  override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    messageDigest.update(key().toByteArray(Key.CHARSET))
  }

  abstract fun key(): String

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false

    o as GPUFilterTransformation

    if (filter != o.filter) return false
    if (version != o.version) return false

    return true
  }
}