(ns clojure-advent-of-code.day4part1
  (require [clojure.string :as str]))

(defn md5 [secret]
  (apply str
         (map (partial format "%02x")
              (.digest (doto (java.security.MessageDigest/getInstance "MD5")
                         .reset
                         (.update (.getBytes secret)))))))

(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (lazy-seq (cons n (positive-numbers (inc n))))))

(defn count-zeros [in]
  (every? (partial =\0)  (take 5 in)))

(defn invalid-hash? [in]
  (not (count-zeros (md5 (str "bgvyzdsv" in)))))

(defn main []
  (inc (last (take-while invalid-hash? (positive-numbers)))))

(main)
