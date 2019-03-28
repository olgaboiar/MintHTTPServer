const Computer = require('./Computer.js')
const Human = require('./Human.js')

class Game {
  constructor (gameRules, board, ui) {
    this.currentPlayer = null
    this.nextPlayer = null
    this.player1 = null
    this.player2 = null
    this.gameRules = gameRules
    this.board = board
    this.ui = ui
  }

  printBoard () {
    this.ui.printBoard()
  }

  setCurrentPlayer (player1, player2) {
    if (player1.marker === 'x') {
      this.currentPlayer = player1
      this.nextPlayer = player2
    } else {
      this.currentPlayer = player2
      this.nextPlayer = player1
    }
  }

  switch (currentPlayer, nextPlayer) {
    [currentPlayer, nextPlayer] = [nextPlayer, currentPlayer]
  }

  createPlayers (humanSymbol, computerSymbol, gameRules) {
    this.player1 = new Human('x', this.ui, humanSymbol)
    this.player2 = new Computer('o', this.ui, computerSymbol, gameRules)
  }

  play (board, currentPlayer, nextPlayer) {
    // console.log('game play')
    // if (!this.gameRules.gameOver(board)) {
    //   console.log('game not over')
    //   currentPlayer.move(board)
    //   this.switch(currentPlayer, nextPlayer)
    // }
    this.ui.play(board, currentPlayer, nextPlayer)
  }
}

module.exports = Game
