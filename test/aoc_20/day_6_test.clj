(ns aoc-20.day-6-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-6 :as d6]))

(facts "about 'count-batch'"
       (fact "should count batches correctly"
             (d6/count-batch "abc") => 3
             (d6/count-batch "a\nb\nc") => 3
             (d6/count-batch "ab\nac") => 3
             (d6/count-batch "a\na\na\na") => 1
             (d6/count-batch "b") => 1))

(facts "about 'modified-count-batch'"
       (fact "should count the batches correctly"
             (d6/modified-count-batch "abc") => 3
             (d6/modified-count-batch "a\nb\nc") => 0
             (d6/modified-count-batch "ab\nac") => 1
             (d6/modified-count-batch "a\na\na\na") => 1
             (d6/modified-count-batch "b") => 1))
