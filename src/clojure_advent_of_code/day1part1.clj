(ns clojure-advent-of-code.day1part1
  (:require [clojure.string :as str]))

(def input (str/trim (slurp "resources/day1.txt")))

(defn main []
  (apply + (map (fn [x] (if (= x \() 1 -1))  input)))

(main)
