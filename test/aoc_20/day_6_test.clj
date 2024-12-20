(ns aoc-20.day-6-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-6 :as d6]))

(deftest count-batch-test
       (testing "should count batches correctly"
             (is (= (d6/count-batch "abc") 3))
             (is (= (d6/count-batch "a\nb\nc") 3))
             (is (= (d6/count-batch "ab\nac") 3))
             (is (= (d6/count-batch "a\na\na\na") 1))
             (is (= (d6/count-batch "b") 1))))

(deftest modified-count-batch-test
       (testing "should count the batches correctly"
             (is (= (d6/modified-count-batch "abc") 3))
             (is (= (d6/modified-count-batch "a\nb\nc") 0))
             (is (= (d6/modified-count-batch "ab\nac") 1))
             (is (= (d6/modified-count-batch "a\na\na\na") 1))
             (is (= (d6/modified-count-batch "b") 1))))
