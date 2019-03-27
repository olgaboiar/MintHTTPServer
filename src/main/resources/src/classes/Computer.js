const Player = require('./Player.js')
const Board = require('./Board.js')
var index, bestMove
class Computer extends Player {
  getMove (board) {
    // return this.randomMove(board)
    this.bestMove(board, 'x')
    return bestMove
  }

  randomMove (board) {
    let cell = null
    while (board.spots[cell] !== '') {
      cell = Math.floor(Math.random() * board.spots.length)
    }
    return cell
  }

  moveScore (board, lastMove, depth) {
    // console.log(this)
    if (this.gameRules.win(board) && lastMove === this.marker) {
      return 10 - depth
    }
    if (this.gameRules.win(board) && lastMove !== this.marker) {
      return depth - 10
    }
    if (this.gameRules.tie(board)) {
      return 0
    }
  }

  switchMarker (marker) {
    if (marker === 'x') {
      return 'o'
    } else {
      return 'x'
    }
  }

  bestMove (board, lastMove, depth = 0) {
    let self = this
    let moves = []
    let scores = []
    let currentMove = null
    if (this.gameRules.gameOver(board)) {
      return this.moveScore(board, lastMove, depth)
    }
    board.availableSpots().forEach(function (spot) {
      let potentialBoard = new Board()
      potentialBoard.spots = board.spots
      let initialValue = spot
      currentMove = self.switchMarker(lastMove)
      potentialBoard.setMove(spot, currentMove)
      scores.push(self.bestMove(potentialBoard, currentMove, depth + 1))
      moves.push(spot)
      potentialBoard.setMove(spot, initialValue)
    })
    if (currentMove === this.marker) {
      index = scores.indexOf(Math.max(...scores))
    } else {
      index = scores.indexOf(Math.min(...scores))
    }
    bestMove = moves[index]
    return scores[index]
  }
}

module.exports = Computer
