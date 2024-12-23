(ns aoc-20.day-9-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-9 :as d9]))

(deftest sum-pairs-test
       (testing "should created a set of all the pairs"
             (is (= (d9/sum-pairs [1 2 3 4]) #{3 4 5 6 7}))))

(deftest small-and-large-test
       (testing "should give the smallest and largest numbers"
             (is (= (d9/small-and-large [7 10 2 3 15 8 9]) [2 15]))
             (is (= (d9/small-and-large [1 2 3 4]) [1 4]))
             (is (= (d9/small-and-large [4 3 2 1]) [1 4]))))

(deftest add-to-test
       (testing "should be nil if we can't get to this number from the starting number"
             (is (nil? (d9/add-to [35 20 15 25 47 40 62 55] 127))))
       (testing "should return the sum of the smallest and largest numbers that get us to the target amount (consecutively)"
             (is (= (d9/add-to [15 25 47 40 62 55] 127) 62))))
