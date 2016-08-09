(ns clojure-advent-of-code.day6part1)

(defn read-in-lines [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(def file-input (read-in-lines "resources/day6.txt"))

(defn main []
  (let [board (for [x (range 10) y (range 10)] (vector x y))]
    (println board)
    (println (type board))))

(main)
