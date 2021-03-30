(ns interview-questions.anagrams)

;; Question: For a given array of strings, group the words up that
;; are anagrams of one another. If a word has no anagrams within the
;; array, it will be a group by itself. Count the number of groups
;; you have.

;; Initial solution
(defn anagram-groups1 [words]
  (count
   (for [[_ group] (group-by frequencies words)]
     group)))

;; Cleaner version
(defn anagram-groups2 [words]
  (-> (group-by frequencies words)
      (count)))
