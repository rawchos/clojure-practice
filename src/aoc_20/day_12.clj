(ns aoc-20.day-12
  (:require [aoc-20.util :as util]))

(defn read-lines [filename]
  (util/read-lines filename))

;; -- Part 1 --

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

;; Note: So, with this implementation, I think I went a little bit
;; overboard on tracking north, south, east, west and trying to keep
;; them in sync with each other. Would've been easier to just track
;; x and y and let north and east increase them while south and west
;; decrease them. Oh well, maybe I'll do that for part 2.
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

;; -- Part 2 --

;; Updated Legend:
;; - Action N means to move the waypoint north by the given value.
;; - Action S means to move the waypoint south by the given value.
;; - Action E means to move the waypoint east by the given value.
;; - Action W means to move the waypoint west by the given value.
;; - Action L means to rotate the waypoint around the ship left
;;   (counter-clockwise) the given number of degrees.
;; - Action R means to rotate the waypoint around the ship right
;;   (clockwise) the given number of degrees.
;; - Action F means to move forward to the waypoint a number of times
;;   equal to the given value.

(defn rotate-waypoint [waypoint direction degrees]
  (reduce-kv (fn [m k v]
               (assoc m (rotate k direction degrees) v))
             {}
             waypoint))

(defn advance [{:keys [waypoint x y] :as state}
               amount]
  (reduce-kv (fn [m k v]
               (let [update-amount (if (#{:north :east} k)
                                     (* amount v)
                                     (* amount v -1))]
                 (if (#{:north :south} k)
                   (assoc m :x (+ x update-amount))
                   (assoc m :y (+ y update-amount)))))
             state
             waypoint))

(defn adjust-waypoint [waypoint direction amount]
  (let [opposite     (opposite-direction direction)
        this-amount  (get waypoint direction 0)
        other-amount (get waypoint opposite 0)]
    (if (pos? this-amount)
      (assoc waypoint direction (+ amount this-amount))
      (let [new-amount (- other-amount amount)]
        (if (pos? new-amount)
          (-> (assoc waypoint
                     opposite new-amount)
              (dissoc direction))
          (-> (assoc waypoint
                 direction (Math/abs new-amount))
              (dissoc opposite)))))))

(defn p2-process-instruction [{:keys [waypoint] :as state}
                              instruction]
  (let [[_ operation str-amount] (re-find #"^(\w)?(\d+)$" instruction)
        op-key (keyword operation)
        amount (to-int str-amount)]
    (cond
      (#{:N :S :E :W} op-key) (assoc state
                                     :waypoint (adjust-waypoint waypoint (op-key op-map) amount))
      (= :F op-key) (advance state amount)
      (#{:L :R} op-key) (assoc state
                               :waypoint (rotate-waypoint waypoint
                                                          (op-key op-map)
                                                          amount)))))
(defn part-2 [input]
  (let [{:keys [x y]} (reduce p2-process-instruction {:waypoint {:north 1
                                                                  :east 10}
                                                       :x 0
                                                       :y 0} input)]
    (+ (Math/abs x)
       (Math/abs y))))

; (part-2 (read-lines "day12-example-input.txt"))
; (part-2 (read-lines "day12-input.txt"))
