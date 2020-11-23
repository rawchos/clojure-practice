(ns clojure-practice.core-test
  (:require [clojure-practice.core :as core]
            [midje.sweet :refer [fact
                                 facts
                                 =>]]))
(facts "about 'contrived-test'"
       (fact "the contrived test should return true"
             (core/contrived-test) => true))
