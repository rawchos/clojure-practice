(ns interview-questions.stock-profit-test
  (:require [interview-questions.stock-profit :as sp]
            [midje.sweet :refer [fact
                                 facts
                                 =>]]))

(facts "about 'at-least-2?'"
       (fact "should verify whether or not there are at least 2 of a number"
             (sp/at-least-2? [6 12 3 9 3 5 1] 3) => true
             (sp/at-least-2? [6 12 3 9 3 5 1] 6) => false))

(facts "about 'stock-profit'"
       (fact "should find the correct number of pairs with duplicate numbers"
             (sp/stock-profit [1 3 46 1 3 9] 47) => 1)
       (fact "should find the correct number of pairs when only 1 number of half the target"
             (sp/stock-profit [6 12 3 9 3 5 1] 12) => 2))
