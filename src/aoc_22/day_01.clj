(ns aoc-22.day-01
  (:require [aoc-utils.core :as utils]
            [clojure.string :as str]))

(defn count-calories
  "Count the calories of a food list. Converts each to an
   integer first then sums them."
  [food-list]
  (->> (map #(Integer/parseInt %) food-list)
       (reduce +)))

(comment
  (def the-input (utils/read-lines "22" "day01.txt"))
  
  ; part 1
  (->> the-input
       (partition-by str/blank?)
       (remove (partial every? str/blank?))
       (map count-calories)
       sort
       last)

  ; part 2
  (->> the-input
       (partition-by str/blank?)
       (remove (partial every? str/blank?))
       (map count-calories)
       sort
       reverse
       (take 3)
       (reduce +))
  )