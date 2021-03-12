package com.example.openweathermap2.util

/**
 * @author dkpark
 */
object StringUtil {
    val STR_EMPTY_STRING: String? = ""

    /**
     * 주어진 문자열의 값이 널인 경우 blank로 변환한다.
     *
     * @param object
     * @return
     */
    fun getNvlStr(`object`: Any?): Any? {
        return `object` ?: STR_EMPTY_STRING
    }

    /**
     * 원 문자열의 값이 null인경우 해당 문자열로 치환한다.
     *
     * @param strSrcData
     * @param strReplaceData
     * @return
     */
    fun getNvlStr(strSrcData: String?, strReplaceData: String?): String? {
        return if (strSrcData == null || strSrcData == STR_EMPTY_STRING) {
            strReplaceData
        } else strSrcData
    }

    fun getNvlInt(strOrg: String?): Int {
        return if (strOrg == null || strOrg.trim { it <= ' ' }.length == 0) {
            0
        } else strOrg.toInt()
    }
}