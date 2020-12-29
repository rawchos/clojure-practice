(ns aoc-20.day-12-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-12 :as d12]))

(facts "about 'rotation-turns'"
       (fact "should tell the number of turns on the dial based on the degrees"
             (d12/rotation-turns 90) => 1
             (d12/rotation-turns 180) => 2
             (d12/rotation-turns 360) => 0
             (d12/rotation-turns 270) => 3
             (d12/rotation-turns 720) => 0))

(facts "about 'rotate'"
       (fact "should rotate to the correct compass point based on the degrees and direction"
             (d12/rotate :west :right 90) => :north
             (d12/rotate :east :left 180) => :west
             (d12/rotate :north :right 360) => :north
             (d12/rotate :north :right 180) => :south))

(facts "about 'first-index-of'"
       (let [things [:one :two :three :four :three :two :one]]
         (fact "should find the correct index"
               (d12/first-index-of :two things) => 1
               (d12/first-index-of :four things) => 3)))
