(ns clojure-advent-of-code.day6part1)

(defn read-in-lines [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(def file-input (read-in-lines "resources/day6.txt"))

(def grid-size 1000)

(def grid (for [x (range grid-size) y (range grid-size)] [x y]))

(defn on [n] 1)
(defn off [n] -1)
(defn toggle [n] (if (< n 0) 1 -1))

(defn convert-inputs [thing]
  (case thing
    "on" on
    "off" off
    "toggle" toggle
    (read-string thing)))

(def command-regex #"^.*(on|off|toggle)\s(\d*),(\d*)\sthrough\s(\d*),(\d*)$")

(defn process-command [command]
  (map convert-inputs (rest (re-find command-regex command))))

(defn map-values [m keys f & args]
  (reduce #(apply update-in %1 [%2] f args) m keys))

(defn read-instructions [board commands]
  (if (empty? commands)
    (println (count (filter #{1} (vals board))))
    (do
      (println "starting command:" (count commands))
      (let [[cmd x1 x2 y1 y2] (process-command (first commands))]
        (let [xStep (+ (* x1 grid-size) x2) yStep (inc (+ (* y1 grid-size) y2))]
          (let [pointsBetween (into [] (drop xStep (take yStep grid)))]
            (read-instructions (map-values board pointsBetween cmd) (rest commands))))))))

(defn -main [& args]
  (println "Starting day6")
  (let [board (into (hash-map) (for [x (range grid-size) y (range grid-size)] [[x y] -1]))]
    (read-instructions board file-input)))
