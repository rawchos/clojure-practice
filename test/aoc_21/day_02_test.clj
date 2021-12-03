(ns aoc-21.day-02-test
  (:require [midje.sweet :refer [fact facts =>]]
            [aoc-21.day-02 :as d02]))

(facts "about 'convert-instruction'"
  (fact "should convert to a keyword and int"
        (d02/convert-instruction "forward 2") => [:forward 2]))

(facts "about 'move-point'"
  (fact "should create a point that's the representation of a move"
        (d02/move-point [:forward 2]) => [2 0]
        (d02/move-point [:down 5]) => [0 5]
        (d02/move-point [:up 7]) => [0 -7]))

(facts "about 'navigate'"
  (fact "should navigate from one point to another"
        (d02/navigate [0 0] [1 5]) => [1 5]
        (d02/navigate [7 3] [1 -2]) => [8 1]))

(facts "about 'traject'"
  (fact "should traject from one point to another using a complicated ruleset"
        (d02/traject [1 2 3] [:up 1]) => [0 2 3]
        (d02/traject [5 5 5] [:down 3]) => [8 5 5]
        (d02/traject [5 5 5] [:forward 5]) => [5 10 30]))