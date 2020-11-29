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