(ns aoc-20.day-13-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-13 :as d13]))

(deftest bus-ids-test
       (testing "should remove x's and convert to ints"
             (is (= (d13/bus-ids "7,13,x,x,59,x,31,19") '(7 13 59 31 19)))))

(deftest check-bus-test
       (testing "should find the first increment greater than or equal to the goal"
             (is (= (d13/check-bus 10 {} 3) {12 3}))
             (is (= (d13/check-bus 21 {} 7) {21 7}))
             (is (= (d13/check-bus 30 {} 8) {32 8}))
             (is (= (d13/check-bus 15 {} 72) {72 72}))))
