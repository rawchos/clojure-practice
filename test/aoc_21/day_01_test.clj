(ns aoc-21.day-01-test
  (:require [midje.sweet :refer [fact facts => provided]]
            [aoc-21.day-01 :as d01]))

(facts "about 'count-increases'"
  (fact "should count the increases in a collection"
        (d01/count-increases d01/sample-readings) => 7))

(facts "about 'p2-input'"
  (fact "should sum a collection of numbers by a sliding window of 3"
        (d01/p2-input) => '(607 618 618 617 647 716 769 792)
        (provided (d01/get-input) => d01/sample-readings)))