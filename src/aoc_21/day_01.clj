(ns aoc-21.day-01
  (:require [aoc-utils.core :as util]))

(def sample-readings [199 200 208 210 200 207 240 269 260 263])

(defn get-input []
  (->> (util/read-lines "21" "day1.txt")
       (map util/to-int)))

(defn p2-input []
  (->> (get-input)
       (partition 3 1)
       (map #(reduce + %))))

(defn part-1 [readings]
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
  (util/read-lines "21" "day1.txt")
  (part-1 sample-readings)

  (part-1 (get-input)) ; 1482
  (part-1 (p2-input))  ; 1518

  (def my-input (get-input))
  (->> (partition 3 1 sample-readings)
       (map #(reduce + %))
       (part-1))
  )