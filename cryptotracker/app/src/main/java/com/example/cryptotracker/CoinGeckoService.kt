package com.example.cryptotracker

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoService {
    // GET /coins/markets?vs_currency=usd&order=market_cap_desc&per_page=50&page=1&sparkline=false
    @GET("coins/markets")
    suspend fun getCoinsMarkets(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 50,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("price_change_percentage") priceChange: String = "24h"
    ): List<CoinMarket>
}
