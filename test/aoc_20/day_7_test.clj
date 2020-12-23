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
             (d7/parse-rule d7/add-rule "light red bags contain 1 bright white bag, 2 muted yellow bags.") => {:light-red [:bright-white :muted-yellow]}
             (d7/parse-rule d7/add-rule "bright white bags contain 1 shiny gold bag.") => {:bright-white [:shiny-gold]}
             (d7/parse-rule d7/add-rule "dotted black bags contain no other bags.") => {:dotted-black []})
       (fact "should return a map with description and count if using add-rule-with-count"
             (d7/parse-rule d7/add-rule-with-count "light red bags contain 1 bright white bag, 2 muted yellow bags.") => {:light-red [[:bright-white 1] [:muted-yellow 2]]}
             (d7/parse-rule d7/add-rule-with-count "bright white bags contain 1 shiny gold bag.") => {:bright-white [[:shiny-gold 1]]}
             (d7/parse-rule d7/add-rule-with-count "dotted black bags contain no other bags.") => {:dotted-black []}))

(let [rules {:vibrant-plum [[:faded-blue 5] [:dotted-black 6]]
             :light-red [[:bright-white 1] [:muted-yellow 2]]
             :shiny-gold [[:dark-olive 1] [:vibrant-plum 2]]
             :dark-orange [[:bright-white 3] [:muted-yellow 4]]
             :bright-white [[:shiny-gold 1]]
             :muted-yellow [[:shiny-gold 2] [:faded-blue 9]]
             :faded-blue []
             :dark-olive [[:faded-blue 3] [:dotted-black 4]]
             :dotted-black []}]
  (fact "'count-bags' should accurately count the number of bags required"
        (d7/count-bags rules :shiny-gold) => 32
        (d7/count-bags rules :faded-blue) => 0
        (d7/count-bags rules :dark-orange) => 406))


