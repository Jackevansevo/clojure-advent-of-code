(ns clojure-advent-of-code.day3part1
  (require [clojure.string :as str]))

(def input (slurp "resources/day3.txt"))

(defn convert-input [in]
  (case in
    \> [1 0] \< [-1 0] \^ [0 1] \v [0 -1]))

(defn main []
  (let [directions (conj (map convert-input input) [0 0])]
    (count (set (reductions (partial mapv +) directions)))))

(main)
