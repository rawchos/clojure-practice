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

;; TODO: Implement these tests
(facts "about 'in-str?'")

(facts "about 'remove-str'")

(facts "about 'str-diff'")