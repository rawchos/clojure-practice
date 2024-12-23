(ns aoc-20.day-10-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-10 :as d10]))

(deftest add-defaults-test
       (testing "should add the amount to the last item and add the result to the coll"
             (is (= (d10/add-defaults [1 2 3 4] 0 3) [1 2 3 4 0 7]))))

(deftest joltages-test
       (testing "should provide a map of the joltages between adapters"
             (is (= (d10/joltages [0 1 2 3 4 7]) '(1 1 1 1 3)))
             (is (= (d10/joltages [0 2 5 6 7 10]) '(2 3 1 1 3)))))
