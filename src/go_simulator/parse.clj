(ns go-simulator.parse
  (:require [instaparse.core :as insta]
            [clojure.string :as str]))

(insta/defparser sgf-parser
  "src/go_simulator/go_sgf_grammar.txt"
  :output-format :hiccup)

(defn char-coord->int-coord
  [coord]
  "Transforms coordinates such as ab into coordinate such as [1 2]"
  (if (= "" coord)
    :pass
    (mapv(comp #(- % 97) int) coord)))

(char-coord->int-coord "aa")
(char-coord->int-coord "gg")

(def transformer
  {:BlackMove (fn [coord] [:black (char-coord->int-coord coord)])
   :WhiteMove (fn [coord] [:white (char-coord->int-coord coord)])
   :GameTree vector})


;(def filename "alpha_lee_game_5.sgf")
;(def filename "SHORTENED_alpha_lee_game_5.sgf")
;(def filename "SIMPLIFIED_20932240-034-BTC_USD-santana3.sgf")
(def filename "20932240-034-BTC_USD-santana3.sgf")

(def file
  (as-> filename _
      (slurp _)
      (str/split _ #"\n")
      (str/join "" _)))

;file
(sgf-parser file)

(insta/transform transformer (sgf-parser file))

;(insta/visualize sgf-parser file)
