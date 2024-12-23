(ns aoc-20.day-8-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-8 :as d8]))

(deftest process-instruction-test
       (let [state {:instruction-line       20
                    :processed-instructions [0]
                    :accumulator            10}]
         (testing "should process jmp instructions"
               (is (= (d8/process-instruction state "jmp -4") {:instruction-line 16
                                                               :processed-instructions [0 20]
                                                               :accumulator 10}))
               (is (= (d8/process-instruction state "jmp +5") {:instruction-line 25
                                                               :processed-instructions [0 20]
                                                               :accumulator 10})))
         (testing "should process nop instructions"
               (is (= (d8/process-instruction state "nop +0") {:instruction-line 21
                                                               :processed-instructions [0 20]
                                                               :accumulator 10}))
               (is (= (d8/process-instruction state "nop +4") {:instruction-line 21
                                                               :processed-instructions [0 20]
                                                               :accumulator 10})))
         (testing "should process acc instructions"
               (is (= (d8/process-instruction state "acc +5") {:instruction-line 21
                                                               :processed-instructions [0 20]
                                                               :accumulator 15}))
               (is (= (d8/process-instruction state "acc -5") {:instruction-line 21
                                                               :processed-instructions [0 20]
                                                               :accumulator 5})))))

(deftest in?-test
       (testing "should verify whether an item is in a collection"
             (is (false? (d8/in? [0 1 2 4] 3)))
             (is (true? (d8/in? [0 1 2 4] 1)))))

(deftest flip-instruction-test
       (testing "should flip jmp to nop"
             (is (= (d8/flip-instruction "jmp +3") "nop +3")))
       (testing "should flip nop to jmp"
             (is (= (d8/flip-instruction "nop +0") "jmp +0"))))
