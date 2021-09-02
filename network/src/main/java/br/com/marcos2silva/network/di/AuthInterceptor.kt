package br.com.marcos2silva.network.di

import br.com.marcos2silva.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

private const val PROPERTY_API_KEY = "apikey"
private const val PROPERTY_TS = "ts"
private const val PROPERTY_HASH = "hash"

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
        val apiKey = BuildConfig.API_KEY
        val privateKey = BuildConfig.PRIVATE_KEY

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(PROPERTY_API_KEY, apiKey)
            .addQueryParameter(PROPERTY_TS, ts)
            .addQueryParameter(PROPERTY_HASH, "$ts$privateKey$apiKey".md5())
            .build()

        return chain.proceed(original.newBuilder().url(url).build())
    }
}