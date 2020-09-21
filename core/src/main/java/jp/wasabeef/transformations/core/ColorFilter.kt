package jp.wasabeef.transformations.core

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.annotation.ColorInt

/**
 * Copyright (C) 2020 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class ColorFilter constructor(@ColorInt private val color: Int) : Transformation() {

  override fun transform(
    source: Bitmap,
    destination: Bitmap
  ): Bitmap {

    destination.density = source.density

    val paint = Paint().apply {
      isAntiAlias = true
      isFilterBitmap = true
      colorFilter = PorterDuffColorFilter(this@ColorFilter.color, PorterDuff.Mode.SRC_ATOP)
    }
    val canvas = Canvas(destination).apply {
      drawBitmap(source, 0f, 0f, paint)
    }
    canvas.setBitmap(null)

    return destination
  }

  override fun key(): String = "$id(color=$color)"
}