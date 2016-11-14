(ns clojure-advent-of-code.day2part1
  (:require [clojure.string :as str]))

(defn read-in-lines [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(def file-input (read-in-lines "resources/day2.txt"))

(defn calc-paper [[l w h]]
  (+ (* 2 l w) (* 2 w h) (* 2 h l) (apply * (take 2 (sort [l w h])))))

(defn parse-line [in]
  (map read-string (str/split in #"x")))

(defn main []
  (apply + (map calc-paper (map parse-line file-input))))

(main)
