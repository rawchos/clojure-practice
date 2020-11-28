(ns ctci.arrays-and-strings)

;; Question 1.1; Determine if a string passed in is all
;; unique characters.
(defn is-unique? [string]
  (let [char-set (set string)]
    (= (count char-set)
       (count string))))