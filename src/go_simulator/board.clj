(ns go-simulator.board
  (:require [clojure.core.match :refer [match]]
            [clojure.string :as str]))

(defn empty-board
  "Creates an empty board of x width and y height"
  [x y]
  (into [] (take y (repeat (into [] (take x (repeat :empty)))))))

(defn apply-move
  "Takes a move in the form [color [x y]] and applies it to an existing board."
  [board [color [x y]]]
  (assoc-in board [y x] color))

(defn apply-moves
  [board moves]
  (reduce apply-move board moves))

(def grid-chars
  {:top-left "┌"
   :left-edge "├"
   :bottom-left "└"
   :top-edge "┬"
   :bottom-edge "┴"
   :top-right "┐"
   :right-edge "┤"
   :bottom-right "┘"
   :center "┼"
   :black "b"
   :white "w"
   })

(defn pprint
  "Prints a purely textual representation of game board."
  [board]
  (let [width (count (first board))
        right (- width 1)
        height (count board)
        bottom (- height 1)
        lines (map-indexed
               (fn [y row]
                 (str/join ""
                           (map-indexed
                            (fn [x val]
                              (grid-chars
                               (match [val y x]
                                      [:white _ _] :white
                                      [:black _ _] :black
                                      [_ 0 0] :top-left
                                      [_ 0 right] :top-right
                                      [_ 0 _] :top-edge
                                      [_ bottom 0] :bottom-left
                                      [_ bottom right] :bottom-right
                                      [_ bottom _] :bottom-edge
                                      [_ _ 0] :left-edge
                                      [_ _ right] :right-edge
                                      :else :center)))
                            row)))
               board)]
    (doseq [line lines]
      (println line))))

(def my-board
  (-> (empty-board 19 19)
      (apply-move [:black [4 4]])
      (assoc-in [3 3] :white)))

(pprint my-board)
;; => nil
