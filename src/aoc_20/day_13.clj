(ns aoc-20.day-13
  (:require [aoc-20.util :as util]
            [clojure.string :as s]))

(defn read-lines [filename]
  (util/read-lines filename))

;; -- Part 1 --

(defn bus-ids [input]
  (map #(Integer/parseInt %) (-> (s/replace input #"x," "")
                                 (s/split #","))))
(defn check-bus [goal m id]
  (into m (loop [bus-time id]
            (if (>= bus-time goal)
              {bus-time id}
              (recur (+ bus-time id))))))

(defn part-1 [input]
  (let [goal  (Integer/parseInt (first input))
        buses (bus-ids (second input))
        times (reduce (partial check-bus goal) {} buses)
        earliest (first (sort (keys times)))
        bus-id   (get times earliest)]
    (-> (- earliest goal)
        (* bus-id))))

; (part-1 (read-lines "day13-example-input.txt"))
; (part-1 (read-lines "day13-input.txt"))

;; -- Part 2 --

; (def example "7,13,x,x,59,x,31,19")
; (def example2 "13,x,x,41,x,x,x,x,x,x,x,x,x,997,x,x,x,x,x,x,x,23,x,x,x,x,x,x,x,x,x,x,19,x,x,x,x,x,x,x,x,x,29,x,619,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,17")
; (def my-goal (Integer/parseInt "1000390"))

(defn index-pairs [input]
  (->> (map-indexed (fn [idx num]
                    (when-not (= "x" num)
                      [idx (Integer/parseInt num)])) (s/split input #","))
       (remove nil?)))

(defn part-2 [input]
  (let [pairs (index-pairs (second input))]
    pairs))


(part-2 (read-lines "day13-example-input.txt"))
