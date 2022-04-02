package com.carbondev.banking.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.carbondev.banking.repository.UserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProfileImage @Inject constructor(
    private val userDataRepository: UserDataRepository
) {

    private var image: Bitmap? = null

    suspend fun downloadBitmap() = withContext(Dispatchers.IO) {
        val bitmap = try {
            val photoName = userDataRepository.getUser()?.photo
            val profileUrl = "http://103.84.173.185/storage/profile/"

            val urlObj = URL(profileUrl + photoName)
            val conn: HttpURLConnection = urlObj.openConnection() as HttpURLConnection
            val b = BitmapFactory.decodeStream(conn.inputStream)
            conn.disconnect(); b

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        bitmap?.let { image = it }
        return@withContext bitmap
    }

    fun getProfilePhoto(): Bitmap? {
        return image
    }

}