(ns ctci.arrays-and-strings-test
  (:require [ctci.arrays-and-strings :as aas]
            [clojure.test :refer [deftest is testing]]))

(deftest is-unique?-test
       (testing "should be true if all characters are unique"
             (is (true? (aas/is-unique? "blah")))
             (is (true? (aas/is-unique? "abcdef"))))
       (testing "should be false if all characters are not unique"
             (is (false? (aas/is-unique? "bblah")))
             (is (false? (aas/is-unique? "bllahh")))
             (is (false? (aas/is-unique? "blahdeblah"))))
       (testing "should ignore case"
             (is (false? (aas/is-unique? "Bblah")))
             (is (false? (aas/is-unique? "blAah")))
             (is (false? (aas/is-unique? "blaHH")))))

(deftest is-permutation?-test
       (testing "should be true if the strings are permutations"
             (is (true? (aas/is-permutation? "blah" "albh")))
             (is (true? (aas/is-permutation? "abcdefg" "gfeabcd"))))
       (testing "should be false if the strings aren't permutations"
             (is (false? (aas/is-permutation? "abcd" "efgh")))
             (is (false? (aas/is-permutation? "abcd" "abcde")))
             (is (false? (aas/is-permutation? "abcd" "abzcd"))))
       (testing "should ignore case"
             (is (true? (aas/is-permutation? "abcd" "aBcd")))
             (is (true? (aas/is-permutation? "ABCD" "abcd")))
             (is (false? (aas/is-permutation? "ABCD" "ABzCD")))))

(deftest urlify-test
       (testing "should replace spaces with '%20'"
             (is (= (aas/urlify "blah de blah") "blah%20de%20blah"))))

(deftest more-urlify-test
       (testing "should replace spaces with '%20'"
             (is (= (aas/more-urlify "blah de blah") "blah%20de%20blah"))))

(deftest is-palindrome?-test
       (testing "should report correctly if there are palindrome permutations"
             (is (true? (aas/is-palindrome? "tacocat")))
             (is (true? (aas/is-palindrome? "aaccott")))
             (is (true? (aas/is-palindrome? "racecar")))
             (is (false? (aas/is-palindrome? "raccecar")))
             (is (false? (aas/is-palindrome? "abbbaa"))))
       (testing "should ignore whitespaces when checking permutations"
             (is (true? (aas/is-palindrome? "race car")))
             (is (true? (aas/is-palindrome? "taco cat")))
             (is (true? (aas/is-palindrome? "t a c o c a t")))
             (is (false? (aas/is-palindrome? "taco catt"))))
       (testing "should ignore casing when checking permutations"
             (is (true? (aas/is-palindrome? "RACe Car")))
             (is (true? (aas/is-palindrome? "TACOcat")))))

(deftest succint-is-palindrome?-test
       (testing "should report correctly if there are palindrome permutations"
             (is (true? (aas/succint-is-palindrome? "tacocat")))
             (is (true? (aas/succint-is-palindrome? "aaccott")))
             (is (true? (aas/succint-is-palindrome? "racecar")))
             (is (false? (aas/succint-is-palindrome? "raccecar")))
             (is (false? (aas/succint-is-palindrome? "abbbaa"))))
       (testing "should ignore whitespaces when checking permutations"
             (is (true? (aas/succint-is-palindrome? "race car")))
             (is (true? (aas/succint-is-palindrome? "taco cat")))
             (is (true? (aas/succint-is-palindrome? "t a c o c a t")))
             (is (false? (aas/succint-is-palindrome? "taco catt"))))
       (testing "should ignore casing when checking permutations"
             (is (true? (aas/succint-is-palindrome? "RACe Car")))
             (is (true? (aas/succint-is-palindrome? "TACOcat")))))

