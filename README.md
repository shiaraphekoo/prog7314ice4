# CryptoTracker (Retrofit + OkHttp Android App)

## Why this API?
I chose the **CoinGecko** API because:
- It provides **real-time cryptocurrency market data** (prices, market cap, 24h change, icons) across thousands of coins. This data is highly relevant for users who want quick price checks, portfolio overviews, or alerts. (CoinGecko docs). :contentReference[oaicite:1]{index=1}
- CoinGecko is also listed in RapidAPI marketplace, so you can easily switch to a RapidAPI-hosted version if you want API key/usage tracking. :contentReference[oaicite:2]{index=2}
- The `/coins/markets` endpoint returns everything a mobile UI typically needs in one call (id, name, symbol, image, current_price, price_change_percentage_24h, market_cap, etc.). :contentReference[oaicite:3]{index=3}

## How the app is relevant
A mobile app can present:
- A fast list of top coins and their current price / 24h change.
- Drill-down screens (future work) with charts and historical data.
- Push notifications for price thresholds.
Mobile users typically want quick, glanceable data — CoinGecko provides the JSON payloads for that.

## Switching to RapidAPI
If you want to call CoinGecko through RapidAPI's wrapper (to track usage / require API key), change the base URL to the RapidAPI endpoint and add RapidAPI headers (`x-rapidapi-host`, `x-rapidapi-key`) in the OkHttp interceptor. The RapidAPI listing for CoinGecko is here. :contentReference[oaicite:4]{index=4}

## Quick start
1. Open the project in Android Studio.
2. Add `INTERNET` permission (already included).
3. Build & Run (min SDK 24 used in the sample).
4. Optionally: add your RapidAPI key in `NetworkModule.kt` if you want to route via RapidAPI.

## API docs
- CoinGecko docs (endpoint overview): https://docs.coingecko.com. :contentReference[oaicite:5]{index=5}
- RapidAPI listing (optional): https://rapidapi.com/coingecko/api/coingecko. :contentReference[oaicite:6]{index=6}
