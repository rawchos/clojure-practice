(ns interview-questions.anagrams-test
  (:require [interview-questions.anagrams :as ana]
            [clojure.test :refer [deftest is testing]]))

(deftest anagram-groups1-test
       (testing "should count the number of anagram groups"
             (is (= (ana/anagram-groups1 ["cat" "kitten" "listen" "silent" "salient"]) 4))
             (is (= (ana/anagram-groups1 ["one" "two" "three"]) 3))
             (is (= (ana/anagram-groups1 ["emac" "mace"]) 1))))

(deftest anagram-groups2-test
       (testing "should count the number of anagram groups"
             (is (= (ana/anagram-groups2 ["cat" "kitten" "listen" "silent" "salient"]) 4))
             (is (= (ana/anagram-groups2 ["one" "two" "three"]) 3))
             (is (= (ana/anagram-groups2 ["emac" "mace"]) 1))))
