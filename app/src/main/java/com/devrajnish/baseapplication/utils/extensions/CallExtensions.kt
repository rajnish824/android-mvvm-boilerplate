package com.devrajnish.baseapplication.utils.extensions

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

fun AppCompatActivity.callTo(phoneNumber: String, requestCode: Int) {
    val intent = Intent(Intent.ACTION_CALL)

    intent.data = Uri.parse("tel:$phoneNumber")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOfNulls<String>(1)
            permissions[0] = Manifest.permission.CALL_PHONE
            requestPermissions(permissions, requestCode)
        } else {
            startActivity(intent)
        }
    } else {
        startActivity(intent)
    }
}