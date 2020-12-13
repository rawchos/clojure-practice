(ns aoc-20.day-1-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-1 :as d1]))

(facts "about 'part-1'"
       (fact "should mulitiply the 2 numbers that sum up to 2020"
             (d1/part-1 [1721 979 366 299 675 1456]) => 514579))