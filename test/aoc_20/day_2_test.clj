(ns aoc-20.day-2-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-2 :as d2]))

(deftest letter-count-test
       (testing "should give the number of letters in a string"
             (is (= (d2/letter-count "blah" "b") 1))
             (is (= (d2/letter-count "abbcccdddd" "c") 3))
             (is (= (d2/letter-count "blah" "c") 0))))

(deftest valid-password?-test
       (testing "should verify that the password meets requirements"
             (is (true? (d2/valid-password? "blah" "b" 1 9)))
             (is (true? (d2/valid-password? "blah" "c" 0 9)))
             (is (true? (d2/valid-password? "abcde" "a" 1 3)))
             (is (false? (d2/valid-password? "cdefg" "b" 1 3)))
             (is (true? (d2/valid-password? "ccccccccc" "c" 1 9)))
             (is (false? (d2/valid-password? "cccccccccc" "c" 1 9)))))

(deftest part1-test
       (testing "should give the total number of valid passwords"
             (is (= (d2/part-1 ["1-3 a: abcde"
                         "1-3 b: cdefg"
                         "2-9 c: ccccccccc"])
                    2))))

(deftest char-at-test
       (testing "returns the character at the correct index (non-zero based)"
             (is (= (d2/char-at "blah" 1) "b"))
             (is (= (d2/char-at "blah" 4) "h"))
             (is (nil? (d2/char-at "blah" 5)))
             (is (nil? (d2/char-at "blah" 0)))))

(deftest valid-positions?-test
       (testing "true if the character is at either position"
             (is (true? (d2/valid-positions? "abcde" "a" 1 3)))
             (is (true? (d2/valid-positions? "cbade" "a" 1 3))))
       (testing "false if the character is in neither position"
             (is (false? (d2/valid-positions? "cdefg" "b" 1 3))))
       (testing "false if the character is in both positions"
             (is (false? (d2/valid-positions? "ccccccccc" "c" 2 9)))))

(deftest part2-test
       (testing "should count the number of valid passwords"
             (is (= (d2/part-2 ["1-3 a: abcde"
                         "1-3 b: cdefg"
                         "2-9 c: ccccccccc"])
                    1))))