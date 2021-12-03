(ns aoc-21.day-02
  (:require [aoc-utils.core :as util]
            [clojure.string :as str]))

(def sample-instructions ["forward 5"
                          "down 5"
                          "forward 8"
                          "up 3"
                          "down 8"
                          "forward 2"])

(defn convert-instruction [instruction]
  (let [[direction distance] (str/split instruction #" ")]
    [(keyword direction) (util/to-int distance)]))

(defn move-point
  "Takes an instruction and a distance and creates a point that would represent
   the necessary move when added to another point."
  [[direction distance]]
  (case direction
    :forward [distance 0]
    :up      [0 (- distance)]
    :down    [0 distance]
    [0 0]))

(defn navigate [[start-x start-y] [move-x move-y]]
  [(+ start-x move-x) (+ start-y move-y)])

(defn traject [[aim x y] [direction distance]]
  (case direction
    :down    [(+ aim distance) x y]
    :up      [(- aim distance) x y]
    :forward [aim (+ x distance) (+ y (* aim distance))]
    [aim x y]))

(defn get-input []
  (util/read-lines "21" "day02.txt"))

(defn part-1 []
  (->> (get-input)
       (map convert-instruction)
       (map move-point)
       (reduce navigate [0 0])
       (apply *)))

(defn part-2 []
  (->> (get-input)
       (map convert-instruction)
       (reduce traject [0 0 0])
       rest
       (apply *)))

(comment
  (convert-instruction (first sample-instructions))
  (->> (map convert-instruction sample-instructions)
       (map move-point)
       (reduce navigate [0 0])
       (apply *))
  (part-1) ; 2036120
  
  (->> (map convert-instruction sample-instructions)
       (reduce traject [0 0 0])
       rest
       (apply *))
  (part-2) ; 2015547716
  )
