(ns ctci.arrays-and-strings-test
  (:require [ctci.arrays-and-strings :as aas]
            [midje.sweet :refer [fact
                                 facts
                                 =>]]))

(facts "about 'is-unique?"
       (fact "should be true if all characters are unique"
             (aas/is-unique? "blah") => true
             (aas/is-unique? "abcdef") => true)
       (fact "should be false if all characters are not unique"
             (aas/is-unique? "bblah") => false
             (aas/is-unique? "bllahh") => false
             (aas/is-unique? "blahdeblah") => false))