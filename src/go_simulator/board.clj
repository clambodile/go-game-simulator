(ns go-simulator.board
  (:require [clojure.core.match :refer [match]]))

[[:empty :empty :empty :empty :empty :empty :empty :empty :empty]
 [:empty :empty :empty :empty :empty :empty :empty :empty :empty]
 [:empty :empty :empty :empty :empty :empty :empty :empty :empty]
 [:empty :empty :empty :empty :empty :empty :empty :empty :empty]
 [:empty :empty :empty :empty :empty :empty :empty :empty :empty]
 [:empty :empty :empty :empty :empty :empty :empty :empty :empty]
 [:empty :empty :empty :empty :empty :empty :empty :empty :empty]
 [:empty :empty :empty :empty :empty :empty :empty :empty :empty]
 [:empty :empty :empty :empty :empty :empty :empty :empty :empty]]

(defn empty-board
  "Creates an empty board of x width and y height"
  [x y]
  (into [] (take y (repeat (into [] (take x (repeat :empty)))))))

                                        ;(def my-board (empty-board 9 9))

(def grid-chars
  {:top-left "┌	"
   :left-edge "├"
   :bottom-left "└"
   :top-edge "┬"
   :bottom-edge "┴"
   :top-right "┐"
   :right-edge "┤"
   :bottom-right "┘"
   :center "┼"
   :black ⚪
   :white ⚫})

(defn pprint
  "Prints a purely textual representation of game board."
  [board]
  (let [width (count (first board))
        right (- width 1)
        height (count board)
        bottom (- height 1)]
    (map-indexed (fn [y row]
                   (map-indexed (fn [x val]
                                  (grid-chars (match [val x y]
                                                     [:white _ _] :white
                                                     [:black _ _] :black
                                                     [_ 0 0] :top-left
                                                     [_ 0 right] :top-right
                                                     [_ 0 _] :top-edge
                                                     [_ bottom 0] :bottom-left
                                                     [_ bottom right] :bottom-right
                                                     [_ bottom _] :bottom-edge
                                                     [_ _ 0] :left-edge
                                                     [- - right] :right-edge
                                                     :else :center))))))))
