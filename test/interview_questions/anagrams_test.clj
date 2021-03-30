(ns interview-questions.anagrams-test
  (:require [interview-questions.anagrams :as ana]
            [midje.sweet :refer [fact
                                 facts
                                 =>]]))

(facts "about 'anagram-groups1'"
       (fact "should count the number of anagram groups"
             (ana/anagram-groups1 ["cat" "kitten" "listen" "silent" "salient"]) => 4
             (ana/anagram-groups1 ["one" "two" "three"]) => 3
             (ana/anagram-groups1 ["emac" "mace"]) => 1))

(facts "about 'anagram-groups2'"
       (fact "should count the number of anagram groups"
             (ana/anagram-groups2 ["cat" "kitten" "listen" "silent" "salient"]) => 4
             (ana/anagram-groups2 ["one" "two" "three"]) => 3
             (ana/anagram-groups2 ["emac" "mace"]) => 1))
