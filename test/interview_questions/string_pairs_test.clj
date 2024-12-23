(ns interview-questions.string-pairs-test
  (:require [interview-questions.string-pairs :as sp]
            [clojure.test :refer [deftest is testing]]))

(deftest string-pairs.solution-test
       (testing "should split even numbered strings equally"
             (is (= (sp/solution "blah") ["bl" "ah"])))
       (testing "should add an underscore for odd numbered strings"
             (is (= (sp/solution "blahh") ["bl" "ah" "h_"]))))
