(ns aoc-20.day-4-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-4 :as d4]))

(facts "about 'newline->space'"
       (fact "should replace newlines with a space"
             (d4/newline->space "something\nelse") => "something else"))

(facts "about 'split-pair'"
       (fact "should split up pairs into a map"
             (d4/split-pair "something:else") => {:something "else"}))

(facts "about 'parse-passport'"
       (fact "should parse the passport into a hash map"
             (d4/parse-passport "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm") => {:ecl "gry"
                                                                                                                      :pid "860033327"
                                                                                                                      :eyr "2020"
                                                                                                                      :hcl "#fffffd"
                                                                                                                      :byr "1937"
                                                                                                                      :iyr "2017"
                                                                                                                      :cid "147"
                                                                                                                      :hgt "183cm"}))
(facts "about 'required-keys?"
       (fact "should be true if all required keys are present"
             (d4/required-keys? '(:byr :iyr :eyr :hgt :hcl :ecl :pid)) => true
             (d4/required-keys? '(:byr :iyr :eyr :hgt :hcl :ecl :xtra :pid)) => true)
       (fact "should be false if required keys are missing"
             (d4/required-keys? '(:byr :eyr :hcl)) => false
             (d4/required-keys? '(:none)) => false))