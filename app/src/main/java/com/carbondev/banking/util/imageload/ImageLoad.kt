package com.carbondev.banking.util.imageload

//@Composable
//fun loadImage(
//    url: String?,
//    @DrawableRes defaultImage: Int
//): Painter {
//    val default = painterResource(id = defaultImage)
//    var painter by remember { mutableStateOf(default) }
//
//    url?.let {
//        LaunchedEffect(true) {
//            val bitmap = downloadOrMaybeCache(it)
//            if (bitmap != null) {
//                painter = BitmapPainter(bitmap.asImageBitmap())
//            }
//        }
//    }
//
//    return painter
//}

//suspend fun downloadOrMaybeCache(url: String, enableCash: Boolean = true): Bitmap? {
//
//    MemoryCache.get(url)?.let { bitmap ->
//        if (enableCash) return bitmap
//    }
//
//    downloadBitmap(url)?.let { bitmap ->
//        MemoryCache.put(url, bitmap)
//        return bitmap
//    }
//    return null
//}


