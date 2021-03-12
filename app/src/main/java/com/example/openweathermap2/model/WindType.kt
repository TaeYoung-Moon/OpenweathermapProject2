package com.example.openweathermap2.model

enum class WindType(var code: Int, var direction: String) {
    N0(0, "북"),
    NNE(1, "북북동"),
    NE(2, "북동"),
    ENE(3, "동북동"),
    E(4, "동"),
    ESE(5, "동남동"),
    SE(6, "남동"),
    SSE(7, "남남동"),
    S(8, "남"),
    SSW(9, "남남서"),
    SW(10, "남서"),
    WSW(11, "서남서"),
    W(12, "서"),
    WNW(13, "서북서"),
    NW(14, "북서"),
    NNW(15, "북북서"),
    N16(16, "북");

    companion object {
        fun value(value: Int): WindType? {
            for (type in values()) {
                if (type.code == value) {
                    return type
                }
            }
            return null
        }
    }
}