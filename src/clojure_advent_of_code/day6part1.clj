(ns clojure-advent-of-code.day6part1)

(defn read-in-lines [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(def file-input (read-in-lines "resources/day6.txt"))

(defn abs [n] (max n (- n)))

(defn convert-inputs [thing]
  (case thing
    "on" 1
    "off" -1
    "toggle" abs
    (read-string thing)))

(def command-regex #"^.*(on|off|toggle)\s(\d*),(\d*)\sthrough\s(\d*),(\d*)$")

(defn process-command [command]
  (map convert-inputs (rest (re-find command-regex command))))

(defn read-instructions [board command]
  (if (empty? command)
    (println "done")
    (do
      (let [[cmd x1 x2 y1 y2] (process-command (first command))]
        (println x1 x2 y1 y2)
      (read-instructions board (rest command))))))

(def file-input (read-in-lines "resources/day6.txt"))

(defn main []
  (let [board (into (sorted-map) (for [x (range 10) y (range 10)] [[x y] -1]))]
    (read-instructions board file-input)))


(for [x (range 1 3) y (range 1 6)] [x y])

(main)
