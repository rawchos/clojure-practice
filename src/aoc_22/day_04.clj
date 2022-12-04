(ns aoc-22.day-04
  (:require [aoc-utils.core :as util]
            [clojure.data :as data]))

(defn read-input []
  (util/read-lines "22" "day04.txt"))

(def schedule-matcher #"(\d+)-(\d+),(\d+)-(\d+)")
(defn schedule-times [schedule]
  (-> (re-matches schedule-matcher schedule)
      rest))

(defn parse-schedule [schedule]
  (->> (schedule-times schedule)
       (mapv #(Integer/parseInt %))))

(defn fully-overlap? [[s1-start s1-end s2-start s2-end]]
  (or (<= s1-start s2-start s2-end s1-end)
      (<= s2-start s1-start s1-end s2-end)))

(defn has-overlap? [[s1-start s1-end s2-start s2-end]]
  (->> (data/diff (set (range s1-start (inc s1-end)))
                  (set (range s2-start (inc s2-end))))
       last
       seq
       boolean))

(comment
  ; part1 => 515
  (->> (read-input)
       (map parse-schedule)
       (map fully-overlap?)
       (filter true?)
       count)
  
  ; part2 => 883
  (->> (read-input)
       (map parse-schedule)
       (map has-overlap?)
       (filter true?)
       count)
  )