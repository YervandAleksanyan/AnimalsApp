package com.task.animalsapp.core.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.task.animalsapp.core.BuildConfig
import com.task.animalsapp.core.providers.IConfigurationProvider
import com.task.animalsapp.core.services.IAnimalsRetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient(get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideRetrofit(get(), get()) }
    factory { provideAnimalsRetrofitService(get()) }
}


fun provideAnimalsRetrofitService(retrofit: Retrofit): IAnimalsRetrofitService {
    return retrofit.create(IAnimalsRetrofitService::class.java)
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    provider: IConfigurationProvider
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(provider.apiBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(httpLoggingInterceptor)

    return builder.build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return loggingInterceptor
}