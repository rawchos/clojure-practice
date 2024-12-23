(ns aoc-20.day-18-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-18 :as d18]))

(deftest calculate-test
       (testing "should calculate the result of the operations working from left to right"
             (is (= (d18/calculate "1 + 2") 3))
             (is (= (d18/calculate "1 + 2 - 4") -1))
             (is (= (d18/calculate "1 + 2 - 4 * 7") -7))))

(deftest escape-specials-test
       (testing "should escape special characters in a string"
             (is (= (d18/escape-specials "(1 + 4)") "\\(1 \\+ 4\\)"))))

(deftest process-parens-test
       (testing "should process parenthesis from operations from the inside out"
             (is (= (d18/process-parens "1 + (3 * 2)") "1 + 6"))
             (is (= (d18/process-parens "1 + (3 * (1 + 4))") "1 + 15"))
             (is (= (d18/process-parens "1 + (3 * (1 + 4)) + 7") "1 + 15 + 7"))
             (is (= (d18/process-parens "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2") "6810 + 2 + 4 * 2"))))

(deftest process-line-test
       (testing "should perform the operations and return the result"
             (is (= (d18/process-line "1 + 2 * 3 + 4 * 5 + 6") 71))
             (is (= (d18/process-line "1 + (2 * 3) + (4 * (5 + 6))") 51))
             (is (= (d18/process-line "2 * 3 + (4 * 5)") 26))
             (is (= (d18/process-line "5 + (8 * 3 + 9 + 3 * 4 * 3)") 437))
             (is (= (d18/process-line "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))") 12240))
             (is (= (d18/process-line "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2") 13632))))

(deftest calculate-adds-test
       (testing "should calculate the additions and leave the multiplications"
             (is (= (d18/calculate-adds "1 + 2 * 3 + 4 * 5 + 6") "3 * 7 * 11"))))