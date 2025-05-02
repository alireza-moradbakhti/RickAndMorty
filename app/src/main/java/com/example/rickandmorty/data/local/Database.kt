package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.local.dao.CharacterDao
import com.example.rickandmorty.data.local.entity.CharacterEntity
import com.example.rickandmorty.data.local.entity.RemoteKeyEntity
import com.example.rickandmorty.data.local.dao.RemoteKeyDao

@Database(entities = [CharacterEntity::class, RemoteKeyEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val characterDao: CharacterDao
    abstract val remoteKeyDao: RemoteKeyDao
}
