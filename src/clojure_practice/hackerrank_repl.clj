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

; Given a square matrix, calculate the absolute difference between the
; sums of its diagonals.

; For example, the square matrix arr is shown below:

; 1 2 3
; 4 5 6
; 9 8 9  

; The left-to-right diagonal = 1 + 5 + 9 = 15. The right-to-left
; diagonal = 3 + 5 + 9 = 17. Their absolute difference is 15 - 17 = 2.

(defn absolute [number]
  (if (neg? number)
    (* -1 number)
    number))

(defn diagonal-difference [matrix]
  (let [size (count (first matrix))]
    (loop [left-positions  (range size)
           right-positions (reverse left-positions)
           left-diags []
           right-diags []
           rows-left matrix]
      (let [left-position (first left-positions)
            right-position (first right-positions)
            this-row (first rows-left)]
        (if (seq this-row)
          (recur (rest left-positions)
                 (rest right-positions)
                 (conj left-diags (nth this-row left-position))
                 (conj right-diags (nth this-row right-position))
                 (rest rows-left))
          (-> (- (reduce + left-diags)
                 (reduce + right-diags))
              absolute))))))

(defn staircase [height]
  (doseq [x (range 1 (+ 1 height))]
    (prn (apply str (flatten [(repeat (- height x) " ") (repeat x "#")])))))

(defn miniMaxSum [arr]
  (println (format "%d %d"
                   (reduce + (take 4 arr))
                   (reduce + (rest arr)))))

(defn birthdayCakeCandles
  "Takes an array of integers representing candle heights. Returns the
   count of the tallest candles."
  [candles])



(comment 
  (plus-minus [1 1 0 -1 -1])
  (diagonal-difference [[1 2 3]
                        [4 5 6]
                        [9 8 9]])
  (staircase 4)
  (miniMaxSum [1 2 3 4 5])
  
  )