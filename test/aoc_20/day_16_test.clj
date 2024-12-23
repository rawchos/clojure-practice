(ns aoc-20.day-16-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-16 :as d16]))

(deftest tickets-only-test
       (testing "should grab just the tickets out of the string"
             (is (= (d16/tickets-only "your ticket:\n7,1,14") '((7 1 14))))
             (is (= (d16/tickets-only "nearby tickets:\n7,3,47\n40,4,50\n55,2,20\n38,6,12") '((7 3 47)
                                                                                              (40 4 50)
                                                                                              (55 2 20)
                                                                                              (38 6 12))))))

(deftest parse-rules-test
       (testing "should parse the rules and turn them into a hashmap of descriptions and valid ranges"
             (is (= (d16/parse-rules ["class: 1-3 or 5-7" "row: 6-11 or 33-44" "seat: 13-40 or 45-50" "departure time: 34-754 or 763-960"])
                    {:class '((1 3) (5 7))
                     :row '((6 11) (33 44))
                     :seat '((13 40) (45 50))
                     :departure-time '((34 754) (763 960))}))))

(deftest valid-field?-test
       (let [rules {:class '((1 3) (5 7))
                    :row '((6 11) (33 44))}]
         (testing "should be true if the value falls within one of the ranges"
               (is (true? (d16/valid-field? rules 2)))
               (is (true? (d16/valid-field? rules 40)))
               (is (true? (d16/valid-field? rules 8))))
         (testing "should be false if the value doesn't fall within any ranges"
               (is (false? (d16/valid-field? rules 4)))
               (is (false? (d16/valid-field? rules 12)))
               (is (false? (d16/valid-field? rules 45))))))