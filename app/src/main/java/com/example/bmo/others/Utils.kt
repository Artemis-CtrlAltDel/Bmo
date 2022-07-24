package com.example.bmo.others

val formatter = NumberFormatter()
fun Long.format() = formatter.format(this)