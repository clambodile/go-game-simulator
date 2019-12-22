(ns go-simulator.game
  (:require [go-simulator.board :as board]
            [go-simulator.parse :as parse]))

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

(defn branch-depth
  "Returns the depth of a game branch."
  [game]
  (if (empty? (:children game)) 1
      (+ 1 (apply max (map branch-depth (:children game))))))

(defn deepest-branch
  "Returns whatever branch in the game is deepest."
  [game]
  (if (empty? (:children game)) (list (:move game))
      (cons (:move game) (last (sort-by count (map deepest-branch (:children game)))))))


;;select a branch of the game, from beginning to end
;;(to display in the tree when going forward and back)

;;select a specific move in a branch (to display on the board)
