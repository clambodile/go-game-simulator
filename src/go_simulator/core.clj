(ns go-simulator.core
  (:require [go-simulator.board :as board]
            [go-simulator.game :as game]
            [go-simulator.parse :as parse]))


(def filename "alpha_lee_game_5.sgf")

(def the-game
  (parse/read filename))

(def branch
  (game/deepest-branch the-game))

branch

(def board
  (board/apply-moves (board/empty-board 19 19) branch))

(board/pprint board)
