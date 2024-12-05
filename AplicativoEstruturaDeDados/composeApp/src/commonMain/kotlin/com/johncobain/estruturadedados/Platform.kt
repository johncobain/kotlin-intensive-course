package com.johncobain.estruturadedados

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform