(ns aoc-20.day-10
  (:require [aoc-20.util :as util]))

(defn read-lines [filename]
  (util/read-lines filename))

(defn input-from-file [filename]
  (into [] (for [number (read-lines filename)] (Long/parseLong number))))

;; --- Day 10: Adapter Array ---
;; Patched into the aircraft's data port, you discover weather forecasts
;; of a massive tropical storm. Before you can figure out whether it will
;; impact your vacation plans, however, your device suddenly turns off!
;; 
;; Its battery is dead.
;; 
;; You'll need to plug it in. There's only one problem: the charging outlet
;; near your seat produces the wrong number of jolts. Always prepared, you
;; make a list of all of the joltage adapters in your bag.
;; 
;; Each of your joltage adapters is rated for a specific output joltage
;; (your puzzle input). Any given adapter can take an input 1, 2, or 3
;; jolts lower than its rating and still produce its rated output joltage.
;; 
;; In addition, your device has a built-in joltage adapter rated for 3
;; jolts higher than the highest-rated adapter in your bag. (If your
;; adapter list were 3, 9, and 6, your device's built-in adapter would be
;; rated for 12 jolts.)
;; 
;; Treat the charging outlet near your seat as having an effective joltage
;; rating of 0.
;; 
;; Since you have some time to kill, you might as well test all of your
;; adapters. Wouldn't want to get to your resort and realize you can't
;; even charge your device!
;; 
;; If you use every adapter in your bag at once, what is the distribution
;; of joltage differences between the charging outlet, the adapters, and
;; your device?
;; 
;; Find a chain that uses all of your adapters to connect the charging
;; outlet to your device's built-in adapter and count the joltage
;; differences between the charging outlet, the adapters, and your device.
;; What is the number of 1-jolt differences multiplied by the number of
;; 3-jolt differences?

(defn add-defaults [coll min plus-max]
  (conj coll min (+ plus-max (apply max coll))))

(defn joltages [adapters]
  (map (fn [[x y]]
         (- y x)) (partition 2 1 adapters)))

(defn part-1 [input]
  (let [{ones   1
         threes 3} (-> (add-defaults input 0 3)
                       (sort)
                       (joltages)
                       (frequencies))]
    (* ones threes)))

; (part-1 (input-from-file "day10-example-input.txt"))
; (part-1 (input-from-file "day10-input.txt"))
