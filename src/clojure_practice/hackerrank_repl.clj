(ns clojure-practice.hackerrank-repl)

;; This is just used as a repl for solving hackerrank problems

; Given an array of integers, calculate the ratios of its elements that
; are positive, negative, and zero. Print the decimal value of each
; fraction on a new line with  places after the decimal.

; Note: This challenge introduces precision problems. The test cases are
; scaled to six decimal places, though answers with absolute error of up
; to  are acceptable.

; Example [1 1 0 -1 -1]

; There are 5 elements, two positive, two negative and one zero. Their
; ratios are 2/5, 2/5 and 1/5. Results are printed as:

; 0.400000
; 0.400000
; 0.200000

(defn get-count [filter-fn args]
  (-> (filter filter-fn args)
      (count)))

;; TODO: First pass, clean this up.
(defn plus-minus [args]
  (let [num (count args)
        positive (get-count pos? args)
        negative (get-count neg? args)
        zeroes   (get-count #(= 0 %) args)]
    (doseq [number [positive negative zeroes]]
      (println (format "%.6f" (double (/ number num)))))))

(comment 
(plus-minus [1 1 0 -1 -1])
)