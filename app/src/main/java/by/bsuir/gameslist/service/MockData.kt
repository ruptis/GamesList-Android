package by.bsuir.gameslist.service

import by.bsuir.gameslist.model.Game

class MockData {
    var games = (1..30).map { Game.mockGame(it.toString()) }
}