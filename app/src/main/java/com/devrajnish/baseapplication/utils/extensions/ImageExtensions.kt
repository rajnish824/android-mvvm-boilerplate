package com.devrajnish.baseapplication.utils.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.media.ExifInterface
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun ImageView.loadUrl(
        url: String,
        isRounded: Boolean = false,
        placeholder: Int? = null,
        radius: Int? = null
) {
    Glide.with(this).load(url).apply {
        if (isRounded) circleCrop()
        placeholder?.let { placeholder(placeholder) }
        radius?.let { transform(CenterCrop(), RoundedCorners(radius)) }
    }.into(this)
}

fun ImageView.loadUrlWithThumbnail(
        url: String,
        thumbUrl: String,
        isRounded: Boolean = false,
        placeholder: Int? = null,
        radius: Int? = null
) {

    val thumbnailRequest: RequestBuilder<Drawable> = Glide
            .with(context)
            .load(thumbUrl).apply { radius?.let { transform(CenterCrop(), RoundedCorners(radius)) } }

    Glide.with(this).load(url).apply {
        if (isRounded) circleCrop()
        thumbnail(thumbnailRequest)
        placeholder?.let { placeholder(placeholder) }
        radius?.let { transform(CenterCrop(), RoundedCorners(radius)) }
    }.into(this)
}

fun ImageView.loadUri(uri: Uri, isRounded: Boolean = false) {
    Glide.with(this).load(uri).apply {
        if (isRounded) circleCrop()
    }.into(this)
}

fun ImageView.loadFile(file: File, isRounded: Boolean = false, placeholder: Int? = null) {
    Glide.with(this).load(file).apply {
        if (isRounded) circleCrop()
        placeholder?.let { placeholder(placeholder) }
    }.into(this)
}

fun ImageView.loadBitmap(
        bitmap: Bitmap,
        isRounded: Boolean = false,
        placeholder: Int? = null,
        radius: Int? = null
) {
    Glide.with(this).asBitmap().load(bitmap).apply {
        if (isRounded) circleCrop()
        placeholder?.let { placeholder(placeholder) }
        radius?.let { transform(CenterCrop(), RoundedCorners(radius)) }
    }.into(this)
}

fun ImageView.loadVector(resourceId: Int) {
    Glide.with(this).load(resourceId).into(this)
}

fun ImageView.loadRoundedUrl(
        url: String,
        isRounded: Boolean = false,
        placeholder: Int? = null,
        radius: Int
) {
    Glide.with(this).load(url).apply {
        if (isRounded) circleCrop()
        placeholder?.let { placeholder(placeholder) }
        transform(RoundedCorners(radius))
    }.into(this)
}

fun compressImage(file: File): File? {
    return try {
        val exif = ExifInterface(file.absoluteFile.toString())
        val o = BitmapFactory.Options()
        o.inJustDecodeBounds = true
        o.inSampleSize = 6
        var inputStream = FileInputStream(file)
        BitmapFactory.decodeStream(inputStream, null, o)
        inputStream.close()

        val requiredSize = 75

        var scale = 1
        while (o.outWidth / scale / 2 >= requiredSize &&
            o.outHeight / scale / 2 >= requiredSize
        ) {
            scale *= 2
        }
        val o2 = BitmapFactory.Options()
        o2.inSampleSize = scale
        inputStream = FileInputStream(file)
        val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
        inputStream.close()

        // here i override the original image file
        file.createNewFile()
        val outputStream = FileOutputStream(file)
        selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)

        val newExif = ExifInterface(file.path)
        newExif.setAttribute(
            ExifInterface.TAG_ORIENTATION,
            exif.getAttribute(ExifInterface.TAG_ORIENTATION)
        )
        newExif.saveAttributes()

        file
    } catch (e: java.lang.Exception) {
        null
    }
}