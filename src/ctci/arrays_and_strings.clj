(ns ctci.arrays-and-strings
  (:require [clojure.string :as s]))

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
(defn in-str?
  "Determines if a character is found in a string"
  [str char]
  (boolean (re-find (re-pattern char) str)))

(defn remove-str
  "Removes the first occurence of a character from a string"
  [str char]
  (s/replace-first str (re-pattern char) ""))

(defn str-diff [str-a str-b]
  (loop [chars  (s/split str-a #"")
         only-a str-a
         only-b str-b
         both   ""]
    (if-let [this-char (first chars)]
      (if (in-str? only-b this-char)
        (recur (rest chars)
               (remove-str only-a this-char)
               (remove-str only-b this-char)
               (format "%s%s" both this-char))
        (recur (rest chars)
               only-a
               only-b
               both))
      [only-a only-b both])))

;; Note: I was thinking of this on my hike today and realized that
;; I don't consider rearranging characters as an edit. I'm not sure
;; if that's correct or not in the spirit of the question but it
;; seems right to me. I may come back to this at a later date and
;; add a function that doesn't allow rearranging characters. Or at
;; least considers it an edit when you move a character. I don't
;; know, we'll see.
(defn one-away?
  "This implementation relied on the clojure.data/diff function to
   determine the differences in the 2 strings, but because of a quirk
   in data/diff, it wasn't handling string diffs as expected. Now it
   relies on the str-diff function. We effectively remove all characters
   that are the same then evaluate what's left to see if we can make 1 or
   less changes to have the strings be equal."
  [first-string second-string]
  (let [[only-a only-b _] (str-diff first-string second-string)]
    (if (and (>= 1 (count only-a))
             (>= 1 (count only-b)))
      true
      false)))

;; Question 1.6: String Compression. Implement a function to perform
;; basic string compression using the counts of repeated characters.
;; If the compressed string is the same size or larger than the original,
;; then return the original uncompressed.
;; EX: aabcccccaaa -> a2b1c5a3

;; Note: Here's a first pass. It's kinda gnarly. I'll think on it a
;; bit and see if it can be refined.
(defn compress [string]
  (if (<= (count string) 2)
    string
    (let [chars-to-process (s/split string #"")]
      (loop [{:keys [previous-char
                     remaining-chars
                     char-count
                     compressed-string] :as state} {:previous-char     (first chars-to-process)
                                                    :remaining-chars   (rest chars-to-process)
                                                    :original-string   string
                                                    :char-count        1
                                                    :compressed-string ""}]
        (if-let [this-char (first remaining-chars)]
          (if (= this-char previous-char)
            (recur (assoc state 
                          :remaining-chars (rest remaining-chars)
                          :char-count (inc char-count)))
            (recur (assoc state
                          :remaining-chars   (rest remaining-chars)
                          :previous-char     this-char
                          :compressed-string (format "%s%s%d"
                                                     compressed-string
                                                     previous-char
                                                     char-count)
                          :char-count        1)))
          (let [new-string (format "%s%s%d" compressed-string previous-char char-count)
                original-string (:original-string state)]
            (if (>= (count new-string) (count original-string))
              original-string
              new-string)))))))

;; Question 1.7: Rotate Matrix: Given an image represented by an N x N matrix,
;; where each pixel in the image is represented by an integer, write a function
;; to rotate the image by 90 degrees.
;; Note: Instead of using a 2 dimensional array (int[][]), I'm going to use
;; a sequence of sequences since this is clojure.
;; Ex: [[01 02 03 04]
;;      [05 06 07 08]
;;      [09 10 11 12]]

(defn build-grid [width height]
  (partition width (map inc (range (* width height)))))

;; First pass, handles grids of varying sizes.
(defn rotate [grid]
  (let [column-order (-> (first grid)
                         (count)
                         (range))
        row-order    (-> (count grid)
                         (range)
                         (reverse))]
    (for [x column-order]
      (for [y row-order]
        (-> (nth grid y)
            (nth x))))))

;; Question 1.8: Zero Matrix - Write an algorithm such that if an
;; element in an M x N matrix is 0, it's entire row and column
;; are set to 0.

;; Question 1.9: String Rotation - Assume you have a method isSubstring
;; which checks if one word is a substring of another. Given two strings,
;; s1 and s2, write code to check if s2 is a rotation of s1 using only
;; one call to isSubstring (e.g. "waterbottle" is a rotation of
;; "erbottlewat").
(defn is-substring? [s1 s2]
  (s/includes? (str s2 s1) s1))