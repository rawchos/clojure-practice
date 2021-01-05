(ns aoc-20.day-15)

;; -- Part 1 --

(def example-input [0 3 6])
(def puzzle-input [6 4 12 1 20 0 16])

(defn primer 
  "After priming the numbers, the next turn would be to evaluate the
   last number that is processed here. Since it'll be the first time
   that number was said, the next number would be 0. Effectively, we're
   skipping a turn here before returning."
  [numbers]
  [(inc (count numbers))
   0
   (into {} (map-indexed (fn [idx number]
                           {number (inc idx)}) numbers))])

(defn next-number [last-number turn numbers]
  (if-let [most-recent (get numbers last-number)]
    (- (dec turn) most-recent)
    0))

(defn part-1 [input]
  (loop [[last-turn
         last-number
         all-numbers] (primer input)]
    (let [this-turn (inc last-turn)
          this-number (next-number last-number this-turn all-numbers)]
      (if (= 2020 this-turn)
        this-number
        (recur [this-turn
               this-number
               (assoc all-numbers last-number last-turn)])))))

; (part-1 example-input)
; (part-1 puzzle-input)
