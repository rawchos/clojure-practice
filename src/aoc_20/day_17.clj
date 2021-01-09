(ns aoc-20.day-17
  (:require [aoc-20.util :as util]))

(defn read-lines [filename]
  (vec (util/read-lines filename)))

;; -- Part 1 --

(defn parse-input
  "Find all the positions in the grid where the cube is active"
  ([grid]
   (parse-input grid false))
  ([grid four-dimensions?]
  (set (for [y (range (count grid))
             x (range (count (first grid)))
             :when (= (get-in grid [y x]) \#)]
         (if four-dimensions?
           [0 y x 0]
           [0 y x])))))

(defn neighbors [[z y x]]
  (for [dz [-1 0 1]
        dy [-1 0 1]
        dx [-1 0 1]
        :when (not (every? zero? [dz dy dx]))]
    [(+ z dz) (+ y dy) (+ x dx)]))

(defn process [neighbors-f grid]
  (set
   (filter #(let [num (count (filter grid (neighbors-f %)))]
              (if (grid %) (<= 2 num 3) (= num 3)))
           (distinct (mapcat neighbors-f grid)))))


(defn part-1 [input]
  (->> (iterate #(process neighbors %) (parse-input input))
       (drop 6)
       (first)
       (count)))

; (part-1 (read-lines "day17-example-input.txt"))
; (part-1 (read-lines "day17-input.txt"))

;; -- Part 2 --
(defn p2-neighbors [[z y x w]]
  (for [dz [-1 0 1]
        dy [-1 0 1]
        dx [-1 0 1]
        dw [-1 0 1]
        :when (not (every? zero? [dz dy dx dw]))]
    [(+ z dz) (+ y dy) (+ x dx) (+ w dw)]))

(defn part-2 [input]
  (->> (iterate #(process p2-neighbors %) (parse-input input true))
       (drop 6)
       (first)
       (count)))

; (part-2 (read-lines "day17-example-input.txt"))
; (part-2 (read-lines "day17-input.txt"))