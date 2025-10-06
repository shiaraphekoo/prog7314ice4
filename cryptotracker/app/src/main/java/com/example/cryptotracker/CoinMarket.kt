package com.example.cryptotracker

import com.google.gson.annotations.SerializedName

data class CoinMarket(
    val id: String,
    val symbol: String,
    val name: String,
    @SerializedName("image") val imageUrl: String?,
    @SerializedName("current_price") val currentPrice: Double?,
    @SerializedName("market_cap") val marketCap: Long?,
    @SerializedName("price_change_percentage_24h") val priceChange24h: Double?
)
