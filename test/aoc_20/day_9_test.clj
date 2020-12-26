(ns aoc-20.day-9-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-9 :as d9]))

(facts "about 'sum-pairs'"
       (fact "should created a set of all the pairs"
             (d9/sum-pairs [1 2 3 4]) => #{3 4 5 6 7}))
