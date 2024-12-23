(ns aoc-21.day-02-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-21.day-02 :as d02]))

(deftest convert-instruction-test
  (testing "should convert to a keyword and int"
        (is (= (d02/convert-instruction "forward 2") [:forward 2]))))

(deftest move-point-test
  (testing "should create a point that's the representation of a move"
        (is (= (d02/move-point [:forward 2]) [2 0]))
        (is (= (d02/move-point [:down 5]) [0 5]))
        (is (= (d02/move-point [:up 7]) [0 -7]))))

(deftest navigate-test
  (testing "should navigate from one point to another"
        (is (= (d02/navigate [0 0] [1 5]) [1 5]))
        (is (= (d02/navigate [7 3] [1 -2]) [8 1]))))

(deftest traject-test
  (testing "should traject from one point to another using a complicated ruleset"
        (is (= (d02/traject [1 2 3] [:up 1]) [0 2 3]))
        (is (= (d02/traject [5 5 5] [:down 3]) [8 5 5]))
        (is (= (d02/traject [5 5 5] [:forward 5]) [5 10 30]))))