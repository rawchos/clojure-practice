(ns ctci.arrays-and-strings
  (:require [clojure.string :as s]))

;; Question 1.1; Determine if a string passed in is all
;; unique characters.
(defn is-unique? [string]
  (let [low-string (s/lower-case string)
        char-set (set low-string)]
    (= (count char-set)
       (count low-string))))

;; Question 1.2: Given two strings, write a method to determine if
;; one is a permutation of the other.
(defn lower-freqs [string]
  (frequencies (s/lower-case string)))

(defn is-permutation? 
  "Check's to see if the strings are permutations of each other by
   first converting to lower case, then comparing the frequencies of
   each character. This function doesn't do anything with spaces."
  [first-string second-string]
  (= (lower-freqs first-string)
     (lower-freqs second-string)))

;; Question 1.3: Write a method to replace spaces with '%20'.
(defn urlify 
  "Replaces spaces with '%20'. Does an in-place replace using the
   clojure.string/replace function."
  [url]
  (s/replace url #" " "%20"))

;; TODO: Implement urlify without using s/replace.
