package com.example.rickandmorty.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.example.rickandmorty.core.utils.AppConstants
import com.example.rickandmorty.data.local.Database
import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.data.repository.CharacterRepositoryImpl
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.domain.usecase.GetCharactersByName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGetCharactersByNameUseCase(
        getCharactersRepository: CharacterRepository
    ): GetCharactersByName = GetCharactersByName(getCharactersRepository)

    @Singleton
    @Provides
    @ExperimentalPagingApi
    fun provideCharacterRepository(
        api: ApiService,
        db: Database
    ): CharacterRepository = CharacterRepositoryImpl(api, db)

    @Singleton
    @Provides
    fun provideRickAndMortyApi(): ApiService =
        Retrofit.Builder().baseUrl(AppConstants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        )
            .build().create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRickAndMortyDatabase(
        app: Application
    ): Database =
        Room.databaseBuilder(app, Database::class.java, "rickAndMortyDb").build()

}