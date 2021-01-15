(ns aoc-20.day-19
  (:require [aoc-20.util :as util]
            [clojure.string :as s]))

(defn read-str [filename]
  (util/read-str filename))

;; Note: I needed a little help on this one. I struggled on it.

;; -- Part 1 --

(defn split-input [str-input]
  (s/split str-input #"\n\n"))

(defn split-rule [rule]
  (let [[rule-num sub-rules] (s/split rule #": ")]
    [(read-string rule-num) (map read-string (s/split sub-rules #" "))]))

(defn input-data [filename]
  (let [[rules messages] (->> (split-input (read-str filename))
                              (map s/split-lines))]
    [(into {} (map split-rule rules)) messages]))

(defn build-pattern
  ([rules]
   (build-pattern rules 16 0))
  ([rules max-depth n]
   (if (pos? max-depth)
     (s/join (map #(if (int? %)
                     (str "(" (build-pattern rules (dec max-depth) %) ")")
                     %)
                  (rules n)))
     "x")))

(defn part-1 [[rules messages]]
  (let [pattern (re-pattern (build-pattern rules))]
    (-> (filter #(re-matches pattern %) messages)
        (count))))

; (part-1 (input-data "day19-example-input.txt"))
; (part-1 (input-data "day19-input.txt"))

;; -- Part 2 --

(defn part-2 [[rules messages]]
  (let [new-rules(assoc rules
                        8 '(42 | 42 8)
                        11 '(42 31 | 42 11 31))
        pattern (re-pattern (build-pattern new-rules))]
    (-> (filter #(re-matches pattern %) messages)
        (count))))

; (part-2 (input-data "day19-example-input2.txt"))
; (part-2 (input-data "day19-input.txt"))
