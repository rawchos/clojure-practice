(ns aoc-20.day-16
  (:require [aoc-20.util :as util]
            [clojure.string :as s]))

(defn read-str [filename]
  (util/read-str filename))

;; -- Part 1 --

;; class: 1-3 or 5-7
;; row: 6-11 or 33-44
;; seat: 13-40 or 45-50
;; 
;; your ticket:
;; 7,1,14
;; 
;; nearby tickets:
;; 7,3,47
;; 40,4,50
;; 55,2,20
;; 38,6,12

(defn split-lines [str]
  (s/split str #"\n"))

(defn parse-ticket 
  "Take a line of values from the ticket and turn them
   into actual numbers."
  [ticket]
  (->> (s/split ticket #",")
       (map #(Integer/parseInt %))))

(defn tickets-only [str]
  (->> (split-lines str)
       (rest)
       (map parse-ticket)))

(defn parse-rules [rules]
  (reduce
   (fn [m line]
     (let [[field ranges] (s/split line #":")]
       (assoc m (-> (s/replace field #" " "-")
                    (keyword)) (->> (re-seq #"\d+" ranges)
                                    (map #(Integer/parseInt %))
                                    (partition 2)))))
   {}
   rules))

(defn parse-input [str-input]
  (let [[rules my-ticket nearby-tickets] (s/split str-input #"\n\n")]
    [(parse-rules (split-lines rules))
     (tickets-only my-ticket)
     (tickets-only nearby-tickets)]))

(defn valid-ranges [rules]
  (->> (vals rules)
       (flatten)
       (partition 2)))

(defn valid-field? [rules field]
  (loop [ranges (valid-ranges rules)]
    (if-let [[minimum maximum] (first ranges)]
      (if (and (>= field minimum)
               (<= field maximum))
        true
        (recur (rest ranges)))
      false)))

(defn part-1 [str-input]
  (let [[rules _ nearby-tickets] (parse-input str-input)]
    (->> (flatten nearby-tickets)
         (remove (partial valid-field? rules))
         (reduce +))))

; (def example-str (read-str "day16-example-input.txt"))

; (part-1 (read-str "day16-example-input.txt"))
; (part-1 (read-str "day16-input.txt"))