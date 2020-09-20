package jp.wasabeef.transformations.coil

import android.graphics.Bitmap
import coil.bitmap.BitmapPool
import coil.size.Size
import coil.transform.Transformation
import jp.wasabeef.transformations.core.Grayscale
import jp.wasabeef.transformations.core.bitmapConfig

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

class GrayscaleTransformation : Transformation {

  private val grayscale = Grayscale()

  override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
    val output = pool.get(input.width, input.height, bitmapConfig(input))
    return grayscale.transform(input, output)
  }

  override fun key() = grayscale.key()

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false

    o as GrayscaleTransformation

    if (grayscale != o.grayscale) return false

    return true
  }

  override fun hashCode(): Int = grayscale.key().hashCode()
}