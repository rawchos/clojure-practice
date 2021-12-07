(ns aoc-21.day-03
  (:require [clojure.string :as str]
            [aoc-utils.core :as util]))

(def sample-readings ["00100"
                      "11110"
                      "10110"
                      "10111"
                      "10101"
                      "01111"
                      "00111"
                      "11100"
                      "10000"
                      "11001"
                      "00010"
                      "01010"])

(defn get-input []
  (util/read-lines "21" "day03.txt"))

(defn higher-lower [freq-map]
  (let [zeros (get freq-map \0 0)
        ones  (get freq-map \1 0)]
    (if (> zeros ones)
      ["0" "1"]
      ["1" "0"])))

(defn part-1 [input]
  (let [num-readings   (count input)
        highs-and-lows (->> (apply interleave input)
                            (partition num-readings)
                            (map #(higher-lower (frequencies %))))
        highs-binary   (->> (map first highs-and-lows)
                            (apply str))
        lows-binary    (->> (map second highs-and-lows)
                            (apply str))]
    (* (Integer/parseInt highs-binary 2)
       (Integer/parseInt lows-binary 2))))

(comment
  (part-1 sample-readings)
  (part-1 (get-input)) ; 3901196
  
  )