(ns go-simulator.board-test
  (:require [go-simulator.board :refer :all]
            [go-simulator.game :as game]
            [clojure.test :refer :all]))

(def test-game
  {:move [:black [0 0]]
   :children (list {:move [:white [0 1]]
                    :children (list {:move [:black [1 0]]
                                     :children ()})})})

(def moves (game/main-branch test-game))

(def test-board
  (empty-board 9 9))
;;test apply-moves
(deftest apply-moves-test
  (testing "applies a sequence of moves to a board."
    (let [result (apply-moves test-board moves)]
      (is = (get-in result [0 0] :black))
      (is = (get-in result [0 1] :white))
      (is = (get-in result [1 0] :black)))))
