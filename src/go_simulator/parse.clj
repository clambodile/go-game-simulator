(ns go-simulator.parse
  (:require [instaparse.core :as insta]
            [clojure.string :as str]
            [clojure.core.match :refer [match]]))

(insta/defparser sgf-parser
  "src/go_simulator/go_sgf_grammar.txt"
  :output-format :hiccup)

(defn char-coord->int-coord
  [coord]
  "Transforms coordinates such as ab into coordinate such as [1 2]"
  (if (= "" coord)
    :pass
    (mapv (comp #(- % 97) int) coord)))

(char-coord->int-coord "aa")
(char-coord->int-coord "gg")


(def transformer
  {:BlackMove (fn [coord] [:black (char-coord->int-coord coord)])
   :WhiteMove (fn [coord] [:white (char-coord->int-coord coord)])
   :GameTree list})

(defn normalize
  "Makes [a b c] match [a [b [c]]]"
  [node]
  (cond (empty? node) nil
        (sequential? (first node)) (update (normalize (first node)) :children #(remove nil? (conj % (normalize (rest node)))))
        (nil? node) nil
        :else {:move node :children '()}))
                                        (def filename "alpha_lee_game_5.sgf")
                                        ;(def filename "SHORTENED_alpha_lee_game_5.sgf")
                                        ;(def filename "SIMPLIFIED_20932240-034-BTC_USD-santana3.sgf")
                                        ;(def filename "20932240-034-BTC_USD-santana3.sgf")
;(def filename "simple_variations.sgf")

(def file
  (as-> filename _
    (slurp _)
    (str/split _ #"\n")
    (str/join "" _)))

                                        ;file
;(insta/transform transformer (sgf-parser file))

(def my-game (normalize (insta/transform transformer (sgf-parser file))))

(:move (first (:children my-game)))

(map :move (take-while (comp not nil?) (iterate (comp first :children) my-game)))

