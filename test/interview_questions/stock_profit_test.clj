(ns interview-questions.stock-profit-test
  (:require [interview-questions.stock-profit :as sp]
            [clojure.test :refer [deftest is testing]]))

(deftest at-least-2?-test
       (testing "should verify whether or not there are at least 2 of a number"
             (is (true? (sp/at-least-2? [6 12 3 9 3 5 1] 3)))
             (is (false? (sp/at-least-2? [6 12 3 9 3 5 1] 6)))))

(deftest stock-profit-test
       (testing "should find the correct number of pairs with duplicate numbers"
             (is (= (sp/stock-profit [1 3 46 1 3 9] 47) 1)))
       (testing "should find the correct number of pairs when only 1 number of half the target"
             (is (= (sp/stock-profit [6 12 3 9 3 5 1] 12) 2))))
