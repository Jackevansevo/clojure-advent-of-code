(ns clojure-advent-of-code.day6part2)

(def grid-size 1000)

(def board (into {} (for [x (range grid-size) y (range grid-size)] [[x y] 0])))

(defn read-in-lines [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(defn convert-inputs [input]
  (case input
    "toggle" (fn [x] (+ x 2))
    "turn on" (fn [x] (inc x))
    "turn off" (fn [x] (if (pos? x) (- x 1) 0))
    (read-string input)))

(def command-regex
  #"(?)^(toggle|turn on|turn off)\s(\d*),(\d*)\sthrough\s(\d*),(\d*)$")

(defn parse-command [command]
  ;; Ignores the first match group of the vector expression
  (map convert-inputs (subvec (re-find command-regex command) 1)))

(defn process-instruction [[cmd x1 y1 x2 y2]]
  (let [coords (for [x (range x1 (inc x2)) y (range y1 (inc y2))] [x y])]
    [cmd coords]))

(defn update-vals [map vals f]
  (reduce #(update-in % [%2] f) map vals))

(defn update-board [[cmd coords]]
  (def board (update-vals board coords cmd)))

(defn -main [& args]
  (println "Starting day6")
  (mapv update-board
        (mapv process-instruction
              (mapv parse-command (read-in-lines "resources/day6.txt"))))
  (println (apply + (vals board))))
