(ns clojure-advent-of-code.day1part1)

(def input (slurp "resources/day1.txt"))

(defn main []
  (- (get(frequencies input) \() (get(frequencies input) \))))

(main)
