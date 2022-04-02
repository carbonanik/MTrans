package com.carbondev.banking.domail.use_case

//class GetProfileImageUseCase @Inject constructor(
//    private val userDataRepository: UserDataRepository,
//    @ApplicationContext private val context: Context
//) {
//    val profileImageUrl = "http://103.84.173.185/storage/profile/"
    //7.png

//    operator fun invoke(maybeFromCache: Boolean = true, defaultResId: Int = R.drawable.female_profile) = flow<Bitmap>{
//
//        val dBit = BitmapFactory.decodeResource(context.resources, defaultResId)
//        if (dBit != null) emit(dBit)
//
//        val bit = userDataRepository.getUser()?.photo?.let {
//            downloadOrMaybeCache(profileImageUrl+it, maybeFromCache)
//        }
//        if (bit != null) emit(bit)
//    }
//}