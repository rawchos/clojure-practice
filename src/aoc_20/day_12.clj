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

; (defn adjust-total [state operation amount]
;   (let [[op-key current-amount] (get-amount state operation)]
;     (assoc state op-key (+ current-amount amount))))

(defn to-int [number]
  (Integer/parseInt number))

(defn adjust-total [amount this-side opposite-side]
  (if (pos? this-side)
    (+ amount this-side)
    (Math/abs (- opposite-side amount))))

(defn opposite-direction [direction]
  (case direction
    :east :west
    :west :east
    :north :south
    :south :north))

;; TODO: You probably don't always update the other direction to zero.
;; If south is at 20 and you move north 10, you're still on the south
;; side. Need a function to perform the update and check both
;; directions.
(defn process-instruction [{:keys [facing
                                   north
                                   south
                                   east
                                   west] :as state}
                           instruction]
  (let [[_ operation str-amount] (re-find #"^(\w)?(\d+)$" instruction)
        op-key (keyword operation)
        amount (to-int str-amount)]
    (case op-key
      :N (assoc state
                :north (adjust-total amount north south)
                :south 0)
      :S (assoc state
                :south (adjust-total amount south north)
                :north 0)
      :E (assoc state
                :east (adjust-total amount east west)
                :west 0)
      :W (assoc state
                :west (adjust-total amount west east)
                :east 0)
      :F (let [current-amount (facing state)
               opposite-key (opposite-direction facing)]
           (assoc state
                  facing (adjust-total amount
                                       current-amount
                                       (opposite-key state))
                  opposite-key 0))
      (assoc state :facing (rotate facing (op-key op-map) amount)))))

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

(part-1 (read-lines "day12-example-input.txt"))
(part-1 (read-lines "day12-input.txt"))


