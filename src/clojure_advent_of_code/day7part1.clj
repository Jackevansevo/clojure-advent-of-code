(ns clojure-advent-of-code.day7part1
  (require [clojure.string :as str]))

;; [TODO] Reverse the commands so
;; NOT qq -> d becomes  d -> NOT qq
;; 1 AND s -> a becomes a -> 1 AND s
;; Then order alphabetically, and solve sequentially

(defn read-in-lines [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (doall (line-seq r))))

(def board (atom {}))

(defn my-bit-not [b] (bit-and (bit-not b) 0xffff))

(def bitwise-func {:AND bit-and :OR bit-or :NOT my-bit-not :LSHIFT
                   bit-shift-left :RSHIFT bit-shift-right})

(defn is-command [c]
  (boolean (re-find #"^(AND|OR|LSHIFT|RSHIFT|NOT)$" c)))

(defn parse-commands [cmd]
  (map read-string
       (sort-by is-command
                (flatten (map #(str/split % #" ")
                              (map str/trim (rseq (str/split cmd #"->"))))))))


(defn keywordify [input]
  (map (fn [n] (if (symbol? n) (keyword n) n)) input))

(defn single-elem? [s]
  (and (seq s) (empty? (rest s))))

(defn sexpressify [exp]
  (if (single-elem? exp)
    (first exp)
    (cons (get bitwise-func (keyword (last exp))) (butlast exp))))

(defn update-board [pos signal]
  (swap! board assoc (keyword pos) signal))

(defn add-wires [[wire & command]]
  (update-board wire (sexpressify command)))


(defn run-curcuit [[wire cmd]]
  (if (or (instance? Long cmd) (symbol? cmd))
    (update-board wire cmd)
    (let [replaced (map (fn [n] (get (deref board) (keyword n) n)) cmd)]
      (if (every? true? (map (fn [n] (instance? Long n)) (rest replaced)))
        (update-board wire (eval replaced))))))


(defn -main [& args]
  (mapv add-wires
        (sort-by first (map parse-commands (read-in-lines "resources/day7.txt"))))
  (mapv run-curcuit (deref board))
  (println (get (deref board) :lx)))

(-main)
