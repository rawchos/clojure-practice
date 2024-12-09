(ns aoc-22.day-08
  (:require [aoc-utils.core :as util]))

(defn read-input []
  (util/read-lines "22" "day08.txt"))

(defn char->int [c]
  (-> (str c) Integer/parseInt))

(defn parse-line [input-line]
  (map char->int input-line))

(comment
  (->> (read-input)
       (take 3) ; remove this line
       (map parse-line))
  )