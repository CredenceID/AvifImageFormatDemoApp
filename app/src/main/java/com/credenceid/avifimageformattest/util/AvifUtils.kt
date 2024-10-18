package com.credenceid.avifimageformattest.util

import android.content.Context
import android.graphics.Bitmap
import com.radzivon.bartoshyk.avif.coder.HeifCoder
import com.radzivon.bartoshyk.avif.coder.ToneMapper

fun decodeAvifFile(imageRawBytes: ByteArray, context: Context):Bitmap {
    return HeifCoder(context, ToneMapper.LOGARITHMIC).decode(imageRawBytes)
}