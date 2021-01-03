(ns aoc-20.day-14-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-14 :as d14]))

(facts "about 'add-binary-string'"
       (fact "should convert a binary string and add it"
             (d14/add-binary-string 0 "1001001") => 73
             (d14/add-binary-string 2 "000000000000000000000000000000001011") => 13))
