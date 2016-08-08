(ns clojure-advent-of-code.day5part2)

(defn has-repeat-letter-pattern? [phrase]
  (boolean (re-matches #".*(.).\1.*" phrase)))

(defn has-duplicate-non-overlapping-pairs? [phrase]
  (boolean (re-matches #".*((.{2})).*\1.*" phrase)))

(defn check-if-nice [phrase]
  (and
    (has-repeat-letter-pattern? phrase)
    (has-duplicate-non-overlapping-pairs? phrase)))

(defn read-in-lines [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(def file-input (read-in-lines "resources/day5.txt"))

(defn main []
  (count (filter identity (map check-if-nice file-input))))

(main)
