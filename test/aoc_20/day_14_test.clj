(ns aoc-20.day-14-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-14 :as d14]))

(facts "about 'add-binary-string'"
       (fact "should convert a binary string and add it"
             (d14/add-binary-string 0 "1001001") => 73
             (d14/add-binary-string 2 "000000000000000000000000000000001011") => 13))

(facts "about 'mask-value'"
       (fact "should use the mask to mask the value"
             (d14/mask-value         "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
                             "11") ; "000000000000000000000000000000001011")
                             =>      "000000000000000000000000000001001001"))

(facts "about 'has-x?'"
       (fact "should be true if there are any Xs in the string"
             (d14/has-x? "00X10") => true
             (d14/has-x? "X0010") => true
             (d14/has-x? "0001X") => true)
       (fact "should be false if there aren't any Xs in the string"
             (d14/has-x? "00010") => false))

(facts "about 'convert-x'"
       (fact "should convert the Xs to both 0 and 1 versions"
             (d14/convert-x "00X00") => '("00000" "00100")
             (d14/convert-x "00X00X") => '("000000" "000001" "001000" "001001")))

(facts "about 'mask-address'"
       (fact "should mask the address based on the mask provided"
             (d14/mask-address         "000000000000000000000000000000X1001X"
                               "42") ; "000000000000000000000000000000101010")
                               =>      "000000000000000000000000000000X1101X"))
