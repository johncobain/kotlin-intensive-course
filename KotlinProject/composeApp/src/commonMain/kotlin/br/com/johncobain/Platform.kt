package br.com.johncobain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform