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

(facts "about 'valid-iyr?'"
       (fact "should be only 4 digits"
             (d4/valid-iyr? "201") => false
             (d4/valid-iyr? "20101") => false
             (d4/valid-iyr? "abc2010") => false
             (d4/valid-iyr? "2010abc") => false)
       (fact "should be greater than or equal to 2010"
             (d4/valid-iyr? "2009") => false
             (d4/valid-iyr? "2010") => true
             (d4/valid-iyr? "2011") => true)
       (fact "should be less than or equal to 2020"
             (d4/valid-iyr? "2019") => true
             (d4/valid-iyr? "2020") => true
             (d4/valid-iyr? "2021") => false))

(facts "about 'valid-eyr?'"
       (fact "should be only 4 digits"
             (d4/valid-eyr? "202") => false
             (d4/valid-eyr? "20201") => false
             (d4/valid-eyr? "abc2020") => false
             (d4/valid-eyr? "2020abc") => false)
       (fact "should be greater than or equal to 2020"
             (d4/valid-eyr? "2019") => false
             (d4/valid-eyr? "2020") => true
             (d4/valid-eyr? "2021") => true)
       (fact "should be less than or equal to 2030"
             (d4/valid-eyr? "2029") => true
             (d4/valid-eyr? "2030") => true
             (d4/valid-eyr? "2031") => false))

(facts "about 'valid-hgt?'"
       (fact "must be inches or centimeters"
             (d4/valid-hgt? "3ft") => false)
       (fact "must be at least 150 centimeters"
             (d4/valid-hgt? "149cm") => false
             (d4/valid-hgt? "150cm") => true)
       (fact "must be at most 193 centimeters"
             (d4/valid-hgt? "193cm") => true
             (d4/valid-hgt? "194cm") => false)
       (fact "must be at least 59 inches"
             (d4/valid-hgt? "58in") => false
             (d4/valid-hgt? "59in") => true)
       (fact "must me at most 76 inches"
             (d4/valid-hgt? "76in") => true
             (d4/valid-hgt? "77in") => false))

(facts "about 'valid-hcl?'"
       (fact "should start with '#' followed by six characters 0-9a-f"
             (d4/valid-hcl? "#123abc") => true
             (d4/valid-hcl? "#123abz") => false
             (d4/valid-hcl? "123abc") => false))

(facts "about 'valid-ecl?'"
       (fact "should be in the list of valid values"
             (d4/valid-ecl? "amb") => true
             (d4/valid-ecl? "oth") => true
             (d4/valid-ecl? "wat") => false))

(facts "about 'valid-pid?'"
       (fact "should be a 9 digit number (including leading 0s)"
             (d4/valid-pid? "000056789") => true
             (d4/valid-pid? "abc123456789") => false
             (d4/valid-pid? "123456789") => true
             (d4/valid-pid? "12345678a") => false))

(facts "about 'valid-value?'"
       (fact "should be true if the key requires no validation"
             (d4/valid-value? :none "blah") => true)
       (fact "should determine if valid byr"
             (d4/valid-value? :byr "2002") => true
             (d4/valid-value? :byr "2003") => false)
       (fact "should determine if valid iyr"
             (d4/valid-value? :iyr "2020") => true
             (d4/valid-value? :iyr "2021") => false)
       (fact "should determine if valid eyr"
             (d4/valid-value? :eyr "2030") => true
             (d4/valid-value? :eyr "2031") => false)
       (fact "should determine if valid hgt"
             (d4/valid-value? :hgt "3ft") => false
             (d4/valid-value? :hgt "65in") => true)
       (fact "should determine if valid hcl"
             (d4/valid-value? :hcl "#000abc") => true
             (d4/valid-value? :hcl "#999xyz") => false)
       (fact "should determine if valid ecl"
             (d4/valid-value? :ecl "amb") => true
             (d4/valid-value? :ecl "wat") => false)
       (fact "should dtermine if valid pid"
             (d4/valid-value? :pid "123456789") => true
             (d4/valid-value? :pid "a23456789") => false))

(facts "about 'valid-values?'"
       (fact "should validate all values"
             (d4/valid-values? {:none "blah"
                                :byr "2002"
                                :iyr "2010"}) => true
             (d4/valid-values? {:none "blah"
                                :byr "2003"
                                :iyr "2010"}) => false
             (d4/valid-values? {:none "blah"
                                :byr "2002"
                                :iyr "2021"}) => false))