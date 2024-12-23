(ns aoc-20.day-12-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc-20.day-12 :as d12]))

(deftest rotation-turns-test
       (testing "should tell the number of turns on the dial based on the degrees"
             (is (= (d12/rotation-turns 90) 1))
             (is (= (d12/rotation-turns 180) 2))
             (is (= (d12/rotation-turns 360) 0))
             (is (= (d12/rotation-turns 270) 3))
             (is (zero? (d12/rotation-turns 720)))))

(deftest rotate-test
       (testing "should rotate to the correct compass point based on the degrees and direction"
             (is (= (d12/rotate :west :right 90) :north))
             (is (= (d12/rotate :east :left 180) :west))
             (is (= (d12/rotate :north :right 360) :north))
             (is (= (d12/rotate :north :right 180) :south))))

(deftest first-index-of-test
       (let [things [:one :two :three :four :three :two :one]]
         (testing "should find the correct index"
               (is (= (d12/first-index-of :two things) 1))
               (is (= (d12/first-index-of :four things) 3)))))

(deftest rotate-waypoint-test
       (testing "should rotate the waypoint the given number of degrees"
             (is (= (d12/rotate-waypoint {:north 30
                                          :east 25} :left 90) {:west 30
                                                               :north 25}))
             (is (= (d12/rotate-waypoint {:north 30
                                          :east 25} :right 90) {:east 30
                                                                :south 25}))))

(deftest advance-test
       (testing "should advance to the waypoint n times"
             (is (= (d12/advance {:waypoint {:north 30
                                      :east 20}
                           :x 0
                           :y 0} 4) {:waypoint {:north 30
                                                   :east 20}
                                        :x 120
                                        :y 80}))
             (is (= (d12/advance {:waypoint {:north 5
                                      :west 10}
                           :x 10
                           :y 5} 3) {:waypoint {:north 5
                                                   :west 10}
                                        :x 25
                                        :y -25}))
             (is (= (d12/advance {:waypoint {:south 10
                                      :east 5}
                           :x 20
                           :y 0} 10) {:waypoint {:south 10
                                                    :east 5}
                                         :x -80
                                         :y 50}))))

(deftest adjust-waypoint-test
       (testing "should adjust the waypoint based on the direction"
             (is (= (d12/adjust-waypoint {:north 10 :east 5} :south 5) {:north 5 :east 5}))
             (is (= (d12/adjust-waypoint {:north 15 :east 10} :west 25) {:north 15 :west 15}))
             (is (= (d12/adjust-waypoint {:north 5 :east 10} :north 5) {:north 10 :east 10}))))
