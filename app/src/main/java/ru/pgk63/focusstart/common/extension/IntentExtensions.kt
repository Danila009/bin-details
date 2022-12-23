package ru.pgk63.focusstart.common.extension

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openBrowser(url: String) = this.startActivity(Intent(Intent.ACTION_VIEW)
    .setData(Uri.parse(if(url.startsWith("https://")
        || url.startsWith("http://")) url else "https://$url")))

fun Context.openPhone(numberPhone: String) =
    this.startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$numberPhone")))

fun Context.openMap(latitude:Double, longitude: Double) =
    this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude,$longitude")))