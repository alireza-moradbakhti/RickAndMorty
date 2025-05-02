package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.model.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page")
        page: Int
    ): CharacterResponseDto

    @GET("character/")
    suspend fun getCharactersByName(
        @Query("page")
        page: Int,
        @Query("name")
        characterName: String
    ): CharacterResponseDto
}