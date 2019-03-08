package com.example.lastfmclient.di;

import android.app.Application;

import com.example.lastfmclient.common.Constants;
import com.example.lastfmclient.data.repo.DataSource;
import com.example.lastfmclient.data.repo.RemoteDataSource;
import com.example.lastfmclient.net.LastFMService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public Cache provideCache(Application application) {
        return new Cache(application.getCacheDir(), Constants.CACHE_SIZE);
    }

    @Provides
    @LoggingInterceptor
    @Singleton
    public Interceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @ApiInterceptor
    @Singleton
    public Interceptor provideApiInterceptor() {
        return chain -> {
            HttpUrl originalUrl = chain.request().url();
            HttpUrl newUrl = originalUrl.newBuilder()
                    .addQueryParameter("api_key", Constants.API_KEY)
                    .build();
            Request request = chain.request().newBuilder()
                    .url(newUrl)
                    .build();
            return chain.proceed(request);
        };
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Cache cache,
                                            @LoggingInterceptor Interceptor httpLoggingInterceptor,
                                            @ApiInterceptor Interceptor apiInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(apiInterceptor)
                .connectTimeout(Constants.API_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.API_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public LastFMService provideLastFMService(Retrofit retrofit) {
        return retrofit.create(LastFMService.class);
    }

    @Provides
    @Remote
    @Singleton
    public DataSource provideRemoteDataSource(LastFMService lastFMService) {
        return new RemoteDataSource(lastFMService);
    }

}
