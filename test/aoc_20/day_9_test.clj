(ns aoc-20.day-9-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-9 :as d9]))

(facts "about 'sum-pairs'"
       (fact "should created a set of all the pairs"
             (d9/sum-pairs [1 2 3 4]) => #{3 4 5 6 7}))

(facts "about 'small-and-large'"
       (fact "should give the smallest and largest numbers"
             (d9/small-and-large [7 10 2 3 15 8 9]) => [2 15]
             (d9/small-and-large [1 2 3 4]) => [1 4]
             (d9/small-and-large [4 3 2 1]) => [1 4]))

(facts "about 'add-to'"
       (fact "should be nil if we can't get to this number from the starting number"
             (d9/add-to [35 20 15 25 47 40 62 55] 127) => nil)
       (fact "should return the sum of the smallest and largest numbers that get us to the target amount (consecutively)"
             (d9/add-to [15 25 47 40 62 55] 127) => 62))
