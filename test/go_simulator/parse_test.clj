(ns go-simulator.parse-test
  (:require [go-simulator.parse :refer :all]
            [clojure.test :refer :all]))

(deftest normalize-test
  (let [non-branching [[:black [0 0]] [:white [0 1]] [:black [1 0]]]
        single-branching [[:black [0 0]] [[:white [0 1]] [[:black [1 0]]]]]]
    (testing "normalizes a non-branching game"
      (is (= {:move [:black [0 0]] :children '({:move [:white [0 1]] :children ( {:move [:black [1 0]] :children ()})})}
             (normalize non-branching)))
      (is (= {:move [:black [0 0]] :children '({:move [:white [0 1]] :children ({:move [:black [1 0]] :children ()})})}
             (normalize single-branching))))))
