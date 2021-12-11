(ns aoc-21.day-03
  (:require [aoc-utils.core :as util]))

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

(defn higher-lower [freq-map & [when-equal]]
  (let [zeros (get freq-map \0 0)
        ones  (get freq-map \1 0)]
    (cond
      (and when-equal (= zeros ones)) [when-equal when-equal]
      (> zeros ones) ["0" "1"]
      (< zeros ones) ["1" "0"])))

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

(defn get-rating [readings rating]
  (loop [coll     readings
         position 0]
    (if (= 1 (count coll))
      (first coll)
      (let [[higher lower] (-> (map #(nth % position) coll)
                               frequencies
                               (higher-lower (if (= :oxygen-generator rating)
                                               "1" "0")))]
        (recur (filter #(= (if (= rating :oxygen-generator) higher lower) (str (nth % position))) coll) (inc position))))))
  
(defn part-2 [input]
  (let [oxygen-rating (get-rating input :oxygen-generator)
        co2-rating    (get-rating input :co2-scrubber)]
    (* (Integer/parseInt oxygen-rating 2)
       (Integer/parseInt co2-rating 2))))

(comment
  (part-1 sample-readings)
  (part-1 (get-input)) ; 3901196
  
  (map #(str (nth % 0)) sample-readings)
  
  (filter #(= "0" (str (nth % 0))) sample-readings)

  (-> (map #(str (nth % 0)) sample-readings) frequencies higher-lower)

  (get-rating sample-readings :oxygen-generator)
  (get-rating sample-readings :co2-scrubber)

  (part-2 sample-readings)
  (part-2 (get-input)) ; 4412188
  
  )