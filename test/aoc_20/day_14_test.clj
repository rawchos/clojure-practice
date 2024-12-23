(ns aoc-20.day-14-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-14 :as d14]))

(deftest add-binary-string-test
       (testing "should convert a binary string and add it"
             (is (= (d14/add-binary-string 0 "1001001") 73))
             (is (= (d14/add-binary-string 2 "000000000000000000000000000000001011") 13))))

(deftest mask-value-test
       (testing "should use the mask to mask the value"
             (is (= (d14/mask-value "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X" "11")
                                  ; "000000000000000000000000000000001011")
                                    "000000000000000000000000000001001001"))))

(deftest has-x?-test
       (testing "should be true if there are any Xs in the string"
             (is (true? (d14/has-x? "00X10")))
             (is (true? (d14/has-x? "X0010")))
             (is (true? (d14/has-x? "0001X"))))
       (testing "should be false if there aren't any Xs in the string"
             (is (false? (d14/has-x? "00010")))))

(deftest convert-x-test
       (testing "should convert the Xs to both 0 and 1 versions"
             (is (= (d14/convert-x "00X00") '("00000" "00100")))
             (is (= (d14/convert-x "00X00X") '("000000" "000001" "001000" "001001")))))

(deftest mask-address-test
       (testing "should mask the address based on the mask provided"
             (is (= (d14/mask-address "000000000000000000000000000000X1001X" "42")
                                    ; "000000000000000000000000000000101010")
                                      "000000000000000000000000000000X1101X"))))
