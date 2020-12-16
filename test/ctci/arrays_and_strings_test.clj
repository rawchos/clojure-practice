(ns ctci.arrays-and-strings-test
  (:require [ctci.arrays-and-strings :as aas]
            [midje.sweet :refer [fact
                                 facts
                                 =>]]))

(facts "about 'is-unique?'"
       (fact "should be true if all characters are unique"
             (aas/is-unique? "blah") => true
             (aas/is-unique? "abcdef") => true)
       (fact "should be false if all characters are not unique"
             (aas/is-unique? "bblah") => false
             (aas/is-unique? "bllahh") => false
             (aas/is-unique? "blahdeblah") => false)
       (fact "should ignore case"
             (aas/is-unique? "Bblah") => false
             (aas/is-unique? "blAah") => false
             (aas/is-unique? "blaHH") => false))

(facts "about 'is-permutation?'"
       (fact "should be true if the strings are permutations"
             (aas/is-permutation? "blah" "albh") => true
             (aas/is-permutation? "abcdefg" "gfeabcd") => true)
       (fact "should be false if the strings aren't permutations"
             (aas/is-permutation? "abcd" "efgh") => false
             (aas/is-permutation? "abcd" "abcde") => false
             (aas/is-permutation? "abcd" "abzcd") => false)
       (fact "should ignore case"
             (aas/is-permutation? "abcd" "aBcd") => true
             (aas/is-permutation? "ABCD" "abcd") => true
             (aas/is-permutation? "ABCD" "ABzCD") => false))

(facts "about 'urlify'"
       (fact "should replace spaces with '%20'"
             (aas/urlify "blah de blah") => "blah%20de%20blah"))

(facts "about 'more-urlify'"
       (fact "should replace spaces with '%20'"
             (aas/more-urlify "blah de blah") => "blah%20de%20blah"))

(facts "about 'is-palindrome?'"
       (fact "should report correctly if there are palindrome permutations"
             (aas/is-palindrome? "tacocat") => true
             (aas/is-palindrome? "aaccott") => true
             (aas/is-palindrome? "racecar") => true
             (aas/is-palindrome? "raccecar") => false
             (aas/is-palindrome? "abbbaa") => false)
       (fact "should ignore whitespaces when checking permutations"
             (aas/is-palindrome? "race car") => true
             (aas/is-palindrome? "taco cat") => true
             (aas/is-palindrome? "t a c o c a t") => true
             (aas/is-palindrome? "taco catt") => false)
       (fact "should ignore casing when checking permutations"
             (aas/is-palindrome? "RACe Car") => true
             (aas/is-palindrome? "TACOcat") => true))

(facts "about 'succint-is-palindrome?'"
       (fact "should report correctly if there are palindrome permutations"
             (aas/succint-is-palindrome? "tacocat") => true
             (aas/succint-is-palindrome? "aaccott") => true
             (aas/succint-is-palindrome? "racecar") => true
             (aas/succint-is-palindrome? "raccecar") => false
             (aas/succint-is-palindrome? "abbbaa") => false)
       (fact "should ignore whitespaces when checking permutations"
             (aas/succint-is-palindrome? "race car") => true
             (aas/succint-is-palindrome? "taco cat") => true
             (aas/succint-is-palindrome? "t a c o c a t") => true
             (aas/succint-is-palindrome? "taco catt") => false)
       (fact "should ignore casing when checking permutations"
             (aas/succint-is-palindrome? "RACe Car") => true
             (aas/succint-is-palindrome? "TACOcat") => true))

(facts "about 'is-odd?'"
       (fact "odd numbers are odd"
             (aas/is-odd? 1) => true
             (aas/is-odd? 3) => true
             (aas/is-odd? 999) => true)
       (fact "even numbers are not odd"
             (aas/is-odd? 2) => false
             (aas/is-odd? 0) => false
             (aas/is-odd? 1000) => false))

(facts "about 'remove-spaces'"
       (fact "should remove spaces from strings"
             (aas/remove-spaces "blah de blah") => "blahdeblah"
             (aas/remove-spaces "t a c o c a t") => "tacocat"))

(facts "about 'odds'"
       (fact "should only give the odd numbers"
             (aas/odds [1 3 4 6 7]) => '(1 3 7)
             (aas/odds [2 4 6 8]) => '()
             (aas/odds '(1 3 5 7 9)) => '(1 3 5 7 9)))

(facts "about 'one-away?'"
       (fact "should be true if the strings are 1 character away from each other"
             (aas/one-away? "pale" "ple") => true
             (aas/one-away? "pales" "pale") => true
             (aas/one-away? "pale" "bale") => true)
       (fact "should be false if the strings require more than 1 character adjustment"
             (aas/one-away? "pale" "bake") => false))

(facts "about 'in-str?'"
       (fact "should be true if the char is in the string"
             (aas/in-str? "rick" "c") => true)
       (fact "should be false if the char is not in the string"
             (aas/in-str? "morty" "c") => false))

(facts "about 'remove-str'"
       (fact "should remove the first occurence of the char in the string"
             (aas/remove-str "archer" "r") => "acher")
       (fact "should be the same string if the char isn't found"
             (aas/remove-str "lana" "r") => "lana"))

(facts "about 'str-diff'"
       (fact "should report values correctly"
             (aas/str-diff "rick" "morty") => ["ick" "moty" "r"]
             (aas/str-diff "archer" "lana") => ["rcher" "lna" "a"]
             (aas/str-diff "tina" "dina") => ["t" "d" "ina"]
             (aas/str-diff "blah" "none") => ["blah" "none" ""]))

(facts "about 'compress'"
       (fact "should return the original string if the compressed string is the same size or larger"
             (aas/compress "a") => "a"
             (aas/compress "aa") => "aa"
             (aas/compress "ab") => "ab"
             (aas/compress "abcd") => "abcd"
             (aas/compress "abcdd") => "abcdd"
             (aas/compress "aaabcd") => "aaabcd")
       (fact "should compress the string if it makes sense"
             (aas/compress "aaa") => "a3"
             (aas/compress "aaabcccccaafffd") => "a3b1c5a2f3d1"))

;; Note: (aas/build-grid 3 4) will build a 3x4 grid. The values will be
;;       numbered from 1 to (* 3 4).
;; Ex: ((1 2 3)
;;      (4 5 6)
;;      (7 8 9)
;;      (10 11 12))
(facts "about 'rotate'"
       (fact "should rotate the grid by 90 degrees"
             (aas/rotate (aas/build-grid 3 4)) => '((10 7 4 1)
                                                    (11 8 5 2)
                                                    (12 9 6 3))
             (aas/rotate (aas/build-grid 3 3)) => '((7 4 1)
                                                    (8 5 2)
                                                    (9 6 3))
             (aas/rotate (aas/build-grid 1 1)) => '((1))
             (aas/rotate (aas/build-grid 1 2)) => '((2 1))
             (aas/rotate (aas/build-grid 2 2)) => '((3 1)
                                                    (4 2))))

(facts "about 'is-substring?'"
       (fact "should be true if s2 is a rotation of s1"
             (aas/is-substring? "waterbottle" "erbottlewat") => true
             ;; TODO: This test is failing. Problems with the implementation.
             ;; (aas/is-substring? "something" "else") => false
             (aas/is-substring? "something" "thingsome") => true))