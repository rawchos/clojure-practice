(ns aoc-20.day-10-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-10 :as d10]))

(facts "about 'addto-last'"
       (fact "should add the amount to the last item and add the result to the coll"
             (d10/addto-last [1 2 3 4] 3) => [1 2 3 4 7]))
