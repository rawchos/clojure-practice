(ns aoc-20.day-7
  (:require [aoc-utils.core :as util]
            [clojure.string :as s]))

(defn read-lines [filename]
  (util/read-lines filename))

;; --- Day 7: Handy Haversacks ---
;; You land at the regional airport in time for your next flight. In fact,
;; it looks like you'll even have time to grab some food: all flights are
;; currently delayed due to issues in luggage processing.
;; 
;; Due to recent aviation regulations, many rules (your puzzle input) are
;; being enforced about bags and their contents; bags must be color-coded
;; and must contain specific quantities of other color-coded bags.
;; Apparently, nobody responsible for these regulations considered how
;; long they would take to enforce!
;; 
;; For example, consider the following rules:
;; 
;; light red bags contain 1 bright white bag, 2 muted yellow bags.
;; dark orange bags contain 3 bright white bags, 4 muted yellow bags.
;; bright white bags contain 1 shiny gold bag.
;; muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
;; shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
;; dark olive bags contain 3 faded blue bags, 4 dotted black bags.
;; vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
;; faded blue bags contain no other bags.
;; dotted black bags contain no other bags.
;; 
;; These rules specify the required contents for 9 bag types. In this
;; example, every faded blue bag is empty, every vibrant plum bag contains
;; 11 bags (5 faded blue and 6 dotted black), and so on.
;; 
;; You have a shiny gold bag. If you wanted to carry it in at least one
;; other bag, how many different bag colors would be valid for the
;; outermost bag? (In other words: how many colors can, eventually,
;; contain at least one shiny gold bag?)
;; 
;; In the above rules, the following options would be available to you:
;; 
;; A bright white bag, which can hold your shiny gold bag directly.
;; A muted yellow bag, which can hold your shiny gold bag directly, plus
;;   some other bags.
;; A dark orange bag, which can hold bright white and muted yellow bags,
;;   either of which could then hold your shiny gold bag.
;; A light red bag, which can hold bright white and muted yellow bags,
;;   either of which could then hold your shiny gold bag.
;; So, in this example, the number of bag colors that can eventually
;; contain at least one shiny gold bag is 4.
;; 
;; How many bag colors can eventually contain at least one shiny gold
;; bag? (The list of rules is quite long; make sure you get all of it.)

(defn descr->keyword [descr]
  (-> (s/lower-case descr)
      (s/replace #" " "-")
      (keyword)))

(defn add-rule [rule]
  (when-let [[_ descr] (re-find #"\d+ (\w+ \w+) bag.*" rule)]
    (descr->keyword descr)))

;; Note: Used in part-2. It's up here because we're re-using
;; parse-rule in part-2.
(defn add-rule-with-count [rule]
  (when-let [[_ amount descr] (re-find #"(\d+) (\w+ \w+) bag.*" rule)]
    [(descr->keyword descr) (Integer/parseInt amount)]))

(defn in? [coll item]
  (some #(= item %) coll))

;; TODO: This probably isn't the most efficient. We do a lot of the
;; work multiple times. May come back and revisit at some point.
(defn has-path? 
  "This function will check to see if the description passed in
   (in the form of a keyword) has a path to :shiny-gold. It returns
   either true or nil."
  [rules descr]
  (when-let [allowed (descr rules)]
    (if (in? allowed :shiny-gold)
      true
      (loop [bags allowed]
        (if-let [bag (first bags)]
          (if (has-path? rules bag)
            true
            (recur (rest bags)))
          false)))))

(defn parse-rule [add-rule-f line]
  (let [[container containees] (s/split line #" contain ")
        [_ descr] (re-find #"(\w+ \w+) bags" container)]
    {(descr->keyword descr)
     (into []
           (->> (map add-rule-f (s/split containees #", "))
                (remove nil?)))}))

(defn part-1 [input]
  (let [rules (into {} (map (partial parse-rule add-rule) input))]
    (->> (for [descr (keys rules)] (has-path? rules descr))
         (filter #(= true %))
         (count))))

; (part-1 (read-lines "day7-example-input.txt"))
; (part-1 (read-lines "day7-input.txt"))

;; --- Part Two ---
;; It's getting pretty expensive to fly these days - not because of ticket
;; prices, but because of the ridiculous number of bags you need to buy!
;; 
;; Consider again your shiny gold bag and the rules from the above example:
;; 
;; faded blue bags contain 0 other bags.
;; dotted black bags contain 0 other bags.
;; vibrant plum bags contain 11 other bags: 5 faded blue bags and 6 dotted
;;   black bags.
;; dark olive bags contain 7 other bags: 3 faded blue bags and 4 dotted
;;   black bags.
;; So, a single shiny gold bag must contain 1 dark olive bag (and the 7
;; bags within it) plus 2 vibrant plum bags (and the 11 bags within each
;; of those): 1 + 1*7 + 2 + 2*11 = 32 bags!
;; 
;; Of course, the actual rules have a small chance of going several levels
;; deeper than this example; be sure to count all of the bags, even if the
;; nesting becomes topologically impractical!
;; 
;; Here's another example:
;; 
;; shiny gold bags contain 2 dark red bags.
;; dark red bags contain 2 dark orange bags.
;; dark orange bags contain 2 dark yellow bags.
;; dark yellow bags contain 2 dark green bags.
;; dark green bags contain 2 dark blue bags.
;; dark blue bags contain 2 dark violet bags.
;; dark violet bags contain no other bags.
;; In this example, a single shiny gold bag must contain 126 other bags.
;; 
;; How many individual bags are required inside your single shiny gold bag?
(defn count-bags [rules bag]
  (let [bags (bag rules)]
    (if (empty? bags)
      0
      (reduce +
              (for [[next-bag amount] bags]
                (+ (* amount (count-bags rules next-bag)) amount))))))

(defn part-2 [input]
  (let [rules (into {} (map (partial parse-rule add-rule-with-count) input))]
    (count-bags rules :shiny-gold)))

; (part-2 (read-lines "day7-input.txt"))