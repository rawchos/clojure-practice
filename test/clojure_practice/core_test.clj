(ns clojure-practice.core-test
  (:require [clojure-practice.core :as core]
            [clojure.test :refer [deftest is testing]]))
(deftest contrived-test
       (testing "the contrived test should return true"
             (is (true? (core/contrived-test)))))
