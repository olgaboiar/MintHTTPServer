class Player {
  constructor (marker, ui, symbol, gameRules) {
    this.marker = marker
    this.ui = ui
    this.symbol = symbol
    this.gameRules = gameRules
  }

  move (board, cell) {
    board.setMove(cell, this.marker)
  }
}

module.exports = Player
