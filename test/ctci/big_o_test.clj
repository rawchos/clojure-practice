(ns ctci.big-o-test
  (:require [ctci.big-o :as bo]
            [midje.sweet :refer [facts
                                 fact
                                 =>]]))

(facts "about 'is-working?'"
       (fact "should be true if everything setup right"
             (bo/is-working?) => true))