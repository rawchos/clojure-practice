(ns aoc-20.day-11
  (:require [aoc-utils.core :as util]
            [clojure.string :as s]))

;; Legend:
;;   L -> open seat
;;   # -> occupied seat
;;   . -> floor (no seat)

(defn read-lines [filename]
  (util/read-lines filename))

(defn pad-seats [lines]
  (mapv #(s/split (format ".%s." %) #"") lines))

(defn pad
  "If we pad the entire seat map with floor tiles
   all around it, we don't have to worry about any
   boundaries when checking adjacent seats."
  [seat-map]
  (let [floor-line (repeat (count (first seat-map)) ".")]
    (->> (conj seat-map floor-line)
         (cons floor-line))))

(defn pad-seat-map [filename]
  (-> (pad-seats (read-lines filename))
      (pad)))

(defn adjacents [seat-map [row column]]
  (let [previous-row (nth seat-map (dec row))
        this-row     (nth seat-map row)
        next-row     (nth seat-map (inc row))]
    (flatten
     (list
      (subvec (vec previous-row) (dec column) (+ column 2))
      [(nth this-row (dec column))
       (nth this-row (inc column))]
      (subvec (vec next-row) (dec column) (+ column 2))))))

(defn adjust-seat [seat adjacents max-occupied]
  (let [occupieds (-> (filter #(= "#" %) adjacents)
                      (count))]
    (if (= "L" seat)
      (if (pos? occupieds) "L" "#")
      (if (>= occupieds max-occupied) "L" "#"))))

(defn adjust-seating [seat-map adjacents-f max-occupied]
  (let [row-count (count seat-map)
        col-count (count (first seat-map))]
    (for [row (range row-count)]
      (into [] (for [column (range col-count)]
                 (let [seat (-> (nth seat-map row)
                                (nth column))]
                   (if (= "." seat)
                     seat
                     (adjust-seat seat
                                  (adjacents-f seat-map [row column])
                                  max-occupied))))))))

(defn count-occupieds [row]
  (-> (filter #(= "#" %) row)
      (count)))

(defn part-1 [seat-map]
  (loop [current-seat-map seat-map]
    (let [adjusted-seat-map (adjust-seating current-seat-map adjacents 4)]
      (if (= current-seat-map adjusted-seat-map)
        (reduce + (map count-occupieds adjusted-seat-map))
        (recur adjusted-seat-map)))))

; (part-1 (pad-seat-map "day11-example-input.txt"))
; (part-1 (pad-seat-map "day11-input.txt"))

(defn get-first-seat [seat-map direction [start-row start-column]]
  (let [max-rows (count seat-map)
        max-cols (count (first seat-map))]
    (if (#{:W :E} direction)
      (loop [columns (if (= :E direction)
                       (range (inc start-column) max-cols)
                       (reverse (range start-column)))
             row start-row]
        (if-let [column (first columns)]
          (let [seat (-> (nth seat-map row)
                         (nth column))]
            (if (= "." seat)
              (recur (rest columns) row)
              seat))
          nil))
      (loop [rows (if (#{:NW :N :NE} direction)
                    (reverse (range start-row))
                    (range (inc start-row) max-rows))
             prev-column start-column]
        (if-let [row (first rows)]
          (let [column (cond
                         (#{:NW :SW} direction) (dec prev-column)
                         (#{:NE :SE} direction) (inc prev-column)
                         (#{:N :S} direction) prev-column)]
            (if (and (pos? column)
                     (< column max-cols))
              (let [seat (-> (nth seat-map row)
                             (nth column))]
                (if (= "." seat)
                  (recur (rest rows) column)
                  seat))
              nil))
            nil)))))

(def directions #{:NW :N :NE :E :SE :S :SW :W})

(defn new-adjacents [seat-map coords]
  (for [direction directions]
    (get-first-seat seat-map direction coords)))

(defn part-2 [seat-map]
  (loop [current-seat-map seat-map]
    (let [adjusted-seat-map (adjust-seating current-seat-map
                                            new-adjacents
                                            5)]
      (if (= current-seat-map adjusted-seat-map)
        (reduce + (map count-occupieds adjusted-seat-map))
        (recur adjusted-seat-map)))))

; (part-2 (pad-seat-map "day11-example-input.txt"))
; (part-2 (pad-seat-map "day11-input.txt"))