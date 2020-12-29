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

(defn adjust-total [state operation amount]
  (let [[op-key current-amount] (get-amount state operation)]
    (assoc state op-key (+ current-amount amount))))

(defn to-int [number]
  (Integer/parseInt number))

;; TODO: Slight misunderstanding here so needs some rework. If at north 3,
;; south +11 would put us at south 8 (have to move the 3 down from north
;; first). Not just adding amounts together.
(defn process-instruction [{:keys [facing] :as state}
                           instruction]
  (let [[_ operation str-amount] (re-find #"^(\w)?(\d+)$" instruction)
        op-key (keyword operation)
        amount (to-int str-amount)]
    (cond
      (#{:N :E :S :W} op-key) (adjust-total state op-key amount)
      (= :F op-key) (let [current-amount (facing state)]
                      (assoc state facing (+ current-amount amount)))
      (#{:L :R} op-key) (assoc state
                               :facing
                               (rotate facing (op-key op-map) amount)))))

(defn part-1 [input]
  (reduce process-instruction {:facing :east
                               :north  0
                               :south  0
                               :east   0
                               :west   0} input))

(def example ["F10"
              "N3"
              "F7"
              "R90"
              "F11"])

; (part-1 (read-lines "day12-example-input.txt"))
