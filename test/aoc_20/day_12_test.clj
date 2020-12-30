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

(facts "about 'rotate-waypoint'"
       (fact "should rotate the waypoint the given number of degrees"
             (d12/rotate-waypoint {:north 30
                                   :east 25} :left 90) => {:west 30
                                                           :north 25}
             (d12/rotate-waypoint {:north 30
                                   :east 25} :right 90) => {:east 30
                                                            :south 25}))

(facts "about 'advance'"
       (fact "should advance to the waypoint n times"
             (d12/advance {:waypoint {:north 30
                                      :east 20}
                           :x 0
                           :y 0} 4) => {:waypoint {:north 30
                                                   :east 20}
                                        :x 120
                                        :y 80}
             (d12/advance {:waypoint {:north 5
                                      :west 10}
                           :x 10
                           :y 5} 3) => {:waypoint {:north 5
                                                   :west 10}
                                        :x 25
                                        :y -25}
             (d12/advance {:waypoint {:south 10
                                      :east 5}
                           :x 20
                           :y 0} 10) => {:waypoint {:south 10
                                                    :east 5}
                                         :x -80
                                         :y 50}))

(facts "about 'adjust-waypoint'"
       (fact "should adjust the waypoint based on the direction"
             (d12/adjust-waypoint {:north 10 :east 5} :south 5) => {:north 5 :east 5}
             (d12/adjust-waypoint {:north 15 :east 10} :west 25) => {:north 15 :west 15}
             (d12/adjust-waypoint {:north 5 :east 10} :north 5) => {:north 10 :east 10}))
