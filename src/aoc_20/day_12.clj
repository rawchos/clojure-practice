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

(def op-map {:N :north
             :S :south
             :W :west
             :E :east
             :F :facing
             :L :left
             :R :right})

(defn get-amount [state operation]
  (let [operation-key (operation op-map)]
    [operation-key (operation-key state)]))

(defn to-int [number]
  (Integer/parseInt number))

(defn opposite-direction [direction]
  (case direction
    :east :west
    :west :east
    :north :south
    :south :north))

(defn adjust-total [state direction amount]
  (let [opposite     (opposite-direction direction)
        this-amount  (direction state)
        other-amount (opposite state)]
    (if (pos? this-amount)
      (assoc state direction (+ amount this-amount))
      (let [new-amount (- other-amount amount)]
        (if (pos? new-amount)
          (assoc state
                 opposite new-amount)
          (assoc state
                 opposite 0
                 direction (Math/abs new-amount)))))))

(defn process-instruction [{:keys [facing] :as state}
                           instruction]
  (let [[_ operation str-amount] (re-find #"^(\w)?(\d+)$" instruction)
        op-key (keyword operation)
        amount (to-int str-amount)]
    (cond
      (#{:N :S :E :W} op-key) (adjust-total state (op-key op-map) amount)
      (= :F op-key) (adjust-total state facing amount)
      (#{:L :R} op-key) (assoc state
                               :facing (rotate facing (op-key op-map) amount)))))

(defn part-1 [input]
  (let [{:keys [north
                south
                east
                west]} (reduce process-instruction {:facing :east
                                                    :north  0
                                                    :south  0
                                                    :east   0
                                                    :west   0} input)]
    (+ (+ north south)
       (+ east west))))

; (part-1 (read-lines "day12-example-input.txt"))
; (part-1 (read-lines "day12-input.txt"))


