(ns aoc-22.day-06
  (:require [aoc-utils.core :as util]))

(defn read-input []
  (util/read-str "22" "day06.txt"))

(defn detect-marker [size packet]
  (loop [packet-chars (seq packet)
         final-index  size]
    (if (empty? packet-chars)
      [0 ""]
      (let [this-group (take size packet-chars)]
        (if (= size (-> this-group set count))
          [final-index (apply str this-group)]
          (recur (rest packet-chars)
                 (inc final-index)))))))

(comment
  ; part 1 => [1100 "mbnz"] => 1100
  (->> (read-input)
       (detect-marker 4)
       first)
  
  ; part 2 => [2421 "cmwlnbzqjsgftr"] => 2421
  (->> (read-input)
       (detect-marker 14)
       first)
  )