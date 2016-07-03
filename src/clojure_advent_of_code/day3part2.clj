(ns clojure-advent-of-code.day3part2
  (require [clojure.set :as set])
  (require [clojure.string :as str]))

(def input (slurp "resources/day3.txt"))

(defn convert-input [in]
  (case in
    \> [1 0] \< [-1 0] \^ [0 1] \v [0 -1]))

;; sums vectors in the list, recording unique intermediary steps along the way
;; e.g. [[0 0] [1 0] [1 0] [1 0]] => #{[0 0] [1 0] [2 0] [3 0]}
(defn get-positions [dirs]
  (set (reductions (partial mapv +) dirs)))

;; Converts symbols in the list to corresponding vectors change in x/y
(defn get-vectors [input]
  ; Add the initial starting position
  (conj (map convert-input input) [0 0]))

(defn main []
  ; Splits input up into two lists of commands e.g. ^v^v becomes (^ ^) (v v)
  (let [santa-input (take-nth 2 input)
        robot-input (take-nth 2 (rest input))]
    (let [santa (get-vectors santa-input)
          robot (get-vectors robot-input)]
      (count (set/union (get-positions santa) (get-positions robot))))))

(main)
