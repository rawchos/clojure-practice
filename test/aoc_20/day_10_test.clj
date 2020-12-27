(ns aoc-20.day-10-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-10 :as d10]))

(facts "about 'add-defaults'"
       (fact "should add the amount to the last item and add the result to the coll"
             (d10/add-defaults [1 2 3 4] 0 3) => [1 2 3 4 0 7]))

(facts "about 'joltages'"
       (fact "should provide a map of the joltages between adapters"
             (d10/joltages [0 1 2 3 4 7]) => '(1 1 1 1 3)
             (d10/joltages [0 2 5 6 7 10]) => '(2 3 1 1 3)))
