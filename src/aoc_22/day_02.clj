(ns aoc-22.day-02
  (:require [aoc-utils.core :as util]
            [clojure.string :as str]))

(def play-map {:rock     ["A" "X"]
               :paper    ["B" "Y"]
               :scissors ["C" "Z"]})

(def shape-map
  (reduce-kv (fn [m k v]
               (reduce (fn [m play]
                         (assoc m play k))
                       m v))
             {}
             play-map))

(def selection-score {:rock     1
                      :paper    2
                      :scissors 3})

(def score-map
  {:rock     {:rock     3
              :paper    6
              :scissors 0}
   :paper    {:rock     0
              :paper    3
              :scissors 6}
   :scissors {:rock     6
              :paper    0
              :scissors 3}})

(defn calculate-score [plays]
  (+ (get selection-score (last plays))
     (get-in score-map plays)))

(defn read-input []
  (util/read-lines "22" "day02.txt"))

(defn format-part1 [input-line]
  (->> (str/split input-line #" ")
       (map #(get shape-map %))))

(def part2-strategy {"X" :lose
                     "Y" :draw
                     "Z" :win})

(def strategy-map
  {:rock     {:win  :paper
              :lose :scissors
              :draw :rock}
   :paper    {:win  :scissors
              :lose :rock
              :draw :paper}
   :scissors {:win  :rock
              :lose :paper
              :draw :scissors}})

(def part2-score {:lose 0
                  :draw 3
                  :win  6})

(defn format-part2 [input-line]
  (-> (str/split input-line #" ")
      ((fn [[play1 play2]]
         [(get shape-map play1)
          (get part2-strategy play2)]))))

(defn calculate-score2 [strategy]
  (+ (->> (get-in strategy-map strategy)
          (get selection-score))
     (get part2-score (last strategy))))

(comment
  
  ; part 1 => 8890
  (->> (read-input)
       (map format-part1)
       (map calculate-score)
       (reduce +))
  
  ; part 2 => 10238
  (->> (read-input)
       (map format-part2)
       (map calculate-score2)
       (reduce +))
  
  )