(ns ctci.big-o-test
  (:require [ctci.big-o :as bo]
            [midje.sweet :refer [facts
                                 fact
                                 =>]]))

(facts "about 'recur-sum'"
       (fact "should add up the numbers correctly"
             (bo/recur-sum 7) => 28
             (bo/recur-sum 10) => 55))

(facts "about 'reduce-sum'"
       (fact "should add up the numbers correctly"
             (bo/reduce-sum 7) => 28
             (bo/reduce-sum 10) => 55))