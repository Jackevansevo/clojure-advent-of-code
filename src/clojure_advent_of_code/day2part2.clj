(ns clojure-advent-of-code.day2part2
  (require [clojure.string :as str]))

(defn read-in-lines [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(def file-input (read-in-lines "resources/day2.txt"))

(defn calc-paper [in]
  (let [[l w h] in]
    (+ (* l w h)  (* 2 (reduce + (take 2 (sort (list l w h))))))))

(defn parse-file [in]
  (map read-string (str/split in #"x")))

(defn main []
  (reduce + (map calc-paper (map parse-file file-input))))

(main)