(deftest is-odd?-test
       (testing "odd numbers are odd"
             (is (true? (aas/is-odd? 1)))
             (is (true? (aas/is-odd? 3)))
             (is (true? (aas/is-odd? 999))))
       (testing "even numbers are not odd"
             (is (false? (aas/is-odd? 2)))
             (is (false? (aas/is-odd? 0)))
             (is (false? (aas/is-odd? 1000)))))

(deftest remove-spaces-test
       (testing "should remove spaces from strings"
             (is (= (aas/remove-spaces "blah de blah") "blahdeblah"))
             (is (= (aas/remove-spaces "t a c o c a t") "tacocat"))))

(deftest odds-test
       (testing "should only give the odd numbers"
             (is (= (aas/odds [1 3 4 6 7]) '(1 3 7)))
             (is (= (aas/odds [2 4 6 8]) '()))
             (is (= (aas/odds '(1 3 5 7 9)) '(1 3 5 7 9)))))

(deftest one-away?-test
       (testing "should be true if the strings are 1 character away from each other"
             (is (true? (aas/one-away? "pale" "ple")))
             (is (true? (aas/one-away? "pales" "pale")))
             (is (true? (aas/one-away? "pale" "bale"))))
       (testing "should be false if the strings require more than 1 character adjustment"
             (is (false? (aas/one-away? "pale" "bake")))))

(deftest in-str?-test
       (testing "should be true if the char is in the string"
             (is (true? (aas/in-str? "rick" "c"))))
       (testing "should be false if the char is not in the string"
             (is (false? (aas/in-str? "morty" "c")))))

(deftest remove-str-test
       (testing "should remove the first occurence of the char in the string"
             (is (= (aas/remove-str "archer" "r") "acher")))
       (testing "should be the same string if the char isn't found"
             (is (= (aas/remove-str "lana" "r") "lana"))))

(deftest str-diff-test
       (testing "should report values correctly"
             (is (= (aas/str-diff "rick" "morty") ["ick" "moty" "r"]))
             (is (= (aas/str-diff "archer" "lana") ["rcher" "lna" "a"]))
             (is (= (aas/str-diff "tina" "dina") ["t" "d" "ina"]))
             (is (= (aas/str-diff "blah" "none") ["blah" "none" ""]))))

(deftest compress-test
       (testing "should return the original string if the compressed string is the same size or larger"
             (is (= (aas/compress "a") "a"))
             (is (= (aas/compress "aa") "aa"))
             (is (= (aas/compress "ab") "ab"))
             (is (= (aas/compress "abcd") "abcd"))
             (is (= (aas/compress "abcdd") "abcdd"))
             (is (= (aas/compress "aaabcd") "aaabcd")))
       (testing "should compress the string if it makes sense"
             (is (= (aas/compress "aaa") "a3"))
             (is (= (aas/compress "aaabcccccaafffd") "a3b1c5a2f3d1"))))

;; Note: (aas/build-grid 3 4) will build a 3x4 grid. The values will be
;;       numbered from 1 to (* 3 4).
;; Ex: ((1 2 3)
;;      (4 5 6)
;;      (7 8 9)
;;      (10 11 12))
(deftest rotate-test
       (testing "should rotate the grid by 90 degrees"
             (is (= (aas/rotate (aas/build-grid 3 4)) '((10 7 4 1)
                                                    (11 8 5 2)
                                                    (12 9 6 3))))
             (is (= (aas/rotate (aas/build-grid 3 3)) '((7 4 1)
                                                    (8 5 2)
                                                    (9 6 3))))
             (is (= (aas/rotate (aas/build-grid 1 1)) '((1))))
             (is (= (aas/rotate (aas/build-grid 1 2)) '((2 1))))
             (is (= (aas/rotate (aas/build-grid 2 2)) '((3 1)
                                                    (4 2))))))

(deftest is-substring?-test
       (testing "should be true if s2 is a rotation of s1"
             (is (true? (aas/is-substring? "waterbottle" "erbottlewat")))
             (is (false? (aas/is-substring? "something" "else")))
             (is (true? (aas/is-substring? "something" "thingsome")))))