class GameRules {
  horizontalWin (board) {
    return (board.spots[0] === board.spots[1] && board.spots[0] === board.spots[2] && board.spots[0] !== '') ||
    (board.spots[3] === board.spots[4] && board.spots[4] === board.spots[5] && board.spots[3] !== '') ||
    (board.spots[6] === board.spots[7] && board.spots[7] === board.spots[8] && board.spots[6] !== '')
  }

  verticalWin (board) {
    return (board.spots[0] === board.spots[3] && board.spots[3] === board.spots[6] && board.spots[0] !== '') ||
    (board.spots[1] === board.spots[4] && board.spots[4] === board.spots[7] && board.spots[1] !== '') ||
    (board.spots[2] === board.spots[5] && board.spots[5] === board.spots[8] && board.spots[2] !== '')
  }

  diagonalWin (board) {
    return (board.spots[0] === board.spots[4] && board.spots[4] === board.spots[8] && board.spots[0] !== '') ||
    (board.spots[2] === board.spots[4] && board.spots[4] === board.spots[6] && board.spots[2] !== '')
  }

  win (board) {
    return this.diagonalWin(board) || this.verticalWin(board) || this.horizontalWin(board)
  }

  tie (board) {
    return !this.win(board) && board.availableSpots().length === 0
  }

  gameOver (board) {
    return this.win(board) || this.tie(board)
  }
}

module.exports = GameRules
