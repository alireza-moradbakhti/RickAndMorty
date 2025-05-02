package com.example.rickandmorty.presentation.characterdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.domain.model.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var character: Character

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterDetailBinding.bind(view)
        getArgs()
    }

    private fun getArgs() {
        character = args.character
        bindCharacterDetails()
    }

    private fun bindCharacterDetails() {
        binding.apply {
            character.let {
                tvCharacterName.text = it.name
                tvCharacterSpecies.text = it.species
                tvCharacterGender.text = it.gender
                tvCharacterStatus.text = it.status
                ivCharacterProfile.load(it.image)
                tvCharacterOriginLocation.text = it.origin
                tvCharacterLastLocation.text = it.location
            }
        }
    }

}
