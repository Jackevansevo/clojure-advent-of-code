(ns clojure-advent-of-code.day6part1)

(defn read-in-lines [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(def file-input (read-in-lines "resources/day6.txt"))

(defn process-command [command]
  (rest (re-find #"^.*(on|off|toggle)\s(\d*),(\d*)\sthrough\s(\d*),(\d*)$" command)))

(defn read-instructions [board command]
  (if (empty? command)
    (println "done")
    (do
      (let [[cmd x1 x2 y1 y2] (process-command (first command))]
        (println cmd x1 x2 y1 y2))
      (read-instructions board (rest command)))))


(def file-input (read-in-lines "resources/day6.txt"))

(defn main []
  (let [board (zipmap (for [x (range 5) y (range 5)] (vector x y)) (repeat -1))]
    (read-instructions board file-input)))

(main)
