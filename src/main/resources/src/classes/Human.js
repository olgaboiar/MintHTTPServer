const Player = require('./Player.js')

class Human extends Player {
  move (board) {
    let cell = this.getMove(board)
    board.setMove(cell, this.marker)
  }

  getMove (board) {
    this.ui.getMove(board)
  }
}

module.exports = Human
