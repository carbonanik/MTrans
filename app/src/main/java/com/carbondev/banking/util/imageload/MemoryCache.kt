package com.carbondev.banking.util.imageload

//object MemoryCache{
//
//    private val maxMemory = Runtime.getRuntime().maxMemory() / 1024
//    private val cacheSize = (maxMemory /10).toInt()
//
//    private val cache = object : LruCache<String, Bitmap>(cacheSize){
//        override fun sizeOf(key: String, value: Bitmap): Int {
//            return value.rowBytes * value.height / 1024
//        }
//    }
//
//    fun put(url: String, bitmap: Bitmap) {
//        cache.put(url, bitmap)
//    }
//
//    fun get(url: String): Bitmap? {
//        return cache.get(url)
//    }
//
//    fun clear() {
//        cache.evictAll()
//    }
//}