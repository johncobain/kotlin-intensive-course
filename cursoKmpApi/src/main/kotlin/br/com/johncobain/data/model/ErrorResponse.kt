package br.com.johncobain.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    val message: String
)