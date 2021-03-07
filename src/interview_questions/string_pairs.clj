(ns interview-questions.string-pairs)

;; Complete the solution so that it splits the string into pairs of two
;; characters. If the string contains an odd number of characters then it
;; should replace the missing second character of the final pair with an
;; underscore ('_').

; Examples:

; (solution "abc") ; => ["ab" "c_"]
; (solution "abcd") ; => ["ab" "cd"]

(defn solution [string]
  (mapv (fn [[c1 c2]]
          (str c1 (if c2 c2 "_")))
        (partition-all 2 string)))
