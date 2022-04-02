package com.carbondev.banking.util


fun String.nameIsValid(): Boolean {
    return isNotEmpty()
}
fun String.usernameIsValid(): Boolean {
    val r = "^[a-zA-Z0-9._]+$".toRegex()
    return matches(r)
}
fun String.emailIsValid(): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return matches(emailPattern)
}
fun String.phoneIsValid(): Boolean {
    return isNotEmpty()
}
fun String.passwordIsValid(): Boolean {
    return length >= 6
}
fun String.sponsorIdIsValid(): Boolean {
    return isNotEmpty()
}
fun String.referIdIsValid(): Boolean {
    return isNotEmpty()
}

fun String.nidIsValid(): Boolean {
    return isNotEmpty()//length >= 6// && !contains(" ")
}

