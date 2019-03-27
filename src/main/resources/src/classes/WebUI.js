class WebUI {
  constructor (gameRules) {
    this.board = null
    this.htmlCells = null
    this.gameRules = gameRules
  }

  setBoard () {
    this.board = document.getElementById('board')
  }

  printBoard () {
    this.setBoard()
    this.board.innerHTML = '<div class="board_cell cell-0"></div>' +
                                '<div class="board_cell cell-1"></div>' +
                                '<div class="board_cell cell-2"></div>' +
                                '<div class="board_cell cell-3"></div>' +
                                '<div class="board_cell cell-4"></div>' +
                                '<div class="board_cell cell-5"></div>' +
                                '<div class="board_cell cell-6"></div>' +
                                '<div class="board_cell cell-7"></div>' +
                                '<div class="board_cell cell-8"></div>'
    this.htmlCells = [...this.board.children]
  }

  getMove (gameBoard) {
  }

  play (gameBoard, currentPlayer, nextPlayer) {
    gameBoard.spots.forEach((cell, index) => {
      this.htmlCells[index].addEventListener('click', () => {
        if (this.hasClass(this.htmlCells[index], 'x') || this.hasClass(this.htmlCells[index], 'o') || this.gameRules.gameOver(gameBoard)) return false
        gameBoard.setMove(index, currentPlayer.marker)
        this.addClass(this.htmlCells[index], currentPlayer.marker)
        this.addClass(this.htmlCells[index], currentPlayer.symbol)
        if (this.gameRules.gameOver(gameBoard)) {
          var elements = document.getElementsByClassName(currentPlayer.marker)
          for (let item of elements) {
            this.addClass(item, 'win')
          }
        } else {
          let spot = nextPlayer.getMove(gameBoard)
          gameBoard.setMove(spot, nextPlayer.marker)
          this.addClass(this.htmlCells[spot], nextPlayer.marker)
          this.addClass(this.htmlCells[spot], nextPlayer.symbol)
          if (this.gameRules.gameOver(gameBoard)) console.log('over')
        }
      }, false)
    })
  }

  hasClass (el, className) {
    if (el.classList) {
      return el.classList.contains(className)
    } else {
      return !!el.className.match(new RegExp('(\\s|^)' + className + '(\\s|$)'))
    }
  }

  addClass (el, className) {
    if (el.classList) {
      el.classList.add(className)
    } else if (!this.hasClass(el, className)) {
      el.className += ' ' + className
    }
  }
}

module.exports = WebUI
