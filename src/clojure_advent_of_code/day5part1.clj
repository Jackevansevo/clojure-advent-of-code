(ns clojure-advent-of-code.day5part1
  (require [clojure.string :as str]))

(defn convert-to-num [letter]
  (if (contains? #{\a \e \i \o \u} letter) 1 0))

(defn count-vowels [phrase]
  (reduce + (map convert-to-num phrase)))

(defn at-least-three-vowels [phrase]
  (>= (count-vowels phrase) 3))

(defn has-repeat-letter [phrase]
  (not (= (seq (char-array phrase)) (dedupe phrase))))

(defn has-bad-strings [phrase]
  (not (boolean (re-find #"ab|cd|pq|xy" phrase))))

(defn check-if-nice [phrase]
  (and
    (at-least-three-vowels phrase)
    (has-repeat-letter phrase)
    (has-bad-strings phrase)))

(defn read-in-lines [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(def file-input (read-in-lines "resources/day5.txt"))

(defn main []
  (count (filter identity (map check-if-nice file-input))))

(main)
