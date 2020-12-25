(ns aoc-20.day-8-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-8 :as d8]))

(facts "about 'process-instruction'"
       (let [state {:instruction-line       20
                    :processed-instructions [0]
                    :accumulator            10}]
         (fact "should process jmp instructions"
               (d8/process-instruction state "jmp -4") => {:instruction-line 16
                                                           :processed-instructions [0 20]
                                                           :accumulator 10}
               (d8/process-instruction state "jmp +5") => {:instruction-line 25
                                                           :processed-instructions [0 20]
                                                           :accumulator 10})
         (fact "should process nop instructions"
               (d8/process-instruction state "nop +0") => {:instruction-line 21
                                                           :processed-instructions [0 20]
                                                           :accumulator 10}
               (d8/process-instruction state "nop +4") => {:instruction-line 21
                                                           :processed-instructions [0 20]
                                                           :accumulator 10})
         (fact "should process acc instructions"
               (d8/process-instruction state "acc +5") => {:instruction-line 21
                                                           :processed-instructions [0 20]
                                                           :accumulator 15}
               (d8/process-instruction state "acc -5") => {:instruction-line 21
                                                           :processed-instructions [0 20]
                                                           :accumulator 5})))

(facts "about 'in?'"
       (fact "should verify whether an item is in a collection"
             (d8/in? [0 1 2 4] 3) => false
             (d8/in? [0 1 2 4] 1) => true))

(facts "about 'flip-instruction'"
       (fact "should flip jmp to nop"
             (d8/flip-instruction "jmp +3") => "nop +3")
       (fact "should flip nop to jmp"
             (d8/flip-instruction "nop +0") => "jmp +0"))
