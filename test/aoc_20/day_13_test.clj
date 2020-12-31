(ns aoc-20.day-13-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-13 :as d13]))

(facts "about 'bus-ids'"
       (fact "should remove x's and convert to ints"
             (d13/bus-ids "7,13,x,x,59,x,31,19") => '(7 13 59 31 19)))

(facts "about 'check-bus'"
       (fact "should find the first increment greater than or equal to the goal"
             (d13/check-bus 10 {} 3) => {12 3}
             (d13/check-bus 21 {} 7) => {21 7}
             (d13/check-bus 30 {} 8) => {32 8}
             (d13/check-bus 15 {} 72) => {72 72}))
