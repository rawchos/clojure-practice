(ns ctci.arrays-and-strings
  (:require [clojure.string :as s]
            [clojure.data :as d]))

;; Question 1.1; Determine if a string passed in is all
;; unique characters.
(defn is-unique? [string]
  (let [low-string (s/lower-case string)
        char-set (set low-string)]
    (= (count char-set)
       (count low-string))))

;; Question 1.2: Given two strings, write a function to determine if
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

;; Question 1.3: Write a function to replace spaces with '%20'.
(defn urlify 
  "Replaces spaces with '%20'. Does an in-place replace using the
   clojure.string/replace function."
  [url]
  (s/replace url #" " "%20"))

(defn convert-space [char]
  (if (= \space char)
    "%20"
    char))

(defn more-urlify
  "Another implementation of urlify that doesn't rely on
   clojure.string/replace."
  [url]
  (apply str (map convert-space url)))

;; Question 1.4: Write a function to determine if the string passed
;; in is a permutation of a palindrome. You can ignore whitespace
;; and it doesn't have to be limited to dictionary words.
(defn remove-spaces [string]
  (s/replace string #" " ""))

;; Yes, clojure has an odd? function. This is just writing out
;; my own implementation for the fun of it.
(defn is-odd? [number]
  (pos? (mod number 2)))

;; Could this be better? Think on it a bit.
;; Answer: Yes, it definitely can. See succint-is-palindrome?
(defn is-palindrome? [string]
  (loop [counts (-> (remove-spaces string)
                    (s/lower-case)
                    (frequencies)
                    (vals))
         odds   0]
    (if (> odds 1)
      false
      (if-let [count (first counts)]
        (recur (rest counts) (if (is-odd? count)
                               (inc odds)
                               odds))
        true))))

;; Yeah, so, there's no need to use loop and recur to
;; traverse the count of occurences of each letter and look
;; for the count of odd ones. We already have the list, just
;; filter that list for only odd occurences and then count those.
(defn odds [sequence]
  (filter is-odd? sequence))

(defn succint-is-palindrome? [string]
  (-> (remove-spaces string)
      (s/lower-case)
      (frequencies)
      (vals)
      (odds)
      (count)
      (<= 1)))

;; Question 1.5: One Away. There are 3 types of edits that can be
;; performed on strings: insert a character, remove a character,
;; or replace a character. Given a string, write a function to
;; determine if they are 1 or 0 edits away from each other.
;; TODO: This isn't working right. If there's a difference in
;; the middle, diff is reporting the entire rest wrong
(defn in-str?
  "Determines if a character is found in a string"
  [str char]
  (boolean (re-find (re-pattern char) str)))

(defn remove-str
  "Removes the first occurence of a character from a string"
  [str char]
  (s/replace-first str (re-pattern char) ""))

;; TODO: Convert this to use in-str? and remove-str
(defn str-diff [str-a str-b]
  (loop [chars  (s/split str-a #"")
         only-a str-a
         only-b str-b
         both   ""]
    (if-let [this-char (first chars)]
      (if (re-find (re-pattern this-char) only-b)
        (recur (rest chars)
               (s/replace-first only-a (re-pattern this-char) "")
               (s/replace-first only-b (re-pattern this-char) "")
               (format "%s%s" both this-char))
        (recur (rest chars)
               only-a
               only-b
               both))
      [only-a only-b both])))

;; TODO: Update this to use str-diff. Won't be dealing with vectors
;; anymore, only strings.
(defn one-away?
  "This implementation relies on the clojure.data/diff function to
   determine the differences in the 2 strings. We effectively remove
   all characters that are the same then evaluate what's left to see
   if we can make 1 or less changes to have the strings be equal."
  [first-string second-string]
  (let [[only-a only-b _] (d/diff (seq first-string) (seq second-string))]
    (if (and (>= 1 (count (remove nil? only-a)))
             (>= 1 (count (remove nil? only-b))))
      true
      false)))