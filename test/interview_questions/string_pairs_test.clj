(ns interview-questions.string-pairs-test
  (:require [interview-questions.string-pairs :as sp]
            [midje.sweet :refer [fact
                                 facts
                                 =>]]))

(facts "about 'string-pairs.solution'"
       (fact "should split even numbered strings equally"
             (sp/solution "blah") => ["bl" "ah"])
       (fact "should add an underscore for odd numbered strings"
             (sp/solution "blahh") => ["bl" "ah" "h_"]))
