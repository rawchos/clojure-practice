(ns aoc-20.day-19
  (:require [aoc-20.util :as util]
            [clojure.string :as s]))

(defn read-str [filename]
  (util/read-str filename))

(defn split-input [str-input]
  (s/split str-input #"\n\n"))

(defn input-data [filename]
  (->> (split-input (read-str filename))
       (map s/split-lines)))

(defn part-1 [input]
  input)

((-> (part-1 (input-data "day19-example-input.txt"))
    (first)) 0)
