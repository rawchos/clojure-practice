(ns aoc-21.day-01
  (:require [aoc-utils.core :as util]))

(def sample-readings [199 200 208 210 200 207 240 269 260 263])

(defn get-input []
  (->> (util/read-lines "21" "day01.txt")
       (map util/to-int)))

(defn p2-input []
  (->> (get-input)
       (partition 3 1)
       (map #(reduce + %))))

(defn count-increases [readings]
  (-> (reduce (fn [{:keys [previous-reading] :as state} reading]
                (let [new-state (assoc state :previous-reading reading)]
                  (cond
                    (< previous-reading reading) (update-in new-state [:increased] inc)
                    (> previous-reading reading) (update-in new-state [:decreased] inc)
                    :else new-state)))
              {:previous-reading (first readings)
               :increased        0
               :decreased        0}
              (rest readings))
      :increased))

(comment
  (util/read-lines "21" "day01.txt")
  (count-increases sample-readings)

  (count-increases (get-input)) ; 1482
  (count-increases (p2-input))  ; 1518

  (def my-input (get-input))
  (->> (partition 3 1 sample-readings)
       (map #(reduce + %))
       (count-increases))
  )