(ns clojure-advent-of-code.day1part2
  (require [clojure.string :as str]))

(def input (seq (slurp "resources/day1.txt")))

;; Calculates the direction of movement up/down stairs
(defn next-floor [input]
  (if (= input '\) ) -1 1))

;; Calculate when santa enters the basement
(defn get-basement-pos [input lvl pos]
  (if (not (empty? input))
    (if (= lvl -1)
      pos
      (recur (rest input) (+ lvl (next-floor (first input))) (+ pos 1)))))

(defn main []
  (get-basement-pos input 0 0))

(main)
