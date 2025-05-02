package com.example.rickandmorty.presentation.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.core.enums.CharacterStatusEnums
import com.example.rickandmorty.databinding.ItemCharacterBinding
import androidx.core.graphics.toColorInt


class CharacterListAdapter :
    PagingDataAdapter<com.example.rickandmorty.domain.model.Character, CharacterListAdapter.CharacterListViewHolder>(
        CharacterComparator()
    ) {
    var characterClickListener: CharacterClickListener? = null

    inner class CharacterListViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            characterClickListener
            itemView.setOnClickListener {
                characterClickListener?.onCharacterClicked(
                    getItem(absoluteAdapterPosition)
                )
            }
        }

        fun bindCharacter(character: com.example.rickandmorty.domain.model.Character) {
            binding.apply {
                tvCharacterName.text = character.name
                tvCharacterStatus.text = character.status
                ivCharacterProfile.load(character.image)
                tvCharacterGender.text = character.gender

                when (character.status) {
                    CharacterStatusEnums.CHARACTER_ALIVE.value -> imgCharacterStatus.setColorFilter(
                        "#14D91B".toColorInt()
                    )

                    CharacterStatusEnums.CHARACTER_DEAD.value -> imgCharacterStatus.setColorFilter(
                        "#FF0800".toColorInt()
                    )

                    CharacterStatusEnums.CHARACTER_UNKNOWN.value -> imgCharacterStatus.setColorFilter(
                        "#E3E3E3".toColorInt()
                    )

                    else -> {
                        imgCharacterStatus.setColorFilter("#F8F816".toColorInt())
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        getItem(position)?.let { holder.bindCharacter(it) }
    }

    class CharacterComparator :
        DiffUtil.ItemCallback<com.example.rickandmorty.domain.model.Character>() {
        override fun areItemsTheSame(
            oldItem: com.example.rickandmorty.domain.model.Character,
            newItem: com.example.rickandmorty.domain.model.Character
        ) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: com.example.rickandmorty.domain.model.Character,
            newItem: com.example.rickandmorty.domain.model.Character
        ) =
            oldItem == newItem
    }

    interface CharacterClickListener {
        fun onCharacterClicked(character: com.example.rickandmorty.domain.model.Character?)
    }
}
