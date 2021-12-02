(ns aoc-20.day-16
  (:require [aoc-utils.core :as util]
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
     (vec (first (tickets-only my-ticket)))
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

; (part-1 (read-str "day16-example-input.txt"))
; (part-1 (read-str "day16-input.txt"))

;; -- Part 2 --

(defn valid-ticket? [rules ticket]
  (->> (map (partial valid-field? rules) ticket)
       (filter #(= false %))
       (count)
       (zero?)))

(defn rule-applies? [field [_ [[min1 max1] [min2 max2]]]]
  (or
   (<= min1 field max1)
   (<= min2 field max2)))

(defn applicable-rules 
  "We're going to be taking all values of a particular field
   at the same time and narrowing down the applicable rules as
   much as possible."
  [rules field]
  (reduce
   (fn [rules field]
     (filter #(rule-applies? field %) rules))
   (for [[descr ranges] rules] [descr ranges])
   field))

(defn assoc-unique [m [keys value]]
  (let [[unique] (remove m keys)]
    (assoc m unique value)))

;; Note: This was pretty difficult for me. Probably the toughest one
;;       so far. Probably should come back to it and see if I can
;;       refine it but I'm happy with just continuing on for now.
(defn part-2 [str-input]
  (let [[rules my-ticket nearby-tickets] (parse-input str-input)]
    (->> (filter (partial valid-ticket? rules) nearby-tickets)
         (apply map vector)
         (map-indexed (fn [idx number]
                        [(applicable-rules rules number) idx]))
         (sort-by (fn [[rules _]] (count rules)))
         (reduce assoc-unique {})
         (filter (fn [[[descr] _]]
                   (s/starts-with? (name descr) "departure")))
         (map (fn [[_ idx]] (my-ticket idx)))
         (reduce *))))

; (part-2 (read-str "day16-example-input.txt"))
; (part-2 (read-str "day16-input.txt"))