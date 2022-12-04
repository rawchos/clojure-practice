(ns aoc-22.day-03
  (:require [aoc-utils.core :as util]
            [clojure.data :as data]))

(def the-chars      ; a - z          A - Z
  (->> (concat (range 97 123) (range 65 91))
       (map char)))

(def priorities (zipmap the-chars (range 1 53)))

(defn split-groups [line]
  (partition (/ (count line) 2) line))

(defn find-match [[group1 group2]]
  (-> (data/diff (set group1) (set group2))
      (nth 2)))

(defn determine-priority [input-line]
  (-> (split-groups input-line)
      find-match
      first
      priorities))

(defn read-input []
  (util/read-lines "22" "day03.txt"))

(defn determine-badge [[line1 line2 line3]]
  (->> (find-match [line1 line2])
       (#(find-match [% line3]))
       first
       priorities))

(comment
  
  ; part1 => 8176
  (->> (read-input)
       (map determine-priority)
       (reduce +))
  
  ; part2 => 2689
  (->> (read-input)
       (partition 3)
       (map determine-badge)
       (reduce +))

  )