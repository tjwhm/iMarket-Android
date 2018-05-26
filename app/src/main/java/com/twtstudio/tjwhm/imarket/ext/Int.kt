package com.twtstudio.tjwhm.imarket.ext

import java.util.*

fun random(min: Int, max: Int): Int {
    val rand = Random()
    return rand.nextInt((max - min) + 1) + min
}