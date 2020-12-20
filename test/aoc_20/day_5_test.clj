(ns aoc-20.day-5-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-5 :as d5]
            [clojure.string :as s]))

(facts "about 'find-row'"
       (fact "should parse the row correctly"
             (d5/find-row (s/split "BBFFBBF" #"")) => 102
             (d5/find-row (s/split "BFFFBBF" #"")) => 70
             (d5/find-row (s/split "FFFBBBF" #"")) => 14
             (d5/find-row (s/split "FBFBBFF" #"")) => 44))

(facts "about 'find-seat'"
       (fact "should parse the seat correctly"
             (d5/find-seat (s/split "RRR" #"")) => 7
             (d5/find-seat (s/split "RLL" #"")) => 4
             (d5/find-seat (s/split "RLR" #"")) => 5))

(facts "about 'seat-id'"
       (fact "should determine the seat id correctly"
             (d5/seat-id 70 7) => 567
             (d5/seat-id 14 7) => 119
             (d5/seat-id 102 4) => 820))

(facts "about 'parse-ticket'"
       (fact "should parse the ticket correctly"
             (d5/parse-ticket "BFFFBBFRRR") => {567 [70 7]}
             (d5/parse-ticket "FFFBBBFRRR") => {119 [14 7]}
             (d5/parse-ticket "BBFFBBFRLL") => {820 [102 4]}
             (d5/parse-ticket "FBFBBFFRLR") => {357 [44 5]}))