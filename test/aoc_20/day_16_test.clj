(ns aoc-20.day-16-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-16 :as d16]))

(facts "about 'tickets-only'"
       (fact "should grab just the tickets out of the string"
             (d16/tickets-only "your ticket:\n7,1,14") => '((7 1 14))
             (d16/tickets-only "nearby tickets:\n7,3,47\n40,4,50\n55,2,20\n38,6,12") => '((7 3 47)
                                                                                          (40 4 50)
                                                                                          (55 2 20)
                                                                                          (38 6 12))))

(facts "about 'parse-rules'"
       (fact "should parse the rules and turn them into a hashmap of descriptions and valid ranges"
             (d16/parse-rules ["class: 1-3 or 5-7" "row: 6-11 or 33-44" "seat: 13-40 or 45-50" "departure time: 34-754 or 763-960"]) => {:class '((1 3) (5 7))
                                                                                                                                         :row '((6 11) (33 44))
                                                                                                                                         :seat '((13 40) (45 50))
                                                                                                                                         :departure-time '((34 754) (763 960))}))

(facts "about 'valid-field?'"
       (let [rules {:class '((1 3) (5 7))
                    :row '((6 11) (33 44))}]
         (fact "should be true if the value falls within one of the ranges"
               (d16/valid-field? rules 2) => true
               (d16/valid-field? rules 40) => true
               (d16/valid-field? rules 8) => true)
         (fact "should be false if the value doesn't fall within any ranges"
               (d16/valid-field? rules 4) => false
               (d16/valid-field? rules 12) => false
               (d16/valid-field? rules 45) => false)))