(ns aoc-20.day-1-test
  (:require [midje.sweet :refer [fact
                                 facts
                                 =>]]
            [aoc-20.day-1 :as d1]))

(facts "about 'part-1'"
       (fact "should multiply the 2 numbers that sum up to 2020"
             (d1/part-1 [1721 979 366 299 675 1456]) => 514579))

(facts "about 'part-2'"
       (fact "should multiply the 3 numbers that sum up to 2020"
             (d1/part-2 [1721 979 366 299 675 1456]) => 241861950))

(facts "about 'sum-hash'"
       (fact "should create a map with the sum of each number added to every other"
             (d1/sum-hash [1721 979 366 299 675 1456]) => {2435 [979 1456]
                                                           3177 [1721 1456]
                                                           2020 [1721 299]
                                                           1654 [979 675]
                                                           974 [299 675]
                                                           665 [366 299]
                                                           1345 [979 366]
                                                           2131 [675 1456]
                                                           1278 [979 299]
                                                           1822 [366 1456]
                                                           2396 [1721 675]
                                                           1755 [299 1456]
                                                           1041 [366 675]
                                                           2087 [1721 366]
                                                           2700 [1721 979]}
             (d1/sum-hash [1 2 3 4]) => {3 [1 2]
                                         4 [1 3]
                                         5 [2 3]
                                         6 [2 4]
                                         7 [3 4]}))