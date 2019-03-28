class Board {
  constructor () {
    this.spots = ['', '', '', '', '', '', '', '', '']
  }

  availableSpots () {
    let availableSpots = []
    for (let i = 0; i < this.spots.length; i += 1) {
      if (this.spots[i] !== 'x' && this.spots[i] !== 'o') {
        availableSpots.push(i)
      }
    }
    return availableSpots
  }

  setMove (cell, marker) {
    this.spots[cell] = marker
  }
}

module.exports = Board
