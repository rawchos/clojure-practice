(ns aoc-20.day-3-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [clojure.string :as s]
            [aoc-20.day-3 :as d3]))

(facts "about 'tree?'"
       (fact "should be true if the thing is '#'"
             (d3/tree? "#") => true)
       (fact "should be false if anything else"
             (d3/tree? ".") => false
             (d3/tree? nil) => false))

(facts "about 'next-column'"
       (fact "should wrap around the column width"
             (d3/next-column 1 3 4) => 0
             (d3/next-column 2 3 4) => 1
             (d3/next-column 0 3 4) => 3))

(facts "about 'part-1'"
       (let [input ["..##......."
                    "#...#...#.."
                    ".#....#..#."
                    "..#.#...#.#"
                    ".#...##..#."
                    "..#.##....."
                    ".#.#.#....#"
                    ".#........#"
                    "#.##...#..."
                    "#...##....#"
                    ".#..#...#.#"]
             grid (for [row input]
                    (s/split row #""))]
         (fact "should correctly count the number of trees hit"
               (d3/part-1 grid) => 7)))