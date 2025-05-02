package com.example.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Query("Select * from rick_and_morty_db where name LIKE '%' || :characterName || '%'")
    fun getCharactersByName(characterName: String): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM rick_and_morty_db")
    suspend fun deleteCharacters()
}
