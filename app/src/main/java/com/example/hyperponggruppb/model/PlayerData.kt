package com.example.hyperponggruppb.model

data class PlayerData(
    var name: String,
    var highScore: Int,
    var currentLevel: Int,
    var nextLevel: Int,
    var coins: Int,
    ){
    var levelScoresArray = mutableListOf<Int>()
    var levelStarsArray = mutableListOf<Int>()
    var powerUpInventory = listOf<Int>()
}

