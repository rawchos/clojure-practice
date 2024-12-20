(ns aoc-20.day-4-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-4 :as d4]))

(deftest newline->space-test
       (testing "should replace newlines with a space"
             (is (= (d4/newline->space "something\nelse") "something else"))))

(deftest split-pair-test
       (testing "should split up pairs into a map"
             (is (= (d4/split-pair "something:else") {:something "else"}))))

(deftest parse-passport-test
       (testing "should parse the passport into a hash map"
             (is (= (d4/parse-passport "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm")
                    {:ecl "gry"
                     :pid "860033327"
                     :eyr "2020"
                     :hcl "#fffffd"
                     :byr "1937"
                     :iyr "2017"
                     :cid "147"
                     :hgt "183cm"}))))
(deftest required-keys?-test
       (testing "should be true if all required keys are present"
             (is (true? (d4/required-keys? '(:byr :iyr :eyr :hgt :hcl :ecl :pid))))
             (is (true? (d4/required-keys? '(:byr :iyr :eyr :hgt :hcl :ecl :xtra :pid)))))
       (testing "should be false if required keys are missing"
             (is (false? (d4/required-keys? '(:byr :eyr :hcl))))
             (is (false? (d4/required-keys? '(:none))))))

(deftest to-int-test
       (testing "should be nil if nil is passed in"
             (is (nil? (d4/to-int nil))))
       (testing "should parse to int"
             (is (= (d4/to-int "7") 7))))

(deftest valid-byr?-test
       (testing "should be only 4 digits"
             (is (false? (d4/valid-byr? "192")))
             (is (false? (d4/valid-byr? "19201")))
             (is (false? (d4/valid-byr? "abc1920")))
             (is (false? (d4/valid-byr? "1920abc"))))
       (testing "should be greater than or equal to 1920"
             (is (false? (d4/valid-byr? "1919")))
             (is (true? (d4/valid-byr? "1920")))
             (is (true? (d4/valid-byr? "1921"))))
       (testing "should be less than or equal to 2002"
             (is (true? (d4/valid-byr? "2001")))
             (is (true? (d4/valid-byr? "2002")))
             (is (false? (d4/valid-byr? "2003")))))

(deftest valid-iyr?-test
       (testing "should be only 4 digits"
             (is (false? (d4/valid-iyr? "201")))
             (is (false? (d4/valid-iyr? "20101")))
             (is (false? (d4/valid-iyr? "abc2010")))
             (is (false? (d4/valid-iyr? "2010abc"))))
       (testing "should be greater than or equal to 2010"
             (is (false? (d4/valid-iyr? "2009")))
             (is (true? (d4/valid-iyr? "2010")))
             (is (true? (d4/valid-iyr? "2011"))))
       (testing "should be less than or equal to 2020"
             (is (true? (d4/valid-iyr? "2019")))
             (is (true? (d4/valid-iyr? "2020")))
             (is (false? (d4/valid-iyr? "2021")))))

(deftest valid-eyr?-test
       (testing "should be only 4 digits"
             (is (false? (d4/valid-eyr? "202")))
             (is (false? (d4/valid-eyr? "20201")))
             (is (false? (d4/valid-eyr? "abc2020")))
             (is (false? (d4/valid-eyr? "2020abc"))))
       (testing "should be greater than or equal to 2020"
             (is (false? (d4/valid-eyr? "2019")))
             (is (true? (d4/valid-eyr? "2020")))
             (is (true? (d4/valid-eyr? "2021"))))
       (testing "should be less than or equal to 2030"
             (is (true? (d4/valid-eyr? "2029")))
             (is (true? (d4/valid-eyr? "2030")))
             (is (false? (d4/valid-eyr? "2031")))))

(deftest valid-hgt?-test
       (testing "must be inches or centimeters"
             (is (false? (d4/valid-hgt? "3ft"))))
       (testing "must be at least 150 centimeters"
             (is (false? (d4/valid-hgt? "149cm")))
             (is (true? (d4/valid-hgt? "150cm"))))
       (testing "must be at most 193 centimeters"
             (is (true? (d4/valid-hgt? "193cm")))
             (is (false? (d4/valid-hgt? "194cm"))))
       (testing "must be at least 59 inches"
             (is (false? (d4/valid-hgt? "58in")))
             (is (true? (d4/valid-hgt? "59in"))))
       (testing "must me at most 76 inches"
             (is (true? (d4/valid-hgt? "76in")))
             (is (false? (d4/valid-hgt? "77in")))))

(deftest valid-hcl?-test
       (testing "should start with '#' followed by six characters 0-9a-f"
             (is (true? (d4/valid-hcl? "#123abc")))
             (is (false? (d4/valid-hcl? "#123abz")))
             (is (false? (d4/valid-hcl? "123abc")))))

(deftest valid-ecl?-test
       (testing "should be in the list of valid values"
             (is (true? (d4/valid-ecl? "amb")))
             (is (true? (d4/valid-ecl? "oth")))
             (is (false? (d4/valid-ecl? "wat")))))

(deftest valid-pid?-test
       (testing "should be a 9 digit number (including leading 0s)"
             (is (true? (d4/valid-pid? "000056789")))
             (is (false? (d4/valid-pid? "abc123456789")))
             (is (true? (d4/valid-pid? "123456789")))
             (is (false? (d4/valid-pid? "12345678a")))))

(deftest valid-value?-test
       (testing "should be true if the key requires no validation"
             (is (true? (d4/valid-value? :none "blah"))))
       (testing "should determine if valid byr"
             (is (true? (d4/valid-value? :byr "2002")))
             (is (false? (d4/valid-value? :byr "2003"))))
       (testing "should determine if valid iyr"
             (is (true? (d4/valid-value? :iyr "2020")))
             (is (false? (d4/valid-value? :iyr "2021"))))
       (testing "should determine if valid eyr"
             (is (true? (d4/valid-value? :eyr "2030")))
             (is (false? (d4/valid-value? :eyr "2031"))))
       (testing "should determine if valid hgt"
             (is (false? (d4/valid-value? :hgt "3ft")))
             (is (true? (d4/valid-value? :hgt "65in"))))
       (testing "should determine if valid hcl"
             (is (true? (d4/valid-value? :hcl "#000abc")))
             (is (false? (d4/valid-value? :hcl "#999xyz"))))
       (testing "should determine if valid ecl"
             (is (true? (d4/valid-value? :ecl "amb")))
             (is (false? (d4/valid-value? :ecl "wat"))))
       (testing "should dtermine if valid pid"
             (is (true? (d4/valid-value? :pid "123456789")))
             (is (false? (d4/valid-value? :pid "a23456789")))))

(deftest valid-values?-test
       (testing "should validate all values"
             (is (true? (d4/valid-values? {:none "blah"
                                :byr "2002"
                                :iyr "2010"})))
             (is (false? (d4/valid-values? {:none "blah"
                                :byr "2003"
                                :iyr "2010"})))
             (is (false? (d4/valid-values? {:none "blah"
                                :byr "2002"
                                :iyr "2021"})))))