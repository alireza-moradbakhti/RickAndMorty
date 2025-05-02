package com.example.rickandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.data.local.Database
import com.example.rickandmorty.data.paging.RemoteMediator
import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class CharacterRepositoryImpl (
    private val api: ApiService,
    private val db: Database
) : CharacterRepository {
    override fun getCharactersByName(characterName: String): Flow<PagingData<Character>> {
        val pagingSourceFactory = { db.characterDao.getCharactersByName(characterName) }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true,
            ),
            remoteMediator = RemoteMediator(
                api,
                db,
                characterName
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { characterEntityPagingData ->
            characterEntityPagingData.map { characterEntity -> characterEntity.toCharacter() }
        }
    }


}