(ns ctci.big-o-test
  (:require [ctci.big-o :as bo]
            [clojure.test :refer [deftest is testing]]))

(deftest recur-sum-test
       (testing "should add up the numbers correctly"
             (is (= (bo/recur-sum 7) 28))
             (is (= (bo/recur-sum 10) 55))))

(deftest reduce-sum-test
       (testing "should add up the numbers correctly"
             (is (= (bo/reduce-sum 7) 28))
             (is (= (bo/reduce-sum 10) 55))))