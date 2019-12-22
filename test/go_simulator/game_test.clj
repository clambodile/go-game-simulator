(ns go-simulator.game-test
  (:require [go-simulator.game :refer :all]
            [clojure.test :refer :all]))

(def test-game
  {:move [:black [0 0]]
   :children (list {:move [:white [0 1]]
                :children (list {:move [:black [1 0]]
                             :children ()})})})

(deftest main-branch-test
  (testing "Selects the main branch of a game."
    (is (= '([:black [0 0]] [:white [0 1]] [:black [1 0]])
           (main-branch test-game)))))

(deftest branch-depth-test
  (testing "Returns the max depth of a branch."
    (is (= 3 (branch-depth test-game)))))
