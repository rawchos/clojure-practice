(ns aoc-20.day-5-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-5 :as d5]
            [clojure.string :as s]))

(deftest find-row-test
       (testing "should parse the row correctly"
             (is (= (d5/find-row (s/split "BBFFBBF" #"")) 102))
             (is (= (d5/find-row (s/split "BFFFBBF" #"")) 70))
             (is (= (d5/find-row (s/split "FFFBBBF" #"")) 14))
             (is (= (d5/find-row (s/split "FBFBBFF" #"")) 44))))

(deftest find-seat-test
       (testing "should parse the seat correctly"
             (is (= (d5/find-seat (s/split "RRR" #"")) 7))
             (is (= (d5/find-seat (s/split "RLL" #"")) 4))
             (is (= (d5/find-seat (s/split "RLR" #"")) 5))))

(deftest seat-id-test
       (testing "should determine the seat id correctly"
             (is (= (d5/seat-id 70 7) 567))
             (is (= (d5/seat-id 14 7) 119))
             (is (= (d5/seat-id 102 4) 820))))

(deftest parse-ticket-test
       (testing "should parse the ticket correctly"
             (is (= (d5/parse-ticket "BFFFBBFRRR") {567 [70 7]}))
             (is (= (d5/parse-ticket "FFFBBBFRRR") {119 [14 7]}))
             (is (= (d5/parse-ticket "BBFFBBFRLL") {820 [102 4]}))
             (is (= (d5/parse-ticket "FBFBBFFRLR") {357 [44 5]}))))

(deftest my-seat?-test
       (testing "should be true if the id +1 and -1 exist"
             (is (true? (d5/my-seat? {2 [1 2]
                           3 [3 4]
                           4 [5 6]} 3))))
       (testing "should be false if either of the +1 or the -1 don't exist"
             (is (false? (d5/my-seat? {3 [3 4]
                           4 [5 6]} 3)))
             (is (false? (d5/my-seat? {2 [1 2]
                           3 [3 4]} 3)))
             (is (false? (d5/my-seat? {3 [3 4]} 3)))))