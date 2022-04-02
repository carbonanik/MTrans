package com.carbondev.banking.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Msg(
    val name: List<String>? = null,
    val username: List<String>? = null,
    val password: List<String>? = null,
    val phone_no: List<String>? = null,
    val nid: List<String>? = null,
    val subscription_type: List<String>? = null,
    val refer_id: List<String>? = null,
    val sponsor_id: List<String>? = null
) {
    override fun toString(): String {
        val list = listOf(name, username, password, phone_no,
            nid, subscription_type, refer_id, sponsor_id, )

        var returnStr= ""
        list.forEach {
            returnStr += addToString(it)
        }
        return returnStr
    }

    private fun addToString(ls: List<String>?): String {
        var str = ""
        ls?.forEach {
            str += it + "\n"
        }
        return str
    }
}