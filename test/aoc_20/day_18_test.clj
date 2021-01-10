(ns aoc-20.day-18-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-18 :as d18]))

(facts "about 'calculate'"
       (fact "should calculate the result of the operations working from left to right"
             (d18/calculate "1 + 2") => 3
             (d18/calculate "1 + 2 - 4") => -1
             (d18/calculate "1 + 2 - 4 * 7") => -7))

(facts "about 'escape-specials'"
       (fact "should escape special characters in a string"
             (d18/escape-specials "(1 + 4)") => "\\(1 \\+ 4\\)"))

(facts "about 'process-parens'"
       (fact "should process parenthesis from operations from the inside out"
             (d18/process-parens "1 + (3 * 2)") => "1 + 6"
             (d18/process-parens "1 + (3 * (1 + 4))") => "1 + 15"
             (d18/process-parens "1 + (3 * (1 + 4)) + 7") => "1 + 15 + 7"
             (d18/process-parens "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2") => "6810 + 2 + 4 * 2"))

(facts "about 'process-line'"
       (fact "should perform the operations and return the result"
             (d18/process-line "1 + 2 * 3 + 4 * 5 + 6") => 71
             (d18/process-line "1 + (2 * 3) + (4 * (5 + 6))") => 51
             (d18/process-line "2 * 3 + (4 * 5)") => 26
             (d18/process-line "5 + (8 * 3 + 9 + 3 * 4 * 3)") => 437
             (d18/process-line "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))") => 12240
             (d18/process-line "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2") => 13632))