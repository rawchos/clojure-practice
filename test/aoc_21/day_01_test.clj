(ns aoc-21.day-01-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-21.day-01 :as d01]))

(deftest count-increases-test
  (testing "should count the increases in a collection"
        (is (= (d01/count-increases d01/sample-readings) 7))))

; TODO: May have to come back to this after tests are running again
(deftest p2-input-test
  (with-redefs [d01/get-input (constantly d01/sample-readings)]
    (testing "should sum a collection of numbers by a sliding window of 3"
      (is (= (d01/p2-input) '(607 618 618 617 647 716 769 792))))))
      ; (provided (d01/get-input) => d01/sample-readings))))