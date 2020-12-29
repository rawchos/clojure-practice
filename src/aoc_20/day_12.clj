(ns aoc-20.day-12
  (:require [aoc-20.util :as util]))

(defn read-lines [filename]
  (util/read-lines filename))

;; Legend:
;;  - Action N means to move north by the given value.
;;  - Action S means to move south by the given value.
;;  - Action E means to move east by the given value.
;;  - Action W means to move west by the given value.
;;  - Action L means to turn left the given number of degrees.
;;  - Action R means to turn right the given number of degrees.
;;  - Action F means to move forward by the given value in the direction
;;    the ship is currently facing.

(defn rotation-turns [degrees]
  (-> (/ degrees 90)
      (mod 4)))

(defn first-index-of [thing coll]
  (first (keep-indexed #(when (= thing %2) %1) coll)))

(defn order-by [direction]
  (flatten
   (repeat 2
           (case direction
             :left  [:north :west :south :east]
             :right [:north :east :south :west]))))

(defn rotate [current direction degrees]
  (let [turns (rotation-turns degrees)
        directions (order-by direction)]
    (nth directions (+ turns
                       (first-index-of current directions)))))

(defn process-instruction [state instruction])

(defn part-1 [input]
  (reduce process-instruction {:facing :east
                               :north  0
                               :south  0
                               :east   0
                               :west   0} input))

; (rotate :west :right 270)

