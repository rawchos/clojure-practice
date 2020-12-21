(ns aoc-20.day-7-test
  (:require [aoc-20.day-7 :as d7]
            [midje.sweet :refer [fact
                                 facts
                                 =>]]))

(facts "about 'descr->keyword'"
       (fact "should convert a description to a keyword"
             (d7/descr->keyword "light blue") => :light-blue))

(facts "about 'add-rule'"
       (fact "should find matching descriptions"
             (d7/add-rule "1 bright white bag") => :bright-white
             (d7/add-rule "4 muted yellow bags.") => :muted-yellow)
       (fact "should return nil if no matching rule found"
             (d7/add-rule "no other bags.") => nil))

(facts "about 'parse-rule'"
       (fact "should return a map with description and other allowed types"
             (d7/parse-rule "light red bags contain 1 bright white bag, 2 muted yellow bags.") => {:light-red [:bright-white :muted-yellow]}
             (d7/parse-rule "bright white bags contain 1 shiny gold bag.") => {:bright-white [:shiny-gold]}
             (d7/parse-rule "dotted black bags contain no other bags.") => {:dotted-black []}))


