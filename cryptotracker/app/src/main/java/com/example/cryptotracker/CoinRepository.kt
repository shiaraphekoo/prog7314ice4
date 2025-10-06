package com.example.cryptotracker

class CoinRepository(private val service: CoinGeckoService) {
    suspend fun fetchTopCoins(): Result<List<CoinMarket>> {
        return try {
            val response = service.getCoinsMarkets()
            Result.success(response)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}
