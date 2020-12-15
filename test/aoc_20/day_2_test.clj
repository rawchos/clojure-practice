(ns aoc-20.day-2-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-2 :as d2]))

(facts "about 'letter-count'"
       (fact "should give the number of letters in a string"
             (d2/letter-count "blah" "b") => 1
             (d2/letter-count "abbcccdddd" "c") => 3
             (d2/letter-count "blah" "c") => 0))

(facts "about 'valid-password?'"
       (fact "should verify that the password meets requirements"
             (d2/valid-password? "blah" "b" 1 9) => true
             (d2/valid-password? "blah" "c" 0 9) => true
             (d2/valid-password? "abcde" "a" 1 3) => true
             (d2/valid-password? "cdefg" "b" 1 3) => false
             (d2/valid-password? "ccccccccc" "c" 1 9) => true
             (d2/valid-password? "cccccccccc" "c" 1 9) => false))

(facts "about 'part-1'"
       (fact "should give the total number of valid passwords"
             (d2/part-1 ["1-3 a: abcde"
                         "1-3 b: cdefg"
                         "2-9 c: ccccccccc"]) => 2))

(facts "about 'char-at'"
       (fact "returns the character at the correct index (non-zero based)"
             (d2/char-at "blah" 1) => "b"
             (d2/char-at "blah" 4) => "h"
             (d2/char-at "blah" 5) => nil
             (d2/char-at "blah" 0) => nil))

(facts "about 'valid-positions?'"
       (fact "true if the character is at either position"
             (d2/valid-positions? "abcde" "a" 1 3) => true
             (d2/valid-positions? "cbade" "a" 1 3) => true)
       (fact "false if the character is in neither position"
             (d2/valid-positions? "cdefg" "b" 1 3) => false)
       (fact "false if the character is in both positions"
             (d2/valid-positions? "ccccccccc" "c" 2 9) => false))

(facts "about 'part-2'"
       (fact "should count the number of valid passwords"
             (d2/part-2 ["1-3 a: abcde"
                         "1-3 b: cdefg"
                         "2-9 c: ccccccccc"]) => 1))