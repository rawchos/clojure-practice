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

(facts "about 'to-int'"
       (fact "should be nil if nil is passed in"
             (d4/to-int nil) => nil)
       (fact "should parse to int"
             (d4/to-int "7") => 7))

(facts "about 'valid-byr?'"
       (fact "should be only 4 digits"
             (d4/valid-byr? "192") => false
             (d4/valid-byr? "19201") => false
             (d4/valid-byr? "abc1920") => false
             (d4/valid-byr? "1920abc") => false)
       (fact "should be greater than or equal to 1920"
             (d4/valid-byr? "1919") => false
             (d4/valid-byr? "1920") => true
             (d4/valid-byr? "1921") => true)
       (fact "should be less than or equal to 2002"
             (d4/valid-byr? "2001") => true
             (d4/valid-byr? "2002") => true
             (d4/valid-byr? "2003") => false))

(facts "about 'valid-value?'"
       (fact "should be true if the key requires no validation"
             (d4/valid-value? :none "blah") => true)
       (fact "should determine if valid byr"
             (d4/valid-value? :byr "2002") => true
             (d4/valid-value? :byr "2003") => false))

(facts "about 'valid-values?'"
       (fact "should validate all values"
             (d4/valid-values? {:none "blah"
                                :byr "2002"}) => true
             (d4/valid-values? {:none "blah"
                                :byr "2003"}) => false))