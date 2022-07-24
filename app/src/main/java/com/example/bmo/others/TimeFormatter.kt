package com.example.bmo.others

class TimeFormatter {

    fun format(value: String) =
        value.split("T").map {
            it.replace("Z", "UTC")
        }
}