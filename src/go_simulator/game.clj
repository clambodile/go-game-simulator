(ns go-simulator.game
  (:require [go-simulator.board :as board]))

;;take a board and a game and find final board state
;;find

;;a gamepath is a list of integers

;(defn select-branch
;  "Takes a list of integers indicating")

(defn main-branch
  "Returns the main branch of a game."
  [game]
  (->> game
       (iterate #(first (:children %)))
       (take-while (comp not nil?))
       (map :move)))

(def test-game
  {:move [:black [0 0]]
   :children (list {:move [:white [0 1]]
                    :children (list {:move [:black [1 0]]
                                     :children ()})})})

(main-branch test-game)
